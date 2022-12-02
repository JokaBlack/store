let PAGE = 0;
let SIZE = 4;
const BASE_URl = "http://localhost:8080/";

let TOTAL_PAGES = 0;
let CURRENT_PAGE = 0;
let SEARCHED;
let SEARCH_PRESSED = false;

let input = document.getElementById('input');
doFocus();


function createProductElement(product) {
    let newProduct = document.createElement('div');
    const htmlPost =
        `<div class="card mx-3 my-3 col" style="max-width: 300px;">
    <form action="/cart/add" method="post">

            <img src="${product.img}" class="card-img-top" alt="image" style="height: auto; width: 100%">
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    <h6>Price: ${product.price} $</h6>
                    <a onclick="showHideDescription(${product.id})" class="btn btn-primary">More</a>
                    <button class="btn btn-secondary mt-auto " type="submit">
                        <i class="bi bi-cart-plus-fill fs-4"></i>
                    </button>
                    <div>
                        <span onclick="showHideComments(${product.id})" id="comm${product.id}" class = "h1 mx-2">
                            <i class="bi bi-chat-left-dots"></i>
                        </span>
                    </div>
                    
                    <input name="productId" type="hidden" value="${product.id}">
                    <input name="${paramName}" type="hidden" value="${token}">
            </form>
            
             <div class="modal fade align-middle" id="commentModal${product.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Post comments</h5>
                </div>
                <div class="modal-body">
                    <div id="comm-box${product.id}">

                    </div>
                    <form class="comment-form">
                        <div class="mb-3">
                            <label class="form-label">You can add comment</label>
                            <textarea required type="text" class="form-control" name="feedbackText"></textarea>
                        </div>
                        <input type="hidden" name="productId" value="${product.id}">
                        <input name="${paramName}" type="hidden" value="${token}">
                        <button type="submit" class="btn btn-primary" id="add-comm-btn">Add comment</button>
                    </form>
                </div>
                <div class="modal-footer">
                    <button onclick="showHideComments(${product.id})" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    
    <div class="modal fade align-middle" id="moreModal${product.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Description</h5>
                </div>
                <div class="modal-body">
                    <div class="alert alert-success" role="alert">
                        <p>${product.description}</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button onclick="showHideDescription(${product.id})" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

                </div>`
    newProduct.innerHTML = htmlPost;
    return newProduct;

}

let token = document.getElementsByName("csrf")[0].content;
let paramName = document.getElementsByName("csrf_parameter_name")[0].content;
console.log(token)
console.log(paramName)

function addProduct(productElement) {
    let addCommBtn = productElement.getElementsByClassName("comment-form")[0];
    addCommBtn.addEventListener("submit", addComment);
    function addComment(event){
        event.preventDefault();
        const form = event.target;
        const formData = new FormData(form);

        let object = {};

        formData.forEach((value, key) => {
            object[key] = value;
        });
        const pubId = object['pubId'];
        sendCommAdd(formData, pubId);
    }
    document.getElementById("product-box").prepend(productElement);
}

function sendProductRequest() {
    axios.get(BASE_URl + "products/allproducts?page=" + PAGE + "&size=" + SIZE)
        .then((response) => {
            const answer = response.data;
            let products = answer.content;

            TOTAL_PAGES = answer.totalPages;
            CURRENT_PAGE = answer.pageable.pageNumber;

            console.log(answer);
            console.log(answer.content);
            products.forEach(e => {
                addProduct(createProductElement(e));
            });
        })
        .catch(function (error) {
            console.log(error);
        })
}

sendProductRequest();

function next() {
    if (CURRENT_PAGE != (TOTAL_PAGES - 1)) {
        productBoxCleaner();
        PAGE = PAGE + 1;
        if (SEARCH_PRESSED === false) {
            sendProductRequest();
        } else {
            sendProductSearchRequest();
        }
    } else {
        alert("Sorry you cant press next!")
    }
}

function prev() {
    if (CURRENT_PAGE != 0) {
        productBoxCleaner();
        PAGE = PAGE - 1;
        if (SEARCH_PRESSED === false) {
            sendProductRequest();
        } else {
            sendProductSearchRequest()
        }
    } else {
        alert("sorry!cant prev")
    }
}

function productBoxCleaner() {
    document.getElementById("product-box").innerHTML = "";
}

let nextBtn = document.getElementById("next-btn");


let prevBtn = document.getElementById("prev-btn");




function sendProductSearchRequest() {
    axios.get(BASE_URl + "products/findbydescr?desc=" + SEARCHED + "&page=" + PAGE + "&size=" + SIZE)
        .then((response) => {
            const answer = response.data;
            let products = answer.content;

            TOTAL_PAGES = answer.totalPages;
            CURRENT_PAGE = answer.pageable.pageNumber;

            console.log(answer);
            console.log(answer.content);
            products.forEach(e => {
                addProduct(createProductElement(e));
            });
        })
        .catch(function (error) {
            console.log(error);
        })
}


const searchForm = document.getElementById("search-form");
searchForm.addEventListener('submit', searchFunc);

function searchFunc(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    let text = formData.get("searchText");
    SEARCHED = text;
    PAGE = 0;
    SEARCH_PRESSED = true;
    input.focus();
    inputCleaner();

    productBoxCleaner();
    sendProductSearchRequest();

}

function inputCleaner() {
    input.value = '';
}

function doFocus() {
    input.focus();
}

function onlineStoreBtn() {
    productBoxCleaner();
    PAGE = 0;
    SEARCH_PRESSED = false;
    sendProductRequest();
}

function showHideComments(prodId) {
    let comm = document.getElementById("commentModal" + prodId);
    if (comm.style.display == "none") {
        sendGetComms(prodId)
        comm.style.display = 'block';
        comm.style.opacity = '1';
    } else {
        commBoxCleaner(prodId);
        comm.style.display = 'none';
        comm.style.opacity = '0';
    }

}

function showHideDescription(prodId) {
    let description = document.getElementById("moreModal" + prodId);
    if (description.style.display == "none") {
        sendGetComms(prodId)
        description.style.display = 'block';
        description.style.opacity = '1';
    } else {
        commBoxCleaner(prodId);
        description.style.display = 'none';
        description.style.opacity = '0';
    }

}

function sendGetComms(prodId) {
    axios.get(BASE_URl + 'feedbacks/' + prodId)
        .then((response) => {
            const comms = response.data;
            console.log(comms);
            comms.forEach(e => {
                createCommentElement(e, prodId)
            });
        })
        .catch(function (error) {
            console.log(error);
        })
}

function sendCommAdd(formData, pubId) {
    axios.post(BASE_URl + 'feedbacks/add', formData)
        .then((response) => {
            const comm = response.data.body;
            console.log(comm);
            createCommentElement(comm, pubId);
            // problem with redirect and method createCommentElement(cant add without reload)
        })
        .catch(function (error){
            // alert("Perhaps you did not buy this product?")
            let status = error.response.status;
            if(status === 308){
                alert("You are not authorized!")
            }
        })
}


function commBoxCleaner(prodId) {
    document.getElementById("comm-box" + prodId).innerHTML = "";
}

function createCommentElement(comment, pubId) {
    let newComm = document.createElement('div');
    const htmlComm = `
    <div class="alert alert-primary" role="alert">
        <div class="alert alert-dark py-0 w-50" role="alert">
             <h6>${comment.userDto.nickName}</h6>
             <h6>${comment.dateTime}</h6>
        </div>
        <p>commText: ${comment.text}</p>
    </div>`
    newComm.innerHTML = htmlComm;
    document.getElementById("comm-box" + pubId).prepend(newComm);
}





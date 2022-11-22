let PAGE = 0;
let SIZE = 1;
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
        `<div class="card mx-3 my-3" style="max-width: 300px;">
            <img src="${product.img}" class="card-img-top" alt="image" style="max-height: 250px;>
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    <h6>Price: ${product.price}</h6>
                    <a href="#" class="btn btn-primary">More</a>
                </div>
        </div>`
    newProduct.innerHTML = htmlPost;
    return newProduct;
}

function addProduct(productElement) {
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
        if(SEARCH_PRESSED === false){
            sendProductRequest();
        }else {
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
        if(SEARCH_PRESSED === false){
            sendProductRequest();
        }else {
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

function hideShowNext() {
    if (nextBtn.style.display == "none") {
        nextBtn.style.display = 'block';
        nextBtn.style.opacity = '1';
    } else {
        nextBtn.style.display = 'none';
        nextBtn.style.opacity = '0';
    }
}

let prevBtn = document.getElementById("prev-btn");

function hideShowPrev() {
    if (prevBtn.style.display == "none") {
        prevBtn.style.display = 'block';
        prevBtn.style.opacity = '1';
    } else {
        prevBtn.style.display = 'none';
        prevBtn.style.opacity = '0';
    }
}


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

function onlineStoreBtn(){
    productBoxCleaner();
    PAGE = 0;
    SEARCH_PRESSED = false;
    sendProductRequest();
}

// function btnVisible(answer){
//     let pageNum = answer.pageable.pageNumber;
//     console.log(answer.pageable.pageNumber)
//     console.log(answer.totalPages)
//     if(pageNum === (answer.totalPages - 1)){
//         hideShowNext();
//     }else if (pageNum === (answer.totalPages - 2) && pageNum != 0){
//         hideShowNext();
//     }else if(){
//
//     }
// }



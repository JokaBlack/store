<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="icon" href="/images/cart-icon-10.png" type="image/x-icon">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Istok+Web:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container bg-light">
        <nav class="navbar navbar-dark bg-dark bg-gradient">
            <div class="container-fluid">
                <a href="/" class="navbar-brand bg-transparent border-0">OnlineStore</a>
                
            </div>
        </nav>

        <div class="d-flex row row-cols-4 row-cols-md-4 row-cols-sm-3" id="cart-product-box">
            <#list cart.products as product>
                <div class="card mx-3 my-3 col" style="max-width: 300px;">
                        <img src="${product.img}">
                        <p>${product.name}</p>
                    <form action="/cart/delete" method="post" class="mt-auto">
                        <input name="productId" type="hidden" value="${product.id}">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                        </#if>
                        <button type="submit" class="vote flex align-center ">Delete</button>
                    </form>

                </div>
            </#list>
        </div>
        

    </div>


    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="js/mainPage.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
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

        <div class="d-flex row row-cols-lg-4 row row-cols-1 row-cols-md-3 row-cols-sm-2" id="cart-product-box">
            <#list cart.products as product>
                <div class="card mx-3 my-3 col" style="max-width: 300px;">
                        <img src="${product.product.img}">

                        <div class="btn-group" role="group" aria-label="Basic outlined example">
                            <form action="/cart/minus" method="post">
                            <button class="btn btn-outline-primary"><i class="bi bi-arrow-left-square"></i></button>
                                <input name="productId" type="hidden" value="${product.product.id}">
                                <#if _csrf??>
                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                                </#if>
                            </form>
                            <p class="px-3">${product.productAmount}</p>
                            <form action="/cart/plus" method="post">
                            <button class="btn btn-outline-primary"><i class="bi bi-arrow-right-square"></i></button>
                                <input name="productId" type="hidden" value="${product.product.id}">
                                <#if _csrf??>
                                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                                </#if>
                            </form>
                        </div>
                    <form action="/cart/delete" method="post" class="mt-auto">
                        <input name="productId" type="hidden" value="${product.product.id}">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                        </#if>
                        <button type="submit" class="vote flex align-center ">Delete</button>
                    </form>
                    <form action="/user/orders/add" method="post">
                        <input type="hidden" name="productId" value="${product.product.id}">
                        <input type="hidden" name="amount" value="${product.productAmount}">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                        </#if>
                        <button>Order</button>
                    </form>

                </div>
            </#list>
        </div>
        

    </div>


    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="js/cart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
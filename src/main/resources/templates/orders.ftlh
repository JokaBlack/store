<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <title>Orders</title>
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
            <#list orders as order>
                <div class="card mx-3 my-3 col" style="max-width: 300px;">
                    <img src="/${order.product.img}">
                    <p>Order date: ${order.dateTime}</p>
                    <p>Product quantity: ${order.amount}</p>
                    <p>Total sum: ${order.totalSum} $</p>
                </div>
            </#list>
        </div>
        

    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
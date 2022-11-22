<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <title>OnlineStore</title>
    <link rel = "icon" href = "/images/cart-icon-10.png" type = "image/x-icon">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Istok+Web:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container bg-secondary">
        <nav class="navbar navbar-dark bg-dark bg-gradient">
            <div class="container-fluid">
                <button onclick="onlineStoreBtn()" class="navbar-brand bg-transparent border-0">OnlineStore</button>
                <form id="search-form">
                    <div class="input-group mb-3">
                        <input id="input" required = true name="searchText" type="text" class="form-control" placeholder="Enter"
                               aria-label="Enter"
                               aria-describedby="button-addon2">
                        <button class="btn btn-outline-primary" type="submit" id="search-btn">Search</button>
                    </div>
                </form>
                <div class="text-end">
                <i class="bi bi-person text-light fs-4 me-3"></i>
                <i class="bi bi-cart3 text-light fs-4"></i>
            </div>
            </div>
        </nav>

        <div class="d-flex" id="product-box">

        </div>
        <div>
            <button onclick="prev()" id="prev-btn">prev</button>
            <button onclick="next()" id="next-btn">next</button>
        </div>

    </div>




    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="js/mainPage.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
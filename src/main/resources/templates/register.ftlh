<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <title>RegisterPage</title>
    <link rel="icon" href="/images/cart-icon-10.png" type="image/x-icon">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Istok+Web:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container">

       <form action="/user/reg" method="post" style="max-width: 300px;">
           <div class="mb-3 mt-5">
               <#if error != "">
                   <div class="alert alert-danger" >
                       ${error}
                   </div>
               </#if>
               <label for="exampleInputEmail1" class="form-label">Email address</label>
               <input required = true type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
           </div>
           <div class="mb-3">
               <label for="nickName" class="form-label">Nickname</label>
               <input required = true type="text" name="nickName" class="form-control" id="nickName">
           </div>
           <div class="mb-3">
               <label for="exampleInputPassword1" class="form-label">Password</label>
               <input required = true type="password" name="password" class="form-control" id="exampleInputPassword1">
           </div>
           <#if _csrf??>
               <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
           </#if>
           <button type="submit" class="btn btn-primary">Register</button>
       </form>
        


        

    </div>





    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
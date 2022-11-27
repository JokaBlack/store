<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel = "icon" href = "/images/cart-icon-10.png" type = "image/x-icon">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Istok+Web:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container">
       <form action="/login" method="post" style="max-width: 300px;">
           <div class="mb-3 mt-5">
               <#if error == true>
               <div class="alert alert-error" style="color: red">
                   Invalid username or password.
               </div>
               </#if>
               <#if name != "">
                   <div class="alert alert-error" style="color: green">
                       Hello ${name}, please sign in!
                   </div>
               </#if>
               <label for="emailInput" class="form-label">Email address</label>
               <input required = true type="email" name="username" class="form-control" id="emailInput" aria-describedby="emailHelp">
           </div>
           <div class="mb-3">
               <label for="passInput" class="form-label">Password</label>
               <input required = true type="password" name="password" class="form-control" id="passInput">
               <#if _csrf??>
               <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
               </#if>
           </div>
           <button type="submit" class="btn btn-primary">Submit</button>
           <a href="http://localhost:8080/register" class="btn btn-secondary">Register</a>
       </form>
        


        

    </div>





    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
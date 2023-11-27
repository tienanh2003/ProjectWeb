<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css">
    <link href="css/stylelogin.css" rel="stylesheet">
</head>
<body>
        <section class="vh-100">
      <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-md-9 col-lg-6 col-xl-5">
            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
              class="img-fluid" alt="Sample image">
          </div>
          <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                    <form action="resetPassword" method="post">
                        <div class="form-outline mb-4">
                            <input type="email" id="email" name="email" class="form-control form-control-lg" placeholder="Enter a valid email" value="<%= (session.getAttribute("email") != null) ? session.getAttribute("email") : "" %>" />
                        </div>
                        <div class="text-center text-lg-start mt-4 pt-2">
                            <button type="submit" class="btn btn-primary btn-lg" name="action" value="sendCode">Send OTP</button>
                        </div>

                        <% if (session.getAttribute("codeSent") != null && session.getAttribute("codeConfirmed") == null) { %>
                        <!-- Form for confirming the code -->
                        <div class="form-outline mb-3">
                            <input type="text" id="otp" name="otp" class="form-control form-control-lg" placeholder="Confirm OTP" />
                        </div>
                        <div class="text-center text-lg-start mt-4 pt-2">
                            <button type="submit" class="btn btn-primary btn-lg" name="action" value="confirmCode">Confirm OTP</button>
                        </div>
                        <% } %>

                        <% if (session.getAttribute("codeConfirmed") != null) { %>
                        <!-- Form for resetting the password -->
                        <div class="form-outline mb-3">
                            <input type="password" id="newpassword" name="newpassword" class="form-control form-control-lg" placeholder="Enter New Password" />
                        </div>
                        <div class="text-center text-lg-start mt-4 pt-2">
                            <button type="submit" class="btn btn-primary btn-lg" name="action" value="confirmNewPassword" onclick="return validateForm()">Confirm New Password</button>
                            <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="${pageContext.request.contextPath}/login" class="link-danger">Login</a></p>
                        </div>
                        <% } %>
                    </form>

          </div>
        </div>
      </div>
      <div
        class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary">
        <!-- Copyright -->
        <div class="text-white mb-3 mb-md-0">
          Copyright © 2020. All rights reserved.
        </div>
      </div>
    </section>
    <script>
  function validateForm() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("newpassword").value;
    var otp = document.getElementById("otp").value;


    // Validate Email
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      alert("Please enter a valid email address");
      return false;
    }

    // Validate UserName
    if (username === "") {
      alert("Please enter a username");
      return false;
    }

    // Validate Password
    if (password === "") {
      alert("Please enter a password");
      return false;
    }

    // Validate Confirm Password
    if (otp !== "") {
      alert("Please enter a otp");
      return false;
    }

    return true;
  }
</script>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abatuitc
  Date: 19.02.2020
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
    <title>Registration Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="static/registerPage.css">
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<div class="signup-form">
    <form action="RegisterServlet" method="post">
        <h2>Register</h2>
        <p class="hint-text">Create your account. It's free and only takes a minute.</p>
        <div class="form-group">
            <div class="row">
                <div class="col-xs-6"><input type="text" class="form-control" name="name" placeholder="First Name" required="required"></div>
                <div class="col-xs-6"><input type="text" class="form-control" name="surname" placeholder="Last Name" required="required"></div>
            </div>
        </div>
        <div class="form-group">
            <input type="email" class="form-control" name="email" placeholder="Email" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password" placeholder="Password" required="required">
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm Password" required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-success btn-lg btn-block">Register Now</button>
        </div>
        <c:if test="${invalidCredential}">
            <div class="text-danger">
                An account with this email address is already registered .</div>
        </c:if>
        <c:if test="${unconfirmedPassword}">
            <div class="text-danger">
                A password doesn't match .</div>
        </c:if>
    </form>
    <div class="text-center">Already have an account? <a href="${pageContext.request.contextPath}/loginPage">Sign in</a></div>
</div>
</body>
</html>

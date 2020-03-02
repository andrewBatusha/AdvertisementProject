<%--
  Created by IntelliJ IDEA.
  User: abatuitc
  Date: 27.02.2020
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmed</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<div class="container col-xs-3 col-xs-offset-4 was-validated">
    <h2>Confirm your registration</h2>
    <form action="ConfirmedPage" method="post">
        <button type="submit" class="btn btn-success">
            <i class="fas fa-check"></i> Success
        </button>
    </form>
</div>
</body>
</html>

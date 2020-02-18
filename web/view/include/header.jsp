<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Main page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container">
<%--        <div class="navbar-header">--%>
<%--          //  <a class="navbar-brand" href="#"> ${sessionScope.user} </a>--%>
<%--        </div>--%>
        <ul class="nav navbar-nav" style="display: inline-block;" >
            <li class="active"><a href="${pageContext.request.contextPath}/advertisement">Home</a></li>
            <li><a href="#">page 1</a></li>
            <li><a href="#">page 2</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right" style="display: inline-block;">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <c:choose>
                <c:when test = "${!empty sessionScope.user}">
                    <li><a href="${pageContext.request.contextPath}/LogoutServlet"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/loginPage"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
</body>
</html>
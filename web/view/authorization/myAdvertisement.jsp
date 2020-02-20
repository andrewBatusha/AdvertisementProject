<%@ page import="enums.Status" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main</title>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<div class="jumbotron d-flex align-items-center ">
    <div class="container-fluid">
        <c:set var="Approved" value="<%=Status.APPROVED%>"/>
        <c:set var="Waiting" value="<%=Status.WAITING%>"/>
        <c:set var="Denied" value="<%=Status.DENIED%>"/>
        <c:set var="count" value="0" scope="page"/>
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>HeadLine</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="adv" items="${advertisements}">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <c:choose>
                    <c:when test="${adv.status == Approved}">
                        <tr class="table-success">
                            <td>${count}</td>
                            <td><c:out value="${adv.headline}"/></td>
                            <td><c:out value="${adv.status}"/></td>
                        </tr>
                    </c:when>
                    <c:when test="${adv.status == Waiting}">
                        <tr class="table-warning">
                            <td>${count}</td>
                            <td><c:out value="${adv.headline}"/></td>
                            <td><c:out value="${adv.status}"/></td>
                        </tr>
                    </c:when>
                    <c:when test="${adv.status == Denied}">
                        <tr class="table-danger">
                            <td>${count}</td>
                            <td><c:out value="${adv.headline}"/></td>
                            <td><c:out value="${adv.status}"/></td>
                        </tr>
                    </c:when>
                </c:choose>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
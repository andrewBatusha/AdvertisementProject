<%@ page import="enums.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPage</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<div class="jumbotron d-flex align-items-center ">
    <div class="container-fluid">
        <c:set var="count" value="0" scope="page"/>
        <c:set var="User" value="<%=Role.USER%>"/>
        <c:set var="Manager" value="<%=Role.MANAGER%>"/>
        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Setting</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <c:choose>
                    <c:when test="${user.role == User}">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr class="table-success">
                            <td>${count}</td>
                            <td><c:out value="${user.name}   ${user.surname}"/></td>
                            <td><c:out value="${user.email}"/></td>
                            <td><c:out value="${user.role}"/></td>
                            <td>
                                <form action="adminServlet" method="post">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <c:if test = "${user.banStatus}">
                                        <button type="submit" class="btn btn-primary" name="button" value="unban">Unban</button>
                                    </c:if>
                                    <c:if test = "${!user.banStatus}">
                                        <button type="submit" class="btn btn-danger" name="button" value="ban">Ban</button>
                                    </c:if>
                                    <button type="submit" class="btn btn-success" name="button" value="promote">Promote
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${user.role == Manager}">
                        <c:set var="count" value="${count + 1}" scope="page"/>
                        <tr class="table-warning">
                            <td>${count}</td>
                            <td><c:out value="${user.name}   ${user.surname}"/></td>
                            <td><c:out value="${user.email}"/></td>
                            <td><c:out value="${user.role}"/></td>
                            <td>
                                <form action="adminServlet" method="post">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <c:if test = "${user.banStatus}">
                                        <button type="submit" class="btn btn-primary" name="button" value="unban">Unban</button>
                                    </c:if>
                                    <c:if test = "${!user.banStatus}">
                                        <button type="submit" class="btn btn-danger" name="button" value="ban">Ban</button>
                                    </c:if>
                                    <button type="submit" class="btn btn-info" name="button" value="demote">Demote
                                    </button>
                                </form>
                            </td>
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

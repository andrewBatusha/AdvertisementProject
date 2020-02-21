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
        <c:forEach var="adv" items="${advertisements}" varStatus="loop" >
            <c:if test="${adv.status == Approved}">
                <div class="card text-white bg-primary mb-3" style="max-width: 40rem;">
                    <div class="card-header"><c:out value="${adv.theme}"/><p></div>
                    <div class="card-body">
                        <h4 class="card-title"><c:out value="${adv.headline}"/></h4>
                        <p class="card-text"><c:out value="${adv.description}"/></p>
                    </div>
                    <div class="card-footer"><c:out value="${dateOfPublishing.get(loop.index)}"/><p></div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <c:if test="${!empty sessionScope.user}">
    <button type="button" class="btn btn-success btn-lg pull-right"><a href="${pageContext.request.contextPath}/CreatingPage"><span
            class="fas fa-clipboard-list"></span> add Advertisement</a></button>
    </c:if>
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

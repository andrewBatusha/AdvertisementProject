<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abatuitc
  Date: 17.02.2020
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success Page</title>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<h3>Your advs</h3>
<div class="container-fluid">
    <c:forEach var="adv" items="${advertisements}">
    <c:if test="${adv.visibility == true}">
    <div class="card bg-info text-white">
        <div class="card-body text-center">
            <h4 class="card-title"><c:out value="${adv.headline}"/><p></h4>
            <div class="card-text"><c:out value="${adv.description}"/><p></div>
        </div>
        <div class="card-footer"><c:out value="${adv.dateOfPublished}"/><p></div>
        </c:if>
        </c:forEach>
    </div>
</div>
</body>
</html>

 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main</title>

</head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
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
<jsp:include page="../include/_footer.jsp"></jsp:include>
</body>
</html>
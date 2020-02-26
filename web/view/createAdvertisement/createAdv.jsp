<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="enums.Status" %>
<%@ page import="enums.Theme" %><%--
  Created by IntelliJ IDEA.
  User: abatuitc
  Date: 20.02.2020
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateAdvertisement</title>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<div class="container">
    <h2>Creating Advertisement</h2>
    <form action="CreateServlet" method="post">
        <div class="form-group">
            <label for="headline">Headline:</label>
            <input type="text" class="form-control" id="headline" name="headline">
        </div>
        <div class="form-group">
            <label for="comment">Description:</label>
            <textarea class="form-control" rows="5" id="comment" name="description"></textarea>
        </div>
        <div class="form-group">
        <label for="sel1">Choose Theme:</label>
        <select class="form-control" id="sel1" name="statusList">
            <c:forEach var="theme" items="<%=Theme.values()%>">
                <option><c:out value="${theme}"/></option>
            </c:forEach>
        </select>
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input class="form-control" id="phone" type="tel"  name="phone">
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>

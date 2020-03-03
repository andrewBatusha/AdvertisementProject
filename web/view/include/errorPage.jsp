<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>404</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/errorStyle.css"/>
</head>
<body>
<div id="clouds">
    <div class="cloud x1"></div>
    <div class="cloud x1_5"></div>
    <div class="cloud x2"></div>
    <div class="cloud x3"></div>
    <div class="cloud x4"></div>
    <div class="cloud x5"></div>
</div>
<div class='c'>
    <div class='_404'>404</div>
    <hr>
    <div class='_1'>OOPS!</div>
    <div class='_2'>You werent supposed to be able to get here you know</div>
    <a class='btn' href="${pageContext.request.contextPath}/advertisement">GO BACK HOME</a>
</div>


</body>
</html>
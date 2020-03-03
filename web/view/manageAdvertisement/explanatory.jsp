<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Explanatory</title>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>

<div class="container" class="was-validated">
    <h2>Explanatory</h2>
    <form action="explanatoryServlet" method="post">
        <div class="form-group">
            <label for="formGroupExampleInput"> Reason </label>
            <input type="text" class="form-control" id="formGroupExampleInput" name="reason"
                   placeholder="Example input">
        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1"> Details </label>
            <textarea class="form-control" id="exampleFormControlTextarea1" name="details" rows="3"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>

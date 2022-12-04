<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product Pages</title>
</head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<body>

<div class="container" style="margin-top: 20px;">
    <div class="list-group">
        <c:forEach var="product" items="${products}">
            <a href="product-detail?name=${product}" class="list-group-item list-group-item-action">${product}</a>
        </c:forEach>
    </div>
</div>

</body>
</html>

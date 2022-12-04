<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Hello Spring MVC</title>
</head>
<body>
<h1>PRODUCT LIST</h1>
<ul>
    <c:forEach var="product" items="${products}">
        <li>${product}</li>
    </c:forEach>
</ul>
<p>message: ${greeting}</p>
<a href="product">Click To Product List</a>
</body>
</html>

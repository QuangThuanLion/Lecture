<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Product List</title>
</head>
<body>
<h1>PRODUCT LIST FROM DATABASE</h1>
<a href="create-product">Create Product</a>
<ul>
  <c:forEach var="product" items="${products}">
    <li>${product.id}</li>
    <li>${product.name}</li>
    <li>${product.price}</li>
    <li>${product.description}</li>
    <li>${product.createdTime}</li>
    <li>${product.status}</li>
    <li>${product.categoryId}</li>
    <li><a href="product-detail/${product.id}">Click me to Product Detail</a></li>
    </br>
  </c:forEach>
</ul>
</body>
</html>

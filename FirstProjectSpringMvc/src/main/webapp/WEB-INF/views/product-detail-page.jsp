<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Product Detail Page</title>
</head>
<body>
<h1>PRODUCT DETAIL</h1>
<ul>
    <li>${productDTO.id}</li>
    <li>${productDTO.name}</li>
    <li>${productDTO.price}</li>
    <li>${productDTO.description}</li>
    <li>${productDTO.createdTime}</li>
    <li>${productDTO.status}</li>
    <li>${productDTO.categoryId}</li>
    </br>
</ul>
</body>
</html>
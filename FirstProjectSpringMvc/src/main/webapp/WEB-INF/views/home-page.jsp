<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home Page</title>
</head>
<body>
<h1>${message}</h1>
<ul>
    <c:forEach var="product" items="${products}">
        <li>${product}</li>
    </c:forEach>
</ul>
</body>
</html>

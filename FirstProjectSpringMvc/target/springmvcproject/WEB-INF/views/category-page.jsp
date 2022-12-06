<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Category List</title>
</head>
<body>
<h1>CATEGORY LIST FROM DATABASE</h1>
<ul>
  <c:forEach var="category" items="${categories}">
    <li>${category.id}</li>
    <li>${category.name}</li>
    <li>${category.description}</li>
    </br>
  </c:forEach>
</ul>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>List Categories</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>
<div class="container" style="margin-top: 20px; margin-bottom: 20px;">
    <div style="display: flex; align-items: center; justify-content: space-between">
        <h2>CATEGORIES LIST</h2>
    </div>
    <a type="button" class="btn btn-info" href="create-category" style="margin-bottom: 10px;">CREATE CATEGORY</a>
    <div>
        <a href="products">Click To Return Home Page</a>
    </div>
    <c:if test="${message != null}">
        <div class="alert alert-${alert}" role="alert">
                ${message}
        </div>
    </c:if>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Update</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}">
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td>${category.description}</td>
            <td><a type="button" class="btn btn-primary btn-sm" href="category/${category.id}/update">Update</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
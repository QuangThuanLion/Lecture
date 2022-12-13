<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>Add Category</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<style>
    .error {
        color: red; font-weight: bold;
    }
</style>
<body>
<div class="container">
    <h2>Create Category Form</h2>
    <form:form ction="create-category" method="post" modelAttribute="categoryDTO"
          enctype="multipart/form-data"
          onsubmit="return dialogConfirmationCreateCategory()">
        <div class="form-group">
            <label for="name">Category Name:</label>
            <form:input type="text" class="form-control" path="name"
                        placeholder="Enter Category Name"/>
            <span><form:errors cssClass="error" path="name"/></span>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <form:textarea path="description" rows="7" class="form-control"
                           placeholder="Enter Description"/>
            <span><form:errors cssClass="error" path="description"/></span>
        </div>
        <div class="row">
            <div class="form-group col-sm-12">
                <b><span>Category Images</span></b>
                <input type="file" name="category_image" class="form-control">
            </div>
        </div>
        <button onclick="checkCreateConfirmation()" class="btn btn-default">Create</button>
    </form:form>
    <a href="products">Click To Return Home Page</a>
</div>
</body>
</html>
<script>
    let text = "Do you want to create category ?";
    let checkMessage = ""

    function checkCreateConfirmation() {
        if (confirm(text) == true) {
            checkMessage = "OK";
        } else {
            checkMessage = "CANCEL";
        }
    }

    function dialogConfirmationCreateCategory() {
        if (checkMessage === "OK") {
            return true;
        } else {
            return false;
        }
    }
</script>

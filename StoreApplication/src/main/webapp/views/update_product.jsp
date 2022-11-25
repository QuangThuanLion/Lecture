<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Update Product</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Update Product Form</h2>
    <form action="add-product-servlet" method="post" onsubmit="return dialogConfirmationUpdateProduct()">
        <div class="form-group">
            <label for="product_id">ProductId:</label>
            <input type="text" class="form-control" id="product_id"
                   name="product_id" value="${product.id}" readonly="true">
        </div>
        <div class="form-group">
            <label for="product_name">Product Name:</label>
            <input type="text" class="form-control" id="product_name" placeholder="Enter Product Name"
                   name="product_name" value="${product.name}" required>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" class="form-control" id="price" placeholder="Enter Price"
                   name="price" value="${product.price}" required>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control" id="description" placeholder="Enter Description"
                      name="description">${product.description}</textarea>
        </div>
        <div class="form-group">
            <label for="createdTime">CreatedTime:</label>
            <input class="form-control" id="createdTime" placeholder="Enter Description"
                      name="createdTime" type="date" value="${createdTime}"/>
        </div>
        <div class="form-group">
            <label>Status:</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="true" name="status" id="available"
                ${product.status == true ? 'checked' : ''}>
                <label class="form-check-label" for="available">
                    Available
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" value="false" name="status" id="not_available"
                ${product.status == false ? 'checked' : ''}>
                <label class="form-check-label" for="not_available">
                    Not Available
                </label>
            </div>
        </div>
        <div class="form-group">
            <label for="category" class="form-label">Select Category</label>
            <select class="form-select" id="category" name="category">
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}"
                            ${(category.id == product.category.id) ? 'selected' : ''}>
                            ${category.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="color" class="form-label">Select Color</label>
            <select class="form-select" id="color" name="color">
                <option value="success" ${product.active == 'success' ? 'selected' : ''}>success</option>
                <option value="danger" ${product.active == 'danger' ? 'selected' : ''}>danger</option>
                <option value="info" ${product.active == 'info' ? 'selected' : ''}>info</option>
                <option value="warning" ${product.active == 'warning' ? 'selected' : ''}>warning</option>
                <option value="active" ${product.active == 'active' ? 'selected' : ''}>active</option>
            </select>
        </div>

        <button onclick="checkUpdateConfirmation()" class="btn btn-default">Update</button>
    </form>
</div>
</body>
</html>
<script>

    let text = "Do you want to Update product ?";
    let checkMessage = ""
    function checkUpdateConfirmation() {
        if (confirm(text) == true) {
            checkMessage = "OK";
        } else {
            checkMessage = "CANCEL";
        }
    }

    function dialogConfirmationUpdateProduct() {
        if (checkMessage === "OK") {
            return true;
        } else {
            return false;
        }
    }
</script>

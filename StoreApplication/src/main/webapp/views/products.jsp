<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
   <div style="display: flex; align-items: center; justify-content: space-between">
       <h2>PRODUCT LIST</h2>
       <div>
           <a type="button" class="btn btn-warning" href="add-product-servlet">LOGOUT</a>
       </div>
   </div>
    <form action="products-servlet" method="post">
        <div class="container mt-3">
            <div class="input-group mb-3" style="display: flex">
                <input type="text" class="form-control" placeholder="Search" name="keyword" required>
                <div class="input-group-append">
                    <button class="btn btn-success" type="submit">Search</button>
                </div>
            </div>
        </div>
    </form>
    <a type="button" class="btn btn-info" href="add-product-servlet">CREATE PRODUCT</a>
    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>CreatedTime</th>
            <th>UpdatedTime</th>
            <th>Status</th>
            <th>Category</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr class="${product.active}">
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                    <td>${product.createdTime}</td>
                    <td>${product.updatedTime}</td>
                    <td>${product.status}</td>
                    <td>${product.categoryName}</td>

                    <td><a type="button" class="btn btn-primary btn-sm" href="add-product-servlet?productId=${product.id}">Update</a></td>
                    <td><button class="btn btn-danger btn-sm" onclick="confirmDeleteDialog(${product.id})">Delete</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
<script>
    let text = "Do you want to delete product ?";
    function confirmDeleteDialog(productId) {
        if (confirm(text) == true) {
            window.location.href="/StoreApplication/delete-product-servlet?product_id="+productId;
        }
    }
</script>

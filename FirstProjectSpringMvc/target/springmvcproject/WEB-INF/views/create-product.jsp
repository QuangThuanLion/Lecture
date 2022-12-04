<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Product Create</title>
</head>
<body>
<div style="margin-left: 100px; margin-top: 50px;">
    <h1>PRODUCT CREATE</h1>
    <div>
        <form action="create-product" method="post">
            <label>Product Name: <input type="text" name="name" placeholder="product name"></label>
            <br/><br/>
            <label>Product Price: <input type="number" name="price" placeholder="product price"></label>
            <br/><br/>
            <label>Product description: <textarea name="description" placeholder="product description"></textarea></label>
            <br/><br/>
            <label>Product Status:</label>
            <label>Available: <input type="radio" name="status"></label>
            <label>Not Available: <input type="radio" name="status"></label>
            <br/><br/>
            <label>Categories:
                <select name="category">
                    <c:forEach var="categoryId" items="${categories}">
                        <option value="${categoryId.id}">${categoryId.name}</option>
                    </c:forEach>
                </select>
            </label>
            <br/><br/>
            <button type="submit">Create Product</button>
        </form>
    </div>
</div>
</body>
</html>
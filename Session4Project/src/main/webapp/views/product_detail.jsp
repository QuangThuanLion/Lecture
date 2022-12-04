<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product-Detail</title>
</head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

<body>

<div class="container" style="margin-top: 20px;">
    <form action="product-detail" method="post">
        <input type="text" name="product_update" value="${product}" class="form-control"><br/>
        <input type="hidden" name="product_root" value="${product}" class="form-control"><br/>
        <button type="submit">Update Product</button>
    </form>
</div>

</body>
</html>

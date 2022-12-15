<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Add Product</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h2>Create Product Form</h2>
  <form action="create-product" method="post"
        onsubmit="return dialogConfirmationCreateProduct()">
    <div class="form-group">
      <label for="product_name">Product Name:</label>
      <input type="text" class="form-control" id="product_name" placeholder="Enter Product Name"
             name="name" required>
    </div>
    <div class="form-group">
      <label for="price">Price:</label>
      <input type="number" class="form-control" id="price" placeholder="Enter Price" name="price"
             required>
    </div>
    <div class="form-group">
      <label for="description">Description:</label>
      <textarea class="form-control" id="description" placeholder="Enter Description"
                name="description">
      </textarea>
    </div>
    <div class="form-group">
      <label>Status:</label>
      <div class="form-check">
        <input class="form-check-input" type="radio" value="true" name="status" id="available" checked>
        <label class="form-check-label" for="available">
          Available
        </label>
      </div>
      <div class="form-check">
        <input class="form-check-input" type="radio" value="false" name="status" id="not_available">
        <label class="form-check-label" for="not_available">
          Not Available
        </label>
      </div>
    </div>
    <div class="form-group">
      <label for="categoryId" class="form-label">Select Category</label>
      <select class="form-select" id="categoryId" name="categoryId">
        <c:forEach var="category" items="${categories}">
          <option value="${category.id}">${category.name}</option>
        </c:forEach>
      </select>
    </div>
    <button onclick="checkCreateConfirmation()" class="btn btn-default">Create</button>
    <input class="btn btn-default" onclick="onClickAddProductAjax()" value="Click me to add Product Ajax" />
  </form>
</div>
</body>
</html>
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

  let text = "Do you want to create product ?";
  let checkMessage = ""
  function checkCreateConfirmation() {
    if (confirm(text) == true) {
      checkMessage = "OK";
    } else {
      checkMessage = "CANCEL";
    }
  }

  function dialogConfirmationCreateProduct() {
    if (checkMessage === "OK") {
      return true;
    } else {
      return false;
    }
  }

  function onClickAddProductAjax() {
    console.log('onClickAddProductAjax')
    var productName = document.getElementById("product_name").value;
    var productPrice = document.getElementById("price").value;
    var productDescription = document.getElementById("description").value;
    var available = document.getElementById("available").value;
    var not_available = document.getElementById("not_available").value;
    var productCategory = document.getElementById("categoryId").value;
    var payload = {
      "name" : productName,
      "price": productPrice,
      "description" : productDescription,
      "status" : available != null ? available : not_available,
      "categoryId": productCategory
    }
    $.ajax({
      url: '/springmvcproject/api/create-product/rest-api',
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(payload),
      success: function(result) {
        console.log(result)
        // if(result.success) {
        //   var data = result.data;
        //   var listMedicalHistory = data[0];
        //   var listAppointmentStatus = data[1];
        //   getListMedicalHistory(listMedicalHistory, listAppointmentStatus);
        // }
      },
      error: function(error) {
        if(!error.success) {
          console.log("ERROR");
          // window.location.href='/admin/medical-history';
        }
      },
      done: function(e) {
        console.log("e",e);
        console.log("DONE");
        // window.location.href='/admin/medical-history'
      }
    })
  }
</script>

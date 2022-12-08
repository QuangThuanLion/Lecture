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
  <form:form action="${context}/update-category" method="post" modelAttribute="categoryDTO"
             onsubmit="return dialogConfirmationCreateCagegory()">
    <div class="form-group">
      <c:if test="${not empty categoryDTO.id}">
        <label for="id">Category Id:</label>
        <form:input type="text" class="form-control" path="id"
                    readonly="true" value="${categoryDTO.id}"/>
      </c:if>
      <label for="name">Category Name:</label>
      <form:input type="text" class="form-control" path="name" placeholder="Enter Category Name"
                  value="${categoryDTO.name}" />
      <span><form:errors cssClass="error" path="name"/></span>
    </div>
    <div class="form-group">
      <label for="description">Description:</label>
      <form:textarea path="description" rows="7" class="form-control"
                     placeholder="Enter Description" value="${categoryDTO.description}"/>
      <span><form:errors cssClass="error" path="description"/></span>
    </div>
    <c:if test="${not empty categoryDTO.id}">
      <button onclick="checkCreateConfirmation(${categoryDTO.id})" class="btn btn-default">Update</button>
    </c:if>
    <c:if test="${empty categoryDTO.id}">
      <button onclick="checkCreateConfirmation()" class="btn btn-default">Create</button>
    </c:if>
  </form:form>
  <a href="${context}/products">Click To Return Home Page</a>
</div>
</body>
</html>
<script>
  let text = "Do you want to create category ?";
  let checkMessage = ""

  function checkCreateConfirmation(categoryId) {
    if (categoryId !== null && categoryId !== undefined) {
      text = "Do you want to update category ?"
    }
    if (confirm(text) == true) {
      checkMessage = "OK";
    } else {
      checkMessage = "CANCEL";
    }
  }

  function dialogConfirmationCreateCagegory() {
    if (checkMessage === "OK") {
      return true;
    } else {
      return false;
    }
  }
</script>

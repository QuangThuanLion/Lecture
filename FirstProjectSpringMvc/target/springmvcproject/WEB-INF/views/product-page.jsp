<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>List Product</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>
<div class="container" style="margin-top: 20px;">
    <div style="display: flex; align-items: center; justify-content: space-between">
        <h2><spring:message code="layout.product.title"/></h2>
        <div>
            <a type="button" class="btn btn-warning" href="logout-controller">
                <spring:message code="layout.logout.link"/></a>
        </div>
        <div>
            <a href="?language=en">English</a>
            <a href="?language=vi">Viet Nam</a>
        </div>
    </div>
    <a href="categories"><spring:message code="layout.category.link"/></a>
    <form action="products-search" method="post">
        <div class="container mt-3">
            <div class="input-group mb-3" style="display: flex">
                <input type="text" class="form-control" placeholder="Search" name="keyword" required>
                <div class="input-group-append">
                    <button class="btn btn-success" type="submit">
                        <spring:message code="layout.search.link"/></button>
                </div>
            </div>
        </div>
    </form>
    <a type="button" class="btn btn-info" href="create-product" style="margin-bottom: 10px;">
        <spring:message code="layout.product.create"/></a>
    <c:if test="${message != null}">
        <div class="alert alert-${alert}" role="alert">
                ${message}
        </div>
    </c:if>
    <table class="table">
        <thead>
        <tr>
            <th><spring:message code="layout.product.id"/></th>
            <th><spring:message code="layout.product.name"/></th>
            <th><spring:message code="layout.product.price"/></th>
            <th><spring:message code="layout.product.description"/></th>
            <th><spring:message code="layout.product.createTime"/></th>
            <th><spring:message code="layout.product.status"/></th>
            <th><spring:message code="layout.product.category"/></th>
            <th><spring:message code="layout.product.update"/></th>
            <th><spring:message code="layout.product.delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <td><f:formatDate value="${product.createdTime}" pattern="yyyy-MM-dd"/></td>
            <c:choose>
                <c:when test="${product.status}">
                    <td>available</td>
                </c:when>
                <c:otherwise>
                    <td>not available</td>
                </c:otherwise>
            </c:choose>
            <td>${product.categoryName}</td>
            <td><a type="button" class="btn btn-primary btn-sm" href="product/${product.id}/update">
                <spring:message code="layout.product.update"/>
            </a></td>
            <td>
                <button class="btn btn-danger btn-sm" onclick="confirmDeleteDialog(${product.id})">
                    <spring:message code="layout.product.delete"/>
                </button>
            </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"/>
<script type="text/javascript">
    let text = "Do you want to delete product ?";
    function confirmDeleteDialog(productId) {
        if (confirm(text) == true) {
            window.location.href = "${context}/delete-product?product_id=" + productId;
        }
    }
</script>

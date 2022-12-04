<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<%--%>
<%--    List<String> products = (List<String>) request.getAttribute("products");--%>
<%--%>--%>

<%--<h1>PRODUCT LIST</h1>--%>
<%--<ul>--%>
<%--    <%--%>
<%--        for (String product : products) {--%>

<%--    %>--%>
<%--    <li><%= product%>--%>
<%--    </li>--%>
<%--    <%--%>
<%--        }--%>
<%--    %>--%>
<%--</ul>--%>

<ul>
    <p>Su dung JSTL</p>
    <c:forEach var="product" items="${products}">
        <li>${product}</li>
    </c:forEach>
</ul>

<p>${confirm}</p>
</body>
</html>

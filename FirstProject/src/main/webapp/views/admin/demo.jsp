<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<%
    int count = 10;
%>

<%!
    int countUp = 1;
%>

<%
    List<String> products = new ArrayList<>();
    products.add("Apple");
    products.add("Orange");
    products.add("Durian");
    products.add("Cucumber");
%>

<%--<body>--%>
<h1> DEMO SERVLET PAGE JSP UPDATE</h1>
<p><%= request.getContextPath()%>
</p>

<p>
    Page count is: <% out.println(++countUp); %>
</p>

<p></p>
<p>PRODUCT LIST</p>

<ul>
    <%
        for (String product : products) {
    %>
    <li><%= product %>
    </li>
    <%
        }
    %>
</ul>
</body>
</html>

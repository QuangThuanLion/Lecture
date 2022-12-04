<%--Khai bao một số phụ thuộc vào trang--%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Home Page</title>
</head>
<%--Nội dung cần ghi chú ở đây--%>

<%--Code java--%>
<%
    int count = 0;
    String name = "Home Page";
%>

<%--Khai bao bien va phuong thuc--%>
<%!
    int countFor = 10;
%>

<%--Code java--%>
<%
    List<String> products = new ArrayList<>();
    products.add("Apple");
    products.add("Orange");
    products.add("Durian");
    products.add("Cucumber");
%>

<%!
    int countViews = 10;
    int getCount() {
        System.out.println("In getCount() method");
        return countViews;
    }
%>

<body>
<h1>Hello JSP Servlet Update</h1>
Page count is: <% out.println(++count); %>
Increase Count:
<%
    for (int i = 0; i < countFor; i++) {
%>
<li><%= i + 1 %>
</li>
<%
    }
%>
<br/>
<p>Products List</p>
<%
    for (String item : products) {
%>
<li><%= item%></li>
<%
    }
%>

<%
    for (String product : products) {
        System.out.println(product);
    }
%>

<%--Expression <%= Expression%>--%>
<p>Get Count Views: <%= getCount()%></p>
This is my page <%= "Hello World Is My Lap !" %>
</body>
</html>

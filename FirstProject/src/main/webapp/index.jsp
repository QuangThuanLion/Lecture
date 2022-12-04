<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "LOGIN PAGE" %></h1>
<% String contextPath = request.getContextPath(); %>
<br/>

<%
    String message = "";
    if (request.getAttribute("message") != null) {
        message = request.getAttribute("message").toString();
    }
%>
<p><%= message%></p>
<a href="home-jsp-controller">Click Here To home-jsp-controller</a>
<br/>
<a href="demo-jsp-controller">Click Here To demo-jsp-controller</a>

<form action="<%= contextPath%>/login-servlet" method="post">
    <div class="container">
        <label>Username : </label>
        <input type="text" placeholder="Enter Username" name="username" required>
        <label>Password : </label>
        <input type="password" placeholder="Enter Password" name="password" required>
        <button type="submit">Login</button>
    </div>
</form>
</body>
</html>
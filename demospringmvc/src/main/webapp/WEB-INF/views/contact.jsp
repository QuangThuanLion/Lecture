<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Contact</title>
</head>
<body>
<h1>PRODUCT LIST</h1>
<p>Contact: ${contact}</p>
<a href="form-submit">Click me !</a>
<form action="form-submit" method="post">
    <label> student firstname: </label> <input type="text" name="firstname">
    <label> student lastname: </label> <input type="text" name="lastname">
    <input type="submit" value="submit form Post">
</form>
<form action="form-submit" method="put">
    <input type="submit" value="submit form Put">
</form>
<form action="form-submit" method="delete">
    <input type="submit" value="submit form Put">
</form>
</body>
</html>

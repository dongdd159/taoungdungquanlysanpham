<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 5/25/2021
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new customer</title>
    <style>
        .message{
            color:green;
        }
    </style>
</head>
<body>
<h1>Create new product</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/products">Bach to list</a>
</p>
<form method="post">
    <fieldset>
        Name: <input type="text" name="name" id="name">
        <input type="submit" value="Create product">
    </fieldset>
</form>
</body>
</html>

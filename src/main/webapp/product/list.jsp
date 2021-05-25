<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 5/25/2021
  Time: 6:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Product List</h1>
<p><a href="/products?action=create">Create new product</a> </p>
<form action="/products?action=search" method="post">
    <input type="text"name="name" id="name" placeholder="Enter product name"> <button type="submit" value="Search" >Search</button>
</form>
<table>
    <tr>
        <td>Name</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items='${requestScope["productList"]}' var="product">
        <tr>
            <td><a href="/products?action=view&Product&id=${product.getId()}">${product.getName()}</a> </td>
            <td><a href="/products?action=edit&id=${product.getId()}">Edit</a> </td>
            <td><a href="/products?action=delete&id=${product.getId()}">Delete</a> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

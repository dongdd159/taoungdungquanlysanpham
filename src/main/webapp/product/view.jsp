<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 5/25/2021
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View product</title>
</head>
<body>
<h1>Product details</h1>
<p>
    <a href="/products">Back to list</a>
</p>
<table>
    <tr>
        <td>Name: </td>
        <td>${requestScope["product"].getName()}</td>
    </tr>
</table>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 14.07.2019
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
<form action="editProduct" method="post">
        <input name="product" type="text" value="${product.getName()}"/><br>
        <input name="description" type="text"  value="${product.getDescription()}"><br>
        <input name="price" type="text" value="${product.getPrice()}"><br>
    <button  name="edit" type="submit" value="${product.getId()}"> Change DATA </button><br>
</form>
</center>
</body>
</html>

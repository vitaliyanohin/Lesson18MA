<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 09.07.2019
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="editUser" method="post">
    Email <input name="email" type="email" value="${user.getEmail()}"/><br>
    Password <input name="pass" id="password" type="password" ><br>
    Repeat password <input name="repeatPassword" id="repeatPassword" type="password"><br>
    <input type="radio" name="role" value="user">Role: user<Br>
    <input type="radio" name="role" value="admin"> Role: admin<Br>
    <button  name="edit" type="submit" value="${user.getId()}"> Change DATA </button><br>
</form>
</body>
</html>

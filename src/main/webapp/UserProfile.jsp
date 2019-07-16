<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 11.07.2019
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h2> Профиль:</h2>
    <c:out value="${info}"/>
    <a href="newProduct"> New Product </a><br>
    <a href="allProducts"> All Products </a> <br>
        <c:if  test="${user.getRole() eq 'admin'}" >
    <h3> Add NEW USER:</h3>
    <form action="register" method="post">
        Email <input name="email" type="email"/><br>
        Password <input name="pass" type="password"><br>
        Repeat password <input name="repeatPassword" type="password"><br>
        <input type="radio" name="role" value="user">Role: user<Br>
        <input type="radio" name="role" value="admin"> Role: admin<Br>
        <button type="submit"> Submit </button><br>
        <button type="submit" formaction="allUsers" formmethod="get">All Users</button><br>
    </form>
        </c:if>
</body>
</html>

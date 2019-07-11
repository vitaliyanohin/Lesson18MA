<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 03.07.2019
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<center>
    <c:out value="${info}"/>
    <form action="index" method="get">
        Email <input name="email" type="email" value="${email}"/><br>
        Password <input name="pass" id="password" type="password"><br>
        Repeat password <input name="repeatPassword" id="repeatPassword" type="password"><br>
        <button type="submit"> Sing in</button><br>
        <button type="submit" formaction="register" formmethod="post">Sing up</button><br>
    </form>
    <a href="register"> Registration </a><br>
    <a href="newProduct"> New Product </a><br>
    <a href="allUsers"> All Users </a><br>
    <a href="allProducts"> All Products </a> <br>
</center>
</body>
</html>

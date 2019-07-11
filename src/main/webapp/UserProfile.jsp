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
    <form action="UserProfile" method="get">
        Email <input name="email" type="email" value="${user.getEmail()}"/><br>
        Password <input name="pass" id="password" type="password"><br>
        Repeat password <input name="repeatPassword" id="repeatPassword" type="password"><br>
        <button type="submit"> Change DATA </button><br>
        <button type="submit" formaction="allProducts" formmethod="get">My Product</button><br>
        <button type="submit" formaction="newProduct" formmethod="get">New Product</button><br>
        <c:if  test="${user.getRole() eq 'admin'}" >
        <button type="submit" formaction="allUsers" formmethod="get">All Users</button><br>
        </c:if>
    </form>
</body>
</html>

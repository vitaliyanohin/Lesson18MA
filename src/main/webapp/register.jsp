<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 03.07.2019
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Registration </title>
</head>
<body>
<center>
    <c:out value="${info}"/>
    <form action="register" method="post">
        Email <input name="email" type="email" value="${pass}"/><br>
        Password <input name="pass" id="password" type="password"> <br>
        Repeat password <input name="repeatPassword" id="repeatPassword" type="password"><br>
        <input type="submit"><br>
    </form>
</center>
</body>
</html>

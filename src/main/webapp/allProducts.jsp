<%@ page import="factory.ProductServiceFactory" %>
<%@ page import="service.impl.ProductServiceImpl" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 05.07.2019
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="newProduct" method="post">
    <a href="newProduct"> NewProduct </a><br>
    <form action="allUsers" method="post">
        <a href="allUsers"> All Users </a><br>
    </form>
        <center>
            <h2> Список пользователей:</h2>
            <table border="1" align="center">
                <tr> <th> Email </th>
                    <th> Password </th>
                    <th> Actions </th>
                    <c:forEach var="currentProduct" items="${allProductList}">
                <tr>
                    <td> ${currentProduct.getName()}</td>
                    <td> ${currentProduct.getPrice()}</td>
                    <td> <form  action="delete" method="post" >
                        <button name="delete" type="submit" value="${currentProduct.getId()}" >Delete</button> </form>
                        <form  action="allUsers" method="post" >
                            <button  name="edit" type="submit" value="${currentProduct.getId()}" >Edit</button> </form> </td>
                </tr>
                </c:forEach>
</body>
</html>

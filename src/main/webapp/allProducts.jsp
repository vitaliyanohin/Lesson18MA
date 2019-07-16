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
        <center>
            <h2> My Products:</h2>
            <table border="1" align="center">
                <tr> <th> Name </th>
                    <th> description </th>
                    <th> Price </th>
                    <th> Actions </th>
                    <c:forEach var="currentProduct" items="${allProductList}">
                <tr>
                    <td> ${currentProduct.getName()}</td>
                    <td> ${currentProduct.getDescription()}</td>
                    <td> ${currentProduct.getPrice()}</td>
                    <td> <form  action="delete/Product" method="post" >
                        <button name="delete" type="submit" value="${currentProduct.getId()}" >Delete</button> </form>
                        <c:if  test="${user.getRole() eq 'admin'}"  >
                        <form  action="editProduct" method="get" >
                            <button  name="edit" type="submit" value="${currentProduct.getId()}" >Edit</button> </form> </td>
                </c:if>
                </tr>
                </c:forEach>
            </table>
                <center>
                    <form action="newProduct" method="post">
                        <a href="newProduct"> NewProduct </a><br>
                        <c:if  test="${user.getRole() eq 'admin'}"  >
                        <form action="allUsers" method="post">
                            <a href="allUsers"> All Users </a><br>
                        </form>
                        </c:if>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 23.07.2019
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h2> Order:</h2>
    <c:out value="${info}"/>
    <c:out value="${sessionScope.Box}"/>
    <table border="1" align="center">
        <tr> <th> Order ID </th>
            <th> address </th>
            <th> user Name </th>
            <th> product </th>
            <th> description </th>
            <th> price </th>
            <c:forEach var="myOrder" items="${myOrder}">
        <tr>
            <td> ${myOrder.getOrderId()}</td>
            <td> ${myOrder.getAddress()}</td>
            <td> ${myOrder.getUser()}</td>
            <td> ${myOrder.getProduct()}</td>
            <td> ${myOrder.getDescription()}</td>
            <td> ${myOrder.getPrice()}</td>
        </tr>
        </c:forEach>
    </table>
</body>
</html>

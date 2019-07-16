<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 16.07.2019
  Time: 0:59
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
    <h2> <c:out value="${info}"/> </h2>
<center> <h2>Your Order:</h2></center>
    <table border="1" align="center">
        <tr> <th> Name </th>
            <th> description </th>
            <th> Price </th>
            <c:forEach var="currentProduct" items="${productList}">
        <tr>
            <td> ${currentProduct.getName()}</td>
            <td> ${currentProduct.getDescription()}</td>
            <td> ${currentProduct.getPrice()}</td>
        </tr>
        </c:forEach>
    </table>
    <center><h2>Total Price: ${totalPrice} </h2></center>
        <center><h2>Confirm Order:</h2></center>
    <form action="Confirmation" method="get">
       Email <input name="email" type="email" value="${email}" ><br>
        Address <input name="address" type="text" value="${address}"><br>
        <button type="submit"> Get Confirm Code </button><br>
    </form>
<center><h2>Confirm Code:</h2></center>
    <form action="Confirmation" method="post">
        Code <input name="code" type="text" ><br>
        <button type="submit"> Confirm Code </button><br>
    </form>
</center>
</body>
</html>

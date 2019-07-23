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
            <h2> My Orders:</h2>
            <c:out value="${info}"/>
          <c:out value="${sessionScope.Box}"/>
            <table border="1" align="center">
                <tr> <th> OrderID </th>
                    <th> Actions </th>
                    <c:forEach var="currentProduct" items="${allProductList}">
                <tr>
                    <td> ${currentProduct}</td>
                    <td>
                        <form  action="myOrders" method="post" >
                            <button name="view" type="submit" value="${currentProduct}" >View Order info</button> </form>
                </tr>
                </c:forEach>
            </table>
</body>
</html>

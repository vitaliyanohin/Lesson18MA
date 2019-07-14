<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 04.07.2019
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="register" method="post">
    <a href="register"> Registration </a> <br>
    <form action="allProducts" method="post">
        <a href="allProducts"> All Product </a><br>
    </form>
    <center>
        <h2> Список пользователей:</h2>
        <table border="1" align="center">
            <tr>
                <th> Email</th>
                <th> Password</th>
                <th> Actions</th>
                <c:forEach var="currentUser" items="${allUserList}">
            <tr>
                <td> ${currentUser.getEmail()}</td>
                <td> ${currentUser.getPassword()}</td>
                <td>
                    <form action="delete/User" method="post">
                        <button name="delete" type="submit"
                                value="${currentUser.getId()}">Delete
                        </button>
                    </form>
                    <form action="editUser" method="get">
                        <button name="edit" type="submit"
                                value="${currentUser.getId()}">Edit
                        </button>
                    </form>
                </td>
            </tr>
            </c:forEach>

</body>
</html>

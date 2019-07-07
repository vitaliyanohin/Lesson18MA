<%@ page import="factory.UserDaoFactory" %>
<%@ page import="service.impl.AccountServiceImpl" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="factory.AccountServiceFactory" %>
<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 04.07.2019
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
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
<% PrintWriter printWriter = response.getWriter();
    printWriter.write("<center>");
    printWriter.write(" <h2> Список пользователей:</h2>");
    printWriter.write(" <table border=\"1\" align=\"center\">\n"
            + "<tr>" + "<th> Email </th>\n" + "<th> Password </th>\n");
    AccountServiceImpl accountService = AccountServiceFactory.AccountServiceSingleton();
    int id = accountService.size();
    while (id > 0) {
        printWriter.write("<tr>");
        printWriter.write("<td>" + accountService.getUser(id).get().getEmail() + "</td>");
        printWriter.write("<center>");
        printWriter.write("<td>" + accountService.getUser(id).get().getPassword() + "</td>");
        printWriter.write("</tr>");
        id--;
    }
%>
</body>
</html>

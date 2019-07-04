<%@ page import="factory.UserDaoFactory" %>
<%@ page import="service.AccountService" %>
<%@ page import="java.io.PrintWriter" %>
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

    <a href="register"> Registration </a>
</form>
<% PrintWriter printWriter = response.getWriter();
    printWriter.write("<center>");
    printWriter.write(" <h2> Список пользователей:</h2>");
    printWriter.write(" <table border=\"1\" align=\"center\">\n" + "<tr>" + "<th> Email </th>\n" + "<th> Password </th>\n");
    boolean valid = true;
    long id = 1L;

    AccountService accountService = UserDaoFactory.AccountServiceSingltone();
    id = accountService.count();
    while (id > 0) {
        printWriter.write("<tr>");
        printWriter.write("<td>" + accountService.getUserByLogin(id).getEmail() + "</td>");
        printWriter.write("<center>");
        printWriter.write("<td>" + accountService.getUserByLogin(id).getPassword() + "</td>");
        printWriter.write("</tr>");
        id--;
    }
%>
</body>
</html>

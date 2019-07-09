<%@ page import="factory.AccountServiceFactory" %>
<%@ page import="service.impl.AccountServiceImpl" %>
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
    <a href="register"> Registration </a> <br>
    <form action="allProducts" method="post">
        <a href="allProducts"> All Product </a><br>
    </form>
        <% PrintWriter printWriter = response.getWriter();
        printWriter.write("<center>");
        printWriter.write(" <h2> Список пользователей:</h2>");
        printWriter.write(" <table border=\"1\" align=\"center\">\n"
        + "<tr>" + "<th> Email </th>\n" + "<th> Password </th>\n" + "<th> Actions </th>\n");
    AccountServiceImpl accountService = AccountServiceFactory.getInstance();
    int id = accountService.size();
    while (id > 0) {
      printWriter.write("<tr>");
      printWriter.write("<td>" + accountService.getUserById(id).get().getEmail() + "</td>");
      printWriter.write("<center>");
      printWriter.write("<td>" + accountService.getUserById(id).get().getPassword() + "</td>");
      printWriter.write("<td>"
                          + "<form  action=\"allUsers\" method=\"post\">\n"
                          +"<input type=\"submit\"></form>"
                          +"<form  action=\"allProducts\" method=\"post\">\n"
                          +"<input type=\"submit\"> </form>" + "</td>");
      printWriter.write("</tr>");
      id--;
    }
%>
</body>
</html>

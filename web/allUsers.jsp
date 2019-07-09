<%@ page import="factory.AccountServiceFactory" %>
<%@ page import="service.impl.AccountServiceImpl" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
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
     List<Long> allUserIdList =  accountService.getAllUserID().get();
     for (Long currentId : allUserIdList) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + accountService.getUserById(currentId).get().getEmail() + "</td>");
            printWriter.write("<center>");
            printWriter.write("<td>" + accountService.getUserById(currentId).get().getPassword() + "</td>");
            printWriter.write("<td>"
                 + "<form  action=\"delete\" method=\"post\">\n"
                 +"<button name=\"delete\" type=\"submit\" value=\"" + currentId + "\" </button> Delete </form>"
                 +"<form  action=\"allUsers\" method=\"post\">\n" //functional "Edit" in development
                 +"<button name=\"edit\" type=\"submit\" value=\"" + currentId + "\"  </button> Edit </form>" + "</td>");
            printWriter.write("</tr>");
     }
        %>
</body>
</html>

<%@ page import="factory.ProductServiceFactory" %>
<%@ page import="service.impl.ProductServiceImpl" %>
<%@ page import="java.io.PrintWriter" %>
<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 05.07.2019
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
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
            <% PrintWriter printWriter = response.getWriter();
            printWriter.write("<center>");
            printWriter.write(" <h2> Список пользователей:</h2>");
            printWriter.write(" <table border=\"1\" align=\"center\">\n"
            + "<tr>" + "<th> Name </th>\n" + "<th> Price </th>\n");
    ProductServiceImpl productService = ProductServiceFactory.getInstance();
    int id = productService.size();
    while (id > 0){
      printWriter.write("<tr>");
      printWriter.write("<td>" + productService.getProductById(id).get().getName() + "</td>");
      printWriter.write("<center>");
      printWriter.write("<td>" + productService.getProductById(id).get().getPrice() + "</td>");
      printWriter.write("</tr>");
      id--;
    }
%>
</body>
</html>

<%@ page import="java.io.PrintWriter" %>
<%@ page import="service.ProductService" %>
<%@ page import="factory.ProductServiceFactory" %><%--
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
<% PrintWriter printWriter = response.getWriter();
    printWriter.write("<center>");
    printWriter.write(" <h2> Список пользователей:</h2>");
    printWriter.write(" <table border=\"1\" align=\"center\">\n" + "<tr>" + "<th> Name </th>\n" + "<th> Price </th>\n");
    boolean valid = true;
    long id = 1L;

    ProductService productService = ProductServiceFactory.ProductServiceSingleton();
    id = productService.count();
    while (id > 0) {
        printWriter.write("<tr>");
        printWriter.write("<td>" + productService.getProductById(id).getName() + "</td>");
        printWriter.write("<center>");
        printWriter.write("<td>" + productService.getProductById(id).getPrice() + "</td>");
        printWriter.write("</tr>");
        id--;
    }
%>
</body>
</html>

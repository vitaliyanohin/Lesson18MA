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
  List<Long> allProductIdList =  productService.getAllProductId().get();
     for (Long currentId : allProductIdList) {
            printWriter.write("<tr>");
            printWriter.write("<td>" + productService.getProductById(currentId).get().getName() + "</td>");
            printWriter.write("<center>");
            printWriter.write("<td>" + productService.getProductById(currentId).get().getPrice() + "</td>");
            printWriter.write("<td>"
                 + "<form  action=\"delete\" method=\"post\">\n" //functional "Delete" in development
                 +"<button name=\"delete\" type=\"submit\" value=\"" + currentId + "\" </button> Delete </form>"
                 +"<form  action=\"allUsers\" method=\"post\">\n" //functional "Edit" in development
                 +"<button name=\"edit\" type=\"submit\" value=\"" + currentId + "\"  </button> Edit </form>" + "</td>");
            printWriter.write("</tr>");
     }
        %>
</body>
</html>

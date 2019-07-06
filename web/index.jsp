<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 03.07.2019
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <center>
    <form  action="allUsers" method="post">

      Email <input name="email" type="email" value='<%= (request
      .getParameter("email") == null)? "": request.getParameter("email") %>' /><br>
      Password <input name="pass" id="password" type="password"><br>
      R password <input name="rpass" id="rpassword" type="password"><br>
      <input type="submit" ><br>
    </form>
    <a href="register"> Registration </a><br>
    <a href="newProduct"> New Product </a><br>
    <a href="allUsers"> All Users </a><br>
    <a href="allProducts"> All Products </a> <br>
  </center>
  </body>
</html>

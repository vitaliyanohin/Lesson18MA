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
    <%
      String info = (String) request.getAttribute("info");
      if (info != null) {
        response.getWriter().write("<center>");
        response.getWriter().print(info);
        response.getWriter().write("</center>");
      }
    %>
    <form  action="index" method="post">
      Email <input name="email" type="email" value='<%=
      (request.getParameter("email") == null)? "": request.getParameter("email") %>' /><br>
      Password <input name="pass" id="password" type="password"><br>
      Repeat password <input name="rpass" id="rpassword" type="password"><br>
      <button type="submit"> Sing in</button> <br>
      <button type="submit"
              formaction="register"
              formmethod="post">Sing up</button> <br>
    </form>
    <a href="register"> Registration </a><br>
    <a href="newProduct"> New Product </a><br>
    <a href="allUsers"> All Users </a><br>
    <a href="allProducts"> All Products </a> <br>
  </center>
  </body>
</html>

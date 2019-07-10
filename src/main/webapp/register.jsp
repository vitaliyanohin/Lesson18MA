<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 03.07.2019
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Registration </title>
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
<form  action="register" method="post">
   Email <input name="email" type="email" value='<%=
   (request.getParameter("email") == null)? "": request.getParameter("email") %>' /><br>
    Password <input name="pass" id="password" type="password"> <br>
    Repeat password <input name="repeatPassword" id="repeatPassword" type="password"><br>
    <input type="submit"><br>
</form>
</center>
</body>
</html>

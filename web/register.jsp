<%--
  Created by IntelliJ IDEA.
  User: Vitaliy
  Date: 03.07.2019
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Registration </title>
</head>
<body>
<center>
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
      response.getWriter().write("<center>");
      response.getWriter().print(error);
        response.getWriter().write("</center>");
    }
    %>

<form  action="register" method="post">
   Email <input name="email" type="email" value='<%=
   (request.getParameter("email") == null)? "": request.getParameter("email") %>' /><br>
    Password <input name="pass" id="password" type="password"> <br>
    R password <input name="rpass" id="rpassword" type="password"><br>
    <input type="submit"><br>

</form>
</center>
</body>
</html>

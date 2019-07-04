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
<%--    <script type="text/javascript">--%>

<%--        function validateForm() {--%>
<%--            var password1 = document.getElementById('password');--%>
<%--            var password2 = document.getElementById('rpassword');--%>
<%--            if (password1.value !== password2.value) {--%>
<%--                alert('Проверьте пароли!');--%>
<%--                return false;--%>
<%--            }--%>
<%--        }--%>

<%--    </script>--%>
    <title> Registration </title>
</head>
<body>
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
      response.getWriter().write(error);
    }
    %>
<form  action="register" method="post">

    <input name="email" type="email"/>
    <input name="pass" id="password" type="password">
    <input name="rpass" id="rpassword" type="password">
    <input type="submit" >
</form>
</body>
</html>

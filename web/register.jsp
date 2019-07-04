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
    <script type="text/javascript">

        function validateForm(event) {
            var password1 = document.getElementById('password');
            var password2 = document.getElementById('rpassword');
            if (password1.value !== password2.value) {
                alert('Проверьте пароли!');
                return false;
            }
        }

    </script>
    <title> Registration </title>
</head>
<body>
<form  action="register" method="post">

    <input name="email" type="email"/>
    <input name="pass" id="password" type="password">
    <input id="rpassword" type="password">
    <input type="submit" onclick="validateForm()">
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: bobis1888
  Date: 12/22/19
  Time: 12:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/secureServlet" method="post">
    Username:<input name="userName" type="text"><br>
    Password:<input name="password" type="password"><br>
    <input type="submit" value="Login">
</form>
</body>
</html>

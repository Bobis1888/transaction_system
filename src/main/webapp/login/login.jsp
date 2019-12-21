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
<form action="logServlet" method="post">
    Login : <input type="text" name="name"/><br/>
    Password : <input type="password" name="password"/><br/>
    <input type="submit" name="login">
</form>
</body>
</html>

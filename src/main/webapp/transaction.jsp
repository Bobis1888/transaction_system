<%--
  Created by IntelliJ IDEA.
  User: bobis1888
  Date: 12/4/19
  Time: 7:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank</title>
</head>
<body>
<jsp:include page="links.html"/>
<h2>Hello customer</h2>
<form action="/transaction">
    Account number sender :<label>
    <input type="text" name="sender">
</label>
    Account number recipient : <label>
    <input type="text" name="recipient">
</label>
    Amount :<label>
    <input type="text" name="amount">
</label>
    <input type="submit" value="Transaction">
</form>
</body>
</html>

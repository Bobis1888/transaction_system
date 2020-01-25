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
    <link rel="stylesheet" href="/css/style.css">
    <title>Bank</title>
</head>
<body>
<jsp:include page="links.html"/>
<h2>Hello customer</h2>
<form action="/transaction" class="form_transaction">
    Account number sender :<label class="label">
    <input type="text" name="sender">
</label>
    <br>
    Account number recipient : <label class="label">
    <input type="text" name="recipient">
</label>
    <br>
    Amount :<label class="label">
    <input type="text" name="amount">
    <br>
</label>
    <br>
    <input type="submit" value="Transaction">
</form>
</body>
</html>

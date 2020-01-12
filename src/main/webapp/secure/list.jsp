<%@ page import="java.util.ArrayList" %>
<%@ page import="model.BankAccount" %><%--
  Created by IntelliJ IDEA.
  User: bobis1888
  Date: 12/4/19
  Time: 8:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/secure/style.css">
    <title>List Bank Account</title>
</head>
<body>
<jsp:include page="links.html"/>
<br>
    <%
        ArrayList<BankAccount> bankAccounts = (ArrayList<BankAccount>) request.getAttribute("list");
        for (BankAccount b:bankAccounts) {
            out.print("<br>");
            out.print(b);
        }

    %>
</body>
</html>

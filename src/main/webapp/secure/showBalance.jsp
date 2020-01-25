<%@ page import="model.Client" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.BankAccount" %><%--
  Created by IntelliJ IDEA.
  User: bobis1888
  Date: 1/25/20
  Time: 11:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Balance</title>
</head>
<body>
    <h2>Hello <%out.print(((Client)session.getAttribute("client")).getNameClient());%>!</h2>
    <%
        ArrayList<BankAccount> bankAccounts = (ArrayList<BankAccount>) request.getAttribute("listBankAccounts");
        if (bankAccounts!=null){
            for (BankAccount bankAccount: bankAccounts){
                out.print(bankAccount);
            }
        }
    %>
</body>
</html>

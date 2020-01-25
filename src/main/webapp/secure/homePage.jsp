<%@ page import="model.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/style.cssyle.css">
    <title>Bank</title>
</head>
<body>
<jsp:include page="links.html"/>
<div class="header">
	<h1>Bank of Great Mumbai</h1>
    <div class="beta">beta 0.4.1</div>
</div>
<h3>Hello <%
    out.print(((Client)session.getAttribute("client")).getNameClient());
        %>!</h3>
<div><a href="/secure/transaction.jsp"> Send money your BRO</a></div>
<div><a href="/balance">Show you cash(beta)</a></div>
<p><%
        boolean transactionBoolean = false;
        if (request.getAttribute("transactionBoolean")!=null) {
            transactionBoolean = (boolean) request.getAttribute("transactionBoolean");
            if (transactionBoolean) {
                out.print("Transaction was successful");
            } else {
                out.print("Transaction failed");
            }
        }
    %>
</p>
</body>
</html>
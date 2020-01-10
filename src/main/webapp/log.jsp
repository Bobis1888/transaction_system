<%@ page import="java.util.ArrayList" %>
<%@ page import="model.NodeHistory" %><%--
  Created by IntelliJ IDEA.
  User: bobis1888
  Date: 12/31/19
  Time: 7:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log</title>
</head>
<body>
<jsp:include page="links.html"/>
<form action="/history">
    Show the history  <input type="submit" value="Show">
    <br>
    <%
        ArrayList<NodeHistory> arrayList = (ArrayList<NodeHistory>) request.getAttribute("node");
        if (arrayList!=null) {
            for (NodeHistory n : arrayList){
                out.print("<br>");
                out.print(n);
                out.print("<br>");
            }
        }
    %>
</form>
</body>
</html>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Stetsenko</title>
</head>
<body>
<% Map<String, Integer> mapUsers = (Map<String, Integer>) request.getSession().getAttribute("mapUsers");
    for (Map.Entry<String, Integer> item : mapUsers.entrySet())
    {%>
     <%= item.getKey() + " - " + item.getValue()%>
    <br><br>
    <% }; %>
</body>
</html>

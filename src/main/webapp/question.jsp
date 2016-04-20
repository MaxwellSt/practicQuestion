<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Stetsenko</title>
</head>
<body>
<h1><%= request.getAttribute("question")%></h1>
<form action='ServletQuestion' method='post'>
    <input name='answer' value=''>
    <br><br>
    <input type='submit' name='Submit' value='Enter' size=20>
</form>

</body>
</html>

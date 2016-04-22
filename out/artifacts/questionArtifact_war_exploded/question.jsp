<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="model.Question" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Stetsenko</title>
</head>
<body>
<% Question q = (Question)request.getSession().getAttribute("question"); %>

<h1><%= q.getQuestion()%></h1>
<h2><%= "( point: " + q.getPoints() + ")"%></h2>

<form action='ServletQuestion' method='get'>
    <input name='answer' value=''>
    <br><br>
    <input type='submit' name='Submit' value='Enter' size=20>
</form>

</body>
</html>

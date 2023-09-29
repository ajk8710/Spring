<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>

<body>
<div align="center">

<%@ include file="menu.jsp" %>

<h1>Home</h1>
<sec:authorize access="!isAuthenticated()">
<a href="login">Log In</a>
</sec:authorize>

<%@ include file="footer.jsp" %>

</div>
</body>

</html>

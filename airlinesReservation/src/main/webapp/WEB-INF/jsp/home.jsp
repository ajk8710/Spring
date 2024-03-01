<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title>Home</title>
</head>

<body>
<%@ include file="menu.jsp" %>

<div align="center">

<h1>Home</h1>
<sec:authorize access="!isAuthenticated()">
<a href="login">Log In</a>
<br>
<a href="register">Register</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<h5>Welcome, ${username}.</h5>
</sec:authorize>

<%@ include file="footer.jsp" %>

</div>
<script src="js/bootstrap.bundle.js"></script>
</body>

</html>

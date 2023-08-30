<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- <jsp:include page="header.jsp"></jsp:include> --%>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <%@ include file="menu.jsp" %>
</head>
<body>
<div align="center">
<h1>Home</h1>

Logged-in User: ${pageContext.request.userPrincipal.name}<br>
<%-- <br>User Principal: ${pageContext.request.userPrincipal}<br> --%>

<strong>Authorities: <sec:authentication property="principal.authorities"/></strong><br>

<a href="logout">Log Out</a><br><br>

<sec:authorize access="!isAuthenticated()">
<a href="login">Log In</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
<sec:authorize access="hasAuthority('HR') && hasAuthority('User')">
<a href="userForm">User Form</a><br>
<a href="employeeForm">Employee Form</a><br>
</sec:authorize>

<sec:authorize access="hasAuthority('User')">
<a href="userForm">User Form</a><br>
</sec:authorize>

<sec:authorize access="hasAuthority('Admin')">
<a href="userForm">User Form</a><br>
<a href="roleForm">Role Form</a><br>
<a href="employeeForm">Employee Form</a><br>
</sec:authorize>
</sec:authorize>

</div>
<%@ include file="footer.jsp" %>
</body>

</html>

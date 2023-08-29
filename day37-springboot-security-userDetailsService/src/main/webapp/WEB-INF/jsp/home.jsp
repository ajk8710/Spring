<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> <!-- tag library provided by spring security-->
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<div>
<h1>Home</h1>

<sec:authorize access="hasAuthority('HR')">
<a href="employeeForm">Employee Form</a>
<a href="userForm">User Form</a>
</sec:authorize>

<sec:authorize access="hasAuthority('User')">
<a href="userForm">User Form</a>
</sec:authorize>

<sec:authorize access="hasAuthority('Admin')">
<a href="userForm">User Form</a>
<a href="roleForm">Role Form</a>
</sec:authorize>

</div>
</body>

</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Form</title>
</head>

<body>
<div align="center">

<h1>User Form</h1>

<f:form action="saveUser" modelAttribute="user">  <!-- action is what happens upon submit, modelAttribute is name of class starting with lower case -->
<table border="1">
<tr>  <!-- table row -->
<td>User Id:</td> <td><f:input path="userid" value="${retrievedUser.userid}"/></td>  <!-- table data (table columns) -->  <!-- path binds the form field to the bean property. -->
</tr>

<tr>
<td>Name:</td> <td><f:input path="username" value="${retrievedUser.username}"/></td>
</tr>

<tr>
<td>Email:</td> <td><f:input path="email" value="${retrievedUser.email}"/></td>
</tr>

<tr>
<td>Mobile:</td> <td><f:input path="mobile" value="${retrievedUser.mobile}"/></td>
</tr>

<tr>
<td colspan="2" align="center"><input type="submit" value="Submit"/></td>
</tr>
</table>
</f:form>

<table border="1">
<thead>
    <tr>
       <th>User ID</th><th>Name</th><th>Email</th><th>Mobile</th><th>Action</th>
    </tr>
</thead>

<c:forEach items="${users}" var="u">
    <tr>
       <td>${u.userid}</td><td>${u.username}</td><td>${u.email}</td><td>${u.mobile}</td>
       <td><a href="updateUser?userid=${u.userid}">Update</a> | <a href="deleteUser?userid=${u.userid}">Delete</a></td>  <!-- empId should match name of class property -->
    </tr>                                                                                                         <!-- because it creates object with provided property -->
</c:forEach>
</table>

</div>
</body>
</html>

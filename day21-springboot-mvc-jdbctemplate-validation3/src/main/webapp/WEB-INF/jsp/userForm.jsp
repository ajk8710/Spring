<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Form</title>
<style>
.error{
    color:red;
    font-style:italic;
    font-weight:bold;
}
</style>
</head>

<body>
<div align="center">

<h1>User Form</h1>

<f:form action="saveUser" method="POST" modelAttribute="user">  <!-- action is what happens upon submit, modelAttribute is name of class starting with lower case -->
<table border="1">

<tr>  <!-- table row -->
<td>User Id:</td> <td><f:input path="userid" value="${retrievedUser.userid}"/></td>  <!-- table data (table columns) -->  <!-- path binds the form field to the bean property. -->
<td><f:errors path="userid" cssClass="error"/></td>
</tr>

<tr>
<td>Name:</td> <td><f:input path="username" value="${retrievedUser.username}"/></td>
<td><f:errors path="username" cssClass="error"/></td>
</tr>

<tr>
<td>Password:</td> <td><f:password path="password" value="${retrievedUser.password}"/></td>
<td><f:errors path="password" cssClass="error"/></td>
</tr>

<tr>
<td>Email:</td> <td><f:input path="email" value="${retrievedUser.email}"/></td>
<td><f:errors path="email" cssClass="error"/></td>
</tr>

<tr>
<td>Mobile:</td> <td><f:input path="mobile" value="${retrievedUser.mobile}"/></td>
<td><f:errors path="mobile" cssClass="error"/></td>
</tr>

<tr>
<td>Roles:</td> 
<td>
    <c:forEach items="${availableRoles}" var="r">
        <f:checkbox path="roles" label="${r.name}" value="${r.roleId}"/>
    </c:forEach>
</td>
<td><f:errors path="roles" cssClass="error"/></td>
</tr>

<tr>
<td colspan="2" align="center"><input type="submit" value="Submit"/></td>
</tr>

</table>
</f:form>

<table border="1">
<thead>
    <tr>
       <th>User ID</th><th>Name</th><th>Password</th><th>Email</th><th>Mobile</th><th>Action</th>
    </tr>
</thead>

<c:forEach items="${users}" var="u">
    <tr>
       <td>${u.userid}</td><td>${u.username}</td><td>${u.password}</td><td>${u.email}</td><td>${u.mobile}</td><td>${u.roles}</td>
       <td><a href="updateUser?userid=${u.userid}">Update</a> | <a href="deleteUser?userid=${u.userid}">Delete</a></td>  <!-- userid should match name of class property -->
    </tr>                                                                                                          <!-- because it creates object with provided property -->
</c:forEach>
</table>

</div>
</body>
</html>

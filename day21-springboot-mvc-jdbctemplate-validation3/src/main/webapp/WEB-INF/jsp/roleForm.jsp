<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Role Form</title>
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

<h1>Role Form</h1>

<f:form action="saveRole" method="POST" modelAttribute="role">  <!-- action is what happens upon submit, modelAttribute is name of class starting with lower case -->
<table border="1">

<tr>  <!-- table row -->
<td>Role Id:</td> <td><f:input path="roleId" value="${retriedvedRole.roleId}"/></td>  <!-- table data (table columns) -->  <!-- path binds the form field to the bean property. -->
<td><f:errors path="roleId" cssClass="error"/></td>
</tr>

<tr>
<td>Role Name:</td> <td><f:input path="name" value="${retriedvedRole.name}"/></td>
<td><f:errors path="name" cssClass="error"/></td>
</tr>

<tr>
<td colspan="2" align="center"><input type="submit" value="Submit"/></td>
</tr>

</table>
</f:form>

<c:if test="${not empty roles}">
<table border="1">

<thead>
    <tr>
       <th>Role ID</th> <th>Role Name</th> <th>Action</th>
    </tr>
</thead>

<c:forEach items="${roles}" var="r">
    <tr>
       <td>${r.roleId}</td> <td>${r.name}</td>
       <td><a href="updateRole?roleId=${r.roleId}">Update</a> | <a href="deleteRole?roleId=${r.roleId}">Delete</a></td>  <!-- roleId should match name of class property -->
    </tr>                                                                                                          <!-- because it creates object with provided property -->
</c:forEach>

</table>
</c:if>

</div>
</body>
</html>

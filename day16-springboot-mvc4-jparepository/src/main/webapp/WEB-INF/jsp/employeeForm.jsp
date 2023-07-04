<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<html>
<head>
<meta charset="UTF-8">
<title>***EmployeeForm***</title>
</head>
<body>

<div align="center">
<h1>Employee Management Form</h1>
<f:form action="saveEmployee" method="post" modelAttribute="employee">  <!-- modelAttribute is name of class starting with lower case -->
<table>

<tr>
<td>Emp Id:</td>
<td><input type="text" name="empId" value="${retrievedEmployee.empId}"/></td>  <!-- name should match name of class property -->
</tr>

<tr>
<td>Name:</td>
<td><input type="text" name="name" value="${retrievedEmployee.name}"/></td>
</tr>

<tr>
<td>Designation:</td>
<td><input type="text" name="designation" value="${retrievedEmployee.designation}"/></td>
</tr>

<tr>
<td>Salary:</td>
<td><input type="text" name="salary" value="${retrievedEmployee.salary}"/></td>
</tr>

<tr>
<td colspan="2" align="center"><input type="submit" value="Submit"/></td>
</tr>

</table>
</f:form>

<table border="1">

<thead>
	<tr>
	   <th>EmpId</th><th>Name</th><th>Designation</th><th>Salary</th><th>Action</th>
	</tr>
</thead>

<c:forEach items="${listOfEmployees}" var="e">
	<tr>
	   <td>${e.empId}</td><td>${e.name}</td><td>${e.designation}</td><td>${e.salary}</td>
	   <td><a href="delete?empId=${e.empId}">Delete</a> | <a href="update?empId=${e.empId}">Update</a></td>  <!-- empId should match name of class property -->
	</tr>
</c:forEach>

</table>

</div>
</body>
</html>
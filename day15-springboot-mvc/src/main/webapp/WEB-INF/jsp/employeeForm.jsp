<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<html>
<head>
<meta charset="UTF-8">
<title>***EmployeeForm***</title>
</head>
<body>

<div align="center">
<h1>Employee Management Form</h1>
<form action="saveEmployee" method="post">  <!-- post to url when form is submitted -->
<table>

<tr>
<td>Emp Id:</td>
<td><input type="text" name="empid" value="${retrievedEmployee.empId}"/></td>  <!-- name should match RequestParam name -->
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
</form>

<table border="1">

<thead>
    <tr>
       <th>EmpId</th><th>Name</th><th>Designation</th><th>Salary</th><th>Action</th>
    </tr>
</thead>

<c:forEach items="${listOfEmployees}" var="e">
	<tr>  <!-- table row -->
	   <td>${e.empId}</td><td>${e.name}</td><td>${e.designation}</td><td>${e.salary}</td>  <!-- table column -->
	   <td><a href="delete?empid=${e.empId}">Delete</a> | <a href="update?empid=${e.empId}">Update</a></td>  <!-- anchor tag is a link -->
	</tr>
</c:forEach>

</table>

</div>
</body>
</html>
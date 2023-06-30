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
<td><input type="text" name="empid"/></td>  <!-- name should match RequestParam name -->
</tr>

<tr>
<td>Name:</td>
<td><input type="text" name="name"/></td>
</tr>

<tr>
<td>Designation:</td>
<td><input type="text" name="designation"/></td>
</tr>

<tr>
<td>Salary:</td>
<td><input type="text" name="salary"/></td>
</tr>

<tr>
<td colspan="2" align="center"><input type="submit" value="Submit"/></td>
</tr>

</table>
</form>

${listOfEmployees}
<c:forEach items="${listOfEmployees}" var="e">
<br>${e.name}
</c:forEach>
</div>

</body>
</html>
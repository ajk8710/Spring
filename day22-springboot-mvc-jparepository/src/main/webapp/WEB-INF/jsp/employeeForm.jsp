<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<html>
<head>
<meta charset="UTF-8">
<title>***EmployeeForm***</title>
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
<h1>Employee Management Form</h1>

<f:form action="saveEmployee" method="post" modelAttribute="employee">  <!-- modelAttribute is name of class starting with lower case -->
<table>

<tr>
<td>Emp Id:</td>
<td><f:input type="text" path="empId" value="${retrievedEmployee.empId}"/></td>  <!-- name should match name of class property -->
<td><f:errors path="empId" cssClass="error"/></td>
</tr>

<tr>
<td>Name:</td>
<td><f:input type="text" path="name" value="${retrievedEmployee.name}"/></td>
<td><f:errors path="name" cssClass="error"/></td>
</tr>

<tr>
<td>Designation:</td>
<td><f:input type="text" path="designation" value="${retrievedEmployee.designation}"/></td>
<td><f:errors path="designation" cssClass="error"/></td>
</tr>

<tr>
<td>Salary:</td>
<td><f:input type="text" path="salary" value="${retrievedEmployee.salary}"/></td>
<td><f:errors path="salary" cssClass="error"/></td>
</tr>

<tr>
<td>Date of Birth:</td>
<td><f:input type="text" path="dob"/></td>
<td><f:errors path="dob" cssClass="error"/></td>
</tr>

<tr>
<td>Gender:</td>
<td><f:input type="text" path="gender"/></td>
<td><f:errors path="gender" cssClass="error"/></td>
</tr>

<tr>
<td>Citizenship:</td>
<td>
<f:radiobutton name="citizenship" path="citizenship" value="USA" label="usa"/>
<f:radiobutton name="citizenship" path="citizenship" value="Canada" label="canada"/>
<f:radiobutton name="citizenship" path="citizenship" value="Korea" label="korea"/>
<f:radiobutton name="citizenship" path="citizenship" value="UK" label="uk"/>
</td>
<td><f:errors path="citizenship" cssClass="error"/></td>
</tr>

<tr>
<td>Hobbies:</td>
<td><f:checkboxes name="hobbies" path="hobbies" items="${hobbies}"/></td>
<td><f:errors path="hobbies" cssClass="error"/></td>
</tr>

<tr>
<td>Spoken Languages:</td>
<c:forEach items="${spokenLanguages}" var="sl">
    <td><f:checkbox path="spokenLanguages" value="${sl}" label="${sl}"/></td>
</c:forEach>
<td><f:errors path="spokenLanguages" cssClass="error"/></td>
</tr>

<tr>
<td>Skills:</td>
<td>
	<f:select path="skills">
	<f:option value="" label="Please select a vlaue"></f:option>
	<f:options items="${skills}"/>
	</f:select>
</td>
<td><f:errors path="skills" cssClass="error"/></td>
</tr>

<tr>
<td>Insured:</td>
<td><f:input type="text" path="insured"/></td>
<td><f:errors path="insured" cssClass="error"/></td>
</tr>

<tr>
<td>Phone Number:</td>
<td><f:input type="text" path="phoneNumber"/></td>
<td><f:errors path="phoneNumber" cssClass="error"/></td>
</tr>

<tr>
<td>Email:</td>
<td><f:input type="text" path="email"/></td>
<td><f:errors path="email" cssClass="error"/></td>
</tr>

<tr>
<td colspan="3" align="center"><strong>Address</strong></td>
</tr>

<tr>
<td>Address Line1:</td>
<td><f:input type="text" path="address.addressLine1"/></td>
<td><f:errors path="address.addressLine1" cssClass="error"/></td>
</tr>

<tr>
<td>Address Line2::</td>
<td><f:input type="text" path="address.addressLine2"/></td>
<td><f:errors path="address.addressLine2" cssClass="error"/></td>
</tr>

<tr>
<td>City:</td>
<td><f:input type="text" path="address.city"/></td>
<td><f:errors path="address.city" cssClass="error"/></td>
</tr>

<tr>
<td>State:</td>
<td><f:input type="text" path="address.state"/></td>
<td><f:errors path="address.state" cssClass="error"/></td>
</tr>

<tr>
<td>Country:</td>
<td><f:input type="text" path="address.country"/></td>
<td><f:errors path="address.country" cssClass="error"/></td>
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

<c:forEach items="${employees}" var="e">
	<tr>
	   <td>${e.empId}</td><td>${e.name}</td><td>${e.designation}</td><td>${e.salary}</td>
	   <td><a href="deleteEmployee?empId=${e.empId}">Delete</a> | <a href="updateEmployee?empId=${e.empId}">Update</a></td>  <!-- empId should match name of class property -->
	</tr>
</c:forEach>

</table>

</div>
</body>
</html>

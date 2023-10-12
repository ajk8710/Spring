<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> <!-- tag library provided by spring security-->
<!DOCTYPE html>
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

Logged-in User: ${pageContext.request.userPrincipal.name}<br>
<%-- <br>User Principal: ${pageContext.request.userPrincipal}<br> --%>

<strong>Authorities: <sec:authentication property="principal.authorities"/></strong><br>

<a href="${pageContext.request.contextPath}/logout">Log Out</a><br><br>

<f:form action="saveEmployee" method="post" modelAttribute="employee">  <!-- modelAttribute is name of class starting with lower case -->
<table>

<tr>
<td>Emp Id:</td>
<td><f:input type="text" path="empId"/></td>  <!-- path should match name of class field -->
<td><f:errors path="empId" cssClass="error"/></td>
</tr>

<tr>
<td>Name:</td>
<td><f:input type="text" path="name"/></td>
<td><f:errors path="name" cssClass="error"/></td>
</tr>

<tr>
<td>Designation:</td>
<td><f:input type="text" path="designation"/></td>
<td><f:errors path="designation" cssClass="error"/></td>
</tr>

<tr>
<td>Salary:</td>
<td><f:input type="text" path="salary"/></td>
<td><f:errors path="salary" cssClass="error"/></td>
</tr>

<tr>
<td>Date of Birth:</td>  <!-- A Calendar -->
<td><f:input type="date" path="dob"/></td>  <!-- value="<%=java.time.LocalDate.now() %>" -->
<td><f:errors path="dob" cssClass="error"/></td>
</tr>

<tr>
<td>Gender:</td>  <!-- radio buttons grouped under name-->
<td><f:radiobuttons name="gender" path="gender" items="${genders}"/></td>
<td><f:errors path="gender" cssClass="error"/></td>
</tr>

<tr>
<td>Citizenship:</td>  <!-- radio buttons grouped under name-->
<td>
<f:radiobutton name="citizenship" path="citizenship" value="USA" label="usa"/>
<f:radiobutton name="citizenship" path="citizenship" value="Canada" label="canada"/>
<f:radiobutton name="citizenship" path="citizenship" value="Korea" label="korea"/>
<f:radiobutton name="citizenship" path="citizenship" value="UK" label="uk"/>
</td>
<td><f:errors path="citizenship" cssClass="error"/></td>
</tr>

<tr>
<td>Hobbies:</td>  <!-- Checkboxes -->
<td><f:checkboxes name="hobbies" path="hobbies" items="${hobbies}"/></td>
<td><f:errors path="hobbies" cssClass="error"/></td>
</tr>

<tr>
<td>Spoken Languages:</td>  <!-- Checkboxes -->
<td>
<c:forEach items="${spokenLanguages}" var="sl">
    <f:checkbox path="spokenLanguages" value="${sl}" label="${sl}"/>
</c:forEach>
</td>
<td><f:errors path="spokenLanguages" cssClass="error"/></td>
</tr>

<tr>
<td>Skills:</td>  <!-- Selection Boxes -->
<td>
	<f:select path="skills">
	<f:option value="" label="Please select a vlaue"></f:option>
	<f:options items="${skills}"/>
	</f:select>
</td>
<td><f:errors path="skills" cssClass="error"/></td>
</tr>

<tr>
<td>Insured:</td>  <!-- Checkbox -->
<td>
<c:choose>
    <c:when test="${isInsured}">
        <f:checkbox path="insured" checked="checked"/>
    </c:when>
    <c:otherwise>
        <f:checkbox path="insured"/>
    </c:otherwise>
</c:choose>
</td>
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

<c:if test="${not empty employees }">
<table border="1">

<thead>
	<tr>
	   <th>EmpId</th><th>Name</th><th>Designation</th><th>Salary</th>
	   <th>Gender</th><th>Citizenship</th><th>Hobbies</th><th>Spoken Languages</th>
	   <th>Skills</th><th>Insured</th><th>Phone Number</th><th>Email</th>
	   <th>City</th><th>Action</th>
	</tr>
</thead>

<c:forEach items="${employees}" var="e">
	<tr>
	   <td>${e.empId}</td><td>${e.name}</td><td>${e.designation}</td><td>${e.salary}</td>
	   <td>${e.gender}</td><td>${e.citizenship}</td>
	   
	   <td>
	       <c:forEach items="${e.hobbies}" var="hobby">
	           ${hobby}
	       </c:forEach>
	   </td>
	   <td>
	       <c:forEach items="${e.spokenLanguages}" var="sl">
	           ${sl}
	       </c:forEach>
	   </td>
	   <td>
	       <c:forEach items="${e.skills}" var="s">
	           ${s}
	       </c:forEach>
	   </td>
	   <td>${e.insured}</td><td>${e.phoneNumber}</td><td>${e.email}</td>
	   <td>${e.address.city}, ${e.address.state}</td>
	   <td> <a href="${pageContext.request.contextPath}/updateEmployee?empId=${e.empId}">Update</a> | <a href="${pageContext.request.contextPath}/deleteEmployee?empId=${e.empId}">Delete</a> </td>  <!-- empId should match name of class property -->
	</tr>
</c:forEach>

</table>
</c:if>

</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>== User Form ==</title>
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

<f:form action="saveUser" method="post" modelAttribute="user">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="userId"/> </td>
    <td> <f:errors path="userId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Name:</b> </td>
    <td> <f:input path="username"/> </td>
    <td> <f:errors path="username" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Password:</b> </td>
    <td> <f:password path="password"/> </td>
    <td> <f:errors path="password" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Email:</b> </td>
    <td> <f:input path="email"/> </td>
    <td> <f:errors path="email" cssClass="error"/> </td>
    </tr>

    <tr>
    <td> <b>Mobile:</b> </td>
    <td> <f:input path="mobile"/> </td>
    <td> <f:errors path="mobile" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Roles: (should be drop down)</b> </td>
    <td> <f:input path="roles"/> </td>
    <td> <f:errors path="roles" cssClass="error"/> </td>
    </tr>

    <tr>
    <td colspan="2" align="center"> <input type="submit" value="Submit"/> </td>
    </tr>

</table>
</f:form>

<c:if test="${not empty users}">
    <table border=1>
        <thead><tr> <td>ID</td> <td>Name</td> <td>Password</td> <td>Email</td> <td>Mobile</td> <td>Roles</td> </tr></thead>

        <c:forEach items="${users}" var="u">
            <td>${u.userId}</td> <td>${u.username}</td> <td>${u.password}</td> <td>${u.email}</td> <td>${u.mobile}</td> <td>${u.roles}</td>
        </c:forEach>
    </table>
</c:if>

</div>
</body>
</html>
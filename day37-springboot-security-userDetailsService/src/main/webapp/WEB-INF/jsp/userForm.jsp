<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> <!-- tag library provided by spring security-->
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
<h1>User Form</h1>

Logged-in User: ${pageContext.request.userPrincipal.name}<br>
<%-- <br>User Principal: ${pageContext.request.userPrincipal}<br> --%>

<strong>Authorities: <sec:authentication property="principal.authorities"/></strong><br>

<a href="logout">Log Out</a><br><br>

<f:form action="saveUser" method="post" modelAttribute="user">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="userId" value="${retrievedUser.userId}"/> </td>
    <td> <f:errors path="userId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Name:</b> </td>
    <td> <f:input path="username" value="${retrievedUser.username}"/> </td>
    <td> <f:errors path="username" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Password:</b> </td>
    <td> <f:input path="password" value="${retrievedUser.password}"/> </td>
    <td> <f:errors path="password" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Email:</b> </td>
    <td> <f:input path="email" value="${retrievedUser.email}"/> </td>
    <td> <f:errors path="email" cssClass="error"/> </td>
    </tr>

    <tr>
    <td> <b>Mobile:</b> </td>
    <td> <f:input path="mobile" value="${retrievedUser.mobile}"/> </td>
    <td> <f:errors path="mobile" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Roles:</b> </td>
    <td>
    <!-- Having model.addAttribute("user", retrievedUser) and letting user form to be filled in by path without specifying value -->
    <!-- led to error due to user.role to be filled in selection form of String - cannot convert Object (Role) to String. -->
    
    <c:forEach items="${ListofAllRoles}" var="r">
        <c:choose>
            <c:when test="${selectedRoles.contains(r)}">
                <f:checkbox path="roles" value="${r.roleId}" label="${r.name}" checked="true"/>
            </c:when>
            <c:otherwise>
                <f:checkbox path="roles" value="${r.roleId}" label="${r.name}"/>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    </td>
    <td> <f:errors path="roles" cssClass="error"/> </td>
    </tr>

<!--
    <tr>
    <td> <b>Roles:</b> </td>
    <td>
    <f:select path="roles">
        <c:forEach items="${ListofAllRoles}" var="r">
                <c:choose>
                    <c:when test="${selectedRoles.contains(r)}">
                        <f:option value="${r.roleId}" label="${r.name}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${r.roleId}" label="${r.name}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    
    </td>
    <td> <f:errors path="roles" cssClass="error"/> </td>
    </tr>
-->

<%-- A JSP comment. Ignored by the JSP engine. --%>
<!-- An HTML comment. Ignored by the browser. -->

<%-- <!-- this comment with "%" ensures code is not executed in server -->
    <f:select path="roles" value="${retrievedUser.roles.name}">
        <f:option value="" label="Please select roles below"></f:option>
        <c:forEach items="${ListofAllRoles}" var="r">
            <f:option value="${r.roleId}" label="${r.name}"></f:option>
        </c:forEach>
    </f:select> 
--%>

    <tr>
    <td colspan="2" align="center"> <input type="submit" value="Submit"/> </td>
    </tr>

</table>
</f:form>

<c:if test="${not empty users}">
    <table border="1">
        <thead><tr> <td>ID</td> <td>Name</td> <td>Password</td> <td>Email</td> <td>Mobile</td> <td>Roles</td> 
        <sec:authorize access="hasAuthority('Admin')"> <td>Action</td> </sec:authorize> </tr></thead>

        <c:forEach items="${users}" var="u">
            <tr>
            <td>${u.userId}</td> <td>${u.username}</td> <td>${u.password}</td> <td>${u.email}</td> <td>${u.mobile}</td>
            <td>
                <c:forEach items="${u.roles}" var="r">
                    ${r.name}
                </c:forEach>
            </td>
            
            <sec:authorize access="hasAuthority('Admin')">
            <td> <a href="updateUser?userId=${u.userId}"> Update </a> | <a href="deleteUser?userId=${u.userId}"> Delete </a> </td>
            </sec:authorize>
            </tr>
        </c:forEach>
    </table>
</c:if>

</div>
</body>
</html>

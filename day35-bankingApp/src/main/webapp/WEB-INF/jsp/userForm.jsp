<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="common/header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
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
<%@ include file="common/menu.jsp" %>

<div align="center">
<h1>User Management Form</h1>

<strong>Logged-in User: ${pageContext.request.userPrincipal.name}</strong><br>
<%-- <br>User Principal: ${pageContext.request.userPrincipal}<br> --%>
User Role: <sec:authentication property="principal.authorities"/><br>
<a href="logout">Log Out</a><br><br>

<f:form action="saveUser" method="post" modelAttribute="user">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="userId" value="${retrievedUser.userId}"/> </td>
    <td> <f:errors path="userId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Username:</b> </td>
    <td> <f:input path="username" value="${retrievedUser.username}"/> </td>
    <td> <f:errors path="username" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Password:</b> </td>
    <td> <f:input path="password"/> </td>
    <td> <f:errors path="password" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Roles:</b> </td>
    <td>
    <!-- Having model.addAttribute("user", retrievedUser) and letting user form to be filled in by path without specifying value -->
    <!-- led to error due to user.role to be filled in selection form of String - cannot convert Object (Role) to String. -->
    
    <c:forEach items="${ListofAllRoles}" var="r">
        <c:choose>
            <c:when test="${selectedRoles.contains(r)}">
                <f:checkbox path="roles" value="${r.roleId}" label="${r.roleName}" checked="true" class="form-check-input"/>
            </c:when>
            <c:otherwise>
                <f:checkbox path="roles" value="${r.roleId}" label="${r.roleName}" class="form-check-input"/>
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
                        <f:option value="${r.roleId}" label="${r.roleName}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${r.roleId}" label="${r.roleName}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    
    </td>
    <td> <f:errors path="roles" cssClass="error"/> </td>
    </tr>
-->

    <tr>
    <td colspan="2" align="center"> <input type="submit" value="Submit" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>

<div class=container-md>
<c:if test="${not empty users}">
    <table border="1" class="table table-striped">
        <thead><tr> <td>ID</td> <td>Username</td> <td>Password</td> <td>Roles</td> <td>Action</td> </tr></thead>

        <c:forEach items="${users}" var="u">
            <tr>
            <td>${u.userId}</td> <td>${u.username}</td> <td>${u.password}</td>
            <td>
                <c:forEach items="${u.roles}" var="r">
                    ${r.roleName}
                </c:forEach>
            </td>
            
            <td> <a href="updateUser?userId=${u.userId}"> Update </a> | <a href="deleteUser?userId=${u.userId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>

</div>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>

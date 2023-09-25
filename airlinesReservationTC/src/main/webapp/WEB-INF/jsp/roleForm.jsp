<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
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

<%@ include file="menu.jsp" %>
<h1>Role Form</h1>

<f:form action="saveRole" method="post" modelAttribute="role">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>Role ID:</b> </td>
    <td> <f:input path="roleId"/> </td>
    <td> <f:errors path="roleId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Role Name</b> </td>
    <td> <f:input path="roleName"/> </td>
    <td> <f:errors path="roleName" cssClass="error"/> </td>
    </tr>

    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Submit"/> </td>
    </tr>

</table>
</f:form>

<c:if test="${not empty roles}">
    <table border="1">
        <thead><tr> <td>Role ID</td> <td>Role Name</td> <td>Action</td> </tr></thead>
        
        <c:forEach items="${roles}" var="r">
            <tr>
            <td>${r.roleId}</td> <td>${r.roleName}</td>
            <td> <a href="${pageContext.request.contextPath}/updateRole?roleId=${r.roleId}"> Update </a> | <a href="${pageContext.request.contextPath}/deleteRole?roleId=${r.roleId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%@ include file="footer.jsp" %>

</div>
</body>
</html>

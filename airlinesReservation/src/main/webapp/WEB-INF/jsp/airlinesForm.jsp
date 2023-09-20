<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Airlines Form</title>
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
<h1>Airlines Form</h1>

<f:form action="saveAirlines" method="post" modelAttribute="airlines">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="airlinesId" value="${retrievedAirlines.airlinesId}"/> </td>
    <td> <f:errors path="airlinesId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Airlines Name:</b> </td>
    <td> <f:input path="airlinesName" value="${retrievedAirlines.airlinesName}"/> </td>
    <td> <f:errors path="airlinesName" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Airlines Code:</b> </td>
    <td> <f:input path="airlinesCode" value="${retrievedAirlines.airlinesCode}"/> </td>
    <td> <f:errors path="airlinesCode" cssClass="error"/> </td>
    </tr>

    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Submit"/> </td>
    </tr>

</table>
</f:form>

<c:if test="${not empty airlinesList}">
    <table border="1">
        <thead><tr>
            <td>ID</td> <td>Airlines Name</td> <td>Airlines Code</td>
            <td>Action</td>
        </tr></thead>

        <c:forEach items="${airlinesList}" var="a">
            <tr>
            <td>${a.airlinesId}</td> <td>${a.airlinesName}</td> <td>${a.airlinesCode}</td>
            <td> <a href="${pageContext.request.contextPath}/updateAirlines?airlinesId=${a.airlinesId}"> Update </a> | <a href="${pageContext.request.contextPath}/deleteAirlines?airlinesId=${a.airlinesId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%@ include file="footer.jsp" %>

</div>
</body>
</html>

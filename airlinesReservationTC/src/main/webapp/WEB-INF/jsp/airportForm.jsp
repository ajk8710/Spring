<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Airport Form</title>
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
<h1>Airport Form</h1>

<f:form action="saveAirport" method="post" modelAttribute="airport">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="airportId" value="${retrievedAirport.airportId}"/> </td>
    <td> <f:errors path="airportId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Airport City:</b> </td>
    <td> <f:input path="airportCity" value="${retrievedAirport.airportCity}"/> </td>
    <td> <f:errors path="airportCity" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Airport Code:</b> </td>
    <td> <f:input path="airportCode" value="${retrievedAirport.airportCode}"/> </td>
    <td> <f:errors path="airportCode" cssClass="error"/> </td>
    </tr>

    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Submit"/> </td>
    </tr>

</table>
</f:form>

<c:if test="${not empty airports}">
    <table border="1">
        <thead><tr>
            <td>ID</td> <td>Airport City</td> <td>Airport Code</td>
            <td>Action</td>
        </tr></thead>

        <c:forEach items="${airports}" var="a">
            <tr>
            <td>${a.airportId}</td> <td>${a.airportCity}</td> <td>${a.airportCode}</td>
            <td> <a href="${pageContext.request.contextPath}/updateAirport?airportId=${a.airportId}"> Update </a> | <a href="${pageContext.request.contextPath}/deleteAirport?airportId=${a.airportId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%@ include file="footer.jsp" %>

</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title>Passenger Form</title>
    <style>
        .error{
            color:red;
            font-style:italic;
            font-weight:bold;
        }
    </style>
</head>

<body>
<%@ include file="menu.jsp" %>

<div align="center">
<h1>Passenger Form</h1>

<f:form action="savePassenger" method="post" modelAttribute="passenger">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="passengerId" value="${retrievedPassenger.passengerId}"/> </td>
    <td> <f:errors path="passengerId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>First Name:</b> </td>
    <td> <f:input path="firstName" value="${retrievedPassenger.firstName}"/> </td>
    <td> <f:errors path="firstName" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Last Name:</b> </td>
    <td> <f:input path="lastName" value="${retrievedPassenger.lastName}"/> </td>
    <td> <f:errors path="lastName" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Email:</b> </td>
    <td> <f:input path="email" value="${retrievedPassenger.email}"/> </td>
    <td> <f:errors path="email" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Phone:</b> </td>
    <td> <f:input path="phone" value="${retrievedPassenger.phone}"/> </td>
    <td> <f:errors path="phone" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Gender:</b> </td>
    <td> <f:input path="gender" value="${retrievedPassenger.gender}"/> </td>
    <td> <f:errors path="gender" cssClass="error"/> </td>
    </tr>

    <tr>
    <td> <b>Date of Birth:</b> </td>  <!-- A Calendar -->
    <td> <f:input type="date" path="dob" value="<%=java.time.LocalDate.now()%>"/> </td>
    <td> <f:errors path="dob" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Identification Type:</b> </td>
    <td>
        <c:forEach items="${identificationTypes}" var="t">
            <c:choose>
                <c:when test="${retrievedPassenger.identificationType.equals(t)}">
                    <f:radiobutton path="identificationType" value="${t}" label="${t}" checked="true" class="form-check-input"></f:radiobutton>
                </c:when>
                <c:otherwise>
                    <f:radiobutton path="identificationType" value="${t}" label="${t}" class="form-check-input"></f:radiobutton>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </td>
    <td> <f:errors path="identificationType" cssClass="error"/> </td>
    </tr>

    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Submit" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>

<div class=container-md>
<c:if test="${not empty passengers}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>ID</td> <td>First Name</td> <td>Last Name</td> <td>Email</td> 
            <td>Phone</td> <td>Gender</td> <td>DOB</td> <td>ID Type</td> <td>Action</td>
        </tr></thead>

        <c:forEach items="${passengers}" var="p">
            <tr>
            <td>${p.passengerId}</td> <td>${p.firstName}</td> <td>${p.lastName}</td> <td>${p.email}</td>
            <td>${p.phone}</td> <td>${p.gender}</td> <td>${p.dob}</td> <td>${p.identificationType}</td>
            <td> <a href="${pageContext.request.contextPath}/updatePassenger?passengerId=${p.passengerId}"> Update </a> | <a href="${pageContext.request.contextPath}/deletePassenger?passengerId=${p.passengerId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>

<%@ include file="footer.jsp" %>

</div>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>

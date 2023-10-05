<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title>My Trips</title>
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
<h1>My Trips</h1>

<f:form action="passengersReservations" method="get" modelAttribute="passenger">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>Passenger:</b> </td>
    <td>
    <f:select path="passengerId" class="form-select">
        <c:forEach items="${passengers}" var="p">
                <c:choose>
                    <c:when test="${selectedPassenger.equals(p)}">
                        <f:option value="${p.passengerId}" label="ID: ${p.passengerId}, Name: ${p.firstName} ${p.lastName}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${p.passengerId}" label="ID: ${p.passengerId}, Name: ${p.firstName} ${p.lastName}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="passengerId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Select" class="btn btn-primary"/> </td>
    </tr>
    
</table>
</f:form>

<br>
<div class=container-md>
<c:choose>
<c:when test="${not empty reservationsOfPassenger}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>Ticket Number</td> <td>Passenger ID</td> <td>First Name</td> <td>Last Name</td>
            <td>Flight ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
            <td>Checked In</td> <td>Issued Date</td>
            <td>Check In</td>
        </tr></thead>

        <c:forEach items="${reservationsOfPassenger}" var="r">
            <tr>
            <td>${r.ticketNum}</td> <td>${r.passenger.passengerId}</td> <td>${r.passenger.firstName}</td> <td>${r.passenger.lastName}</td>
            <td>${r.flight.flightId}</td> <td>${r.flight.flightNum}</td> <td>${r.flight.departureCity.airportCity}</td> <td>${r.flight.arrivalCity.airportCity}</td>
            <td>${r.checkedIn}</td> <td>${r.issuedDate}</td>
            <td> <a href="${pageContext.request.contextPath}/checkIn?ticketNum=${r.ticketNum}"> Check In </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:when>
<c:otherwise>
    <c:if test="${not empty selectedPassenger}">
        No reservation found for the passenger.
    </c:if>
</c:otherwise>
</c:choose>
</div>

<%@ include file="footer.jsp" %>

</div>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>

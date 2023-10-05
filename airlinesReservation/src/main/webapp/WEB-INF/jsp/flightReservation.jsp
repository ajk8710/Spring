<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title>Flight Reservation</title>
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
<h1>Save Passenger for Reservation</h1>

<f:form action="savePassengerForReservation" method="post" modelAttribute="passenger">  <!-- modelAttribute is name of class starting with lower case -->
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
                    <f:radiobutton path="identificationType" value="${t}" label="${t}" checked="true"></f:radiobutton>
                </c:when>
                <c:otherwise>
                    <f:radiobutton path="identificationType" value="${t}" label="${t}"></f:radiobutton>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </td>
    <td> <f:errors path="identificationType" cssClass="error"/> </td>
    </tr>

    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Save Passenger" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>

<br>
<div class=container-md>
<c:if test="${not empty retrievedPassenger}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>ID</td> <td>First Name</td> <td>Last Name</td> <td>Email</td> 
            <td>Phone</td> <td>Gender</td> <td>DOB</td> <td>ID Type</td> <td>Action</td>
        </tr></thead>
        <tr>
        <td>${retrievedPassenger.passengerId}</td> <td>${retrievedPassenger.firstName}</td>
        <td>${retrievedPassenger.lastName}</td> <td>${retrievedPassenger.email}</td>
        <td>${retrievedPassenger.phone}</td> <td>${retrievedPassenger.gender}</td>
        <td>${retrievedPassenger.dob}</td> <td>${retrievedPassenger.identificationType}</td>
        <td><a href="${pageContext.request.contextPath}/updatePassengerForReservation?passengerId=${retrievedPassenger.passengerId}"> Update </a></td>
        </tr>
    </table>
</c:if>
</div>

<br>
<div class=container-md>
<table border="1" class="table table-striped">
    <thead><tr>
        <td>ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
        <td>Price</td> <td>Capacity</td> <td>Booked</td> <td>Date</td>
        <td>Time</td> <td>Airlines</td>
    </tr></thead>
    <tr>
    <td>${selectedFlight.flightId}</td> <td>${selectedFlight.flightNum}</td>
    <td>${selectedFlight.departureCity.airportCity}</td> <td>${selectedFlight.arrivalCity.airportCity}</td>
    <td>${selectedFlight.ticketPrice}</td> <td>${selectedFlight.capacity}</td>
    <td>${selectedFlight.booked}</td> <td>${selectedFlight.departureDate}</td>
    <td>${selectedFlight.departureTime}</td> <td>${selectedFlight.operatingAirlines.airlinesName}</td>
    </tr>
</table>
</div>

<br>
<c:if test="${not empty retrievedPassenger && not empty selectedFlight}">
<td><a href="${pageContext.request.contextPath}/makeReservation?passengerId=${retrievedPassenger.passengerId}&flightId=${selectedFlight.flightId}" class="btn btn-primary"> Reserve </a></td>
</c:if>

<%@ include file="footer.jsp" %>

</div>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>

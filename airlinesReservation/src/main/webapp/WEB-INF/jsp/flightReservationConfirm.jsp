<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Reservation Confirmation</title>
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

<c:choose>
<c:when test="${reservationSuccess}">

    <h1>Reservation Confirmation</h1>
    
    <br>
    <c:if test="${not empty retrievedPassenger}">
        <table border="1">
            <thead><tr>
                <td>ID</td> <td>First Name</td> <td>Last Name</td> <td>Email</td> 
                <td>Phone</td> <td>Gender</td> <td>DOB</td> <td>ID Type</td>
            </tr></thead>
            <tr>
            <td>${retrievedPassenger.passengerId}</td> <td>${retrievedPassenger.firstName}</td>
            <td>${retrievedPassenger.lastName}</td> <td>${retrievedPassenger.email}</td>
            <td>${retrievedPassenger.phone}</td> <td>${retrievedPassenger.gender}</td>
            <td>${retrievedPassenger.dob}</td> <td>${retrievedPassenger.identificationType}</td>
            </tr>
        </table>
    </c:if>
    
    <br>
    <table border="1">
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
    
    <br>
    <table border="1">
    <thead><tr>
        <td>Ticket Number</td> <td>Passenger ID</td> <td>First Name</td> <td>Last Name</td>
        <td>Flight ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
        <td>Checked In</td> <td>Issued Date</td>
    </tr></thead>
    <tr>
    <td>${rsv.ticketNum}</td> <td>${rsv.passenger.passengerId}</td> <td>${rsv.passenger.firstName}</td> <td>${rsv.passenger.lastName}</td>
    <td>${rsv.flight.flightId}</td> <td>${rsv.flight.flightNum}</td> <td>${rsv.flight.departureCity.airportCity}</td> <td>${rsv.flight.arrivalCity.airportCity}</td>
    <td>${rsv.checkedIn}</td> <td>${rsv.issuedDate}</td>
    </tr>
    </table>

</c:when>
<c:otherwise>
    <h1>Sorry, Reservation was not successful. It may be full</h1>
</c:otherwise>
</c:choose>

<%@ include file="footer.jsp" %>

</div>
</body>
</html>

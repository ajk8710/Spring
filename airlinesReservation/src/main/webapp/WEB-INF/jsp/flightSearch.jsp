<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title>Find Your Trip</title>
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
<h1>Find Your Trip</h1>

<f:form action="flightSearchResults" method="get" modelAttribute="flight">  <!-- modelAttribute is name of class starting with lower case -->
<table>
    
    <tr>
    <td> <b>Departure City:</b> </td>
    <td>
    <f:select path="departureCity" class="form-select">
        <c:forEach items="${airports}" var="a">
                <c:choose>
                    <c:when test="${selectedDepartureCity.equals(a)}">
                        <f:option value="${a.airportId}" label="${a.airportCity}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${a.airportId}" label="${a.airportCity}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="departureCity" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Arrival City:</b> </td>
    <td>
    <f:select path="arrivalCity" class="form-select">
        <c:forEach items="${airports}" var="a">
                <c:choose>
                    <c:when test="${selectedArrivalCity.equals(a)}">
                        <f:option value="${a.airportId}" label="${a.airportCity}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${a.airportId}" label="${a.airportCity}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="arrivalCity" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Search" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>

<div class=container-md>
<c:if test="${not empty listOfSearchedFlights}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
            <td>Price</td> <td>Capacity</td> <td>Booked</td> <td>Date</td>
            <td>Time</td> <td>Airlines</td> <td>Reserve</td>
        </tr></thead>

        <c:forEach items="${listOfSearchedFlights}" var="f">
            <tr>
            <td>${f.flightId}</td> <td>${f.flightNum}</td> <td>${f.departureCity.airportCity}</td> <td>${f.arrivalCity.airportCity}</td>
            <td>${f.ticketPrice}</td> <td>${f.capacity}</td> <td>${f.booked}</td> <td>${f.departureDate}</td>
            <td>${f.departureTime}</td> <td>${f.operatingAirlines.airlinesName}</td>
            <td><a href="${pageContext.request.contextPath}/flightReservation?flightId=${f.flightId}"> Reserve </a></td>
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

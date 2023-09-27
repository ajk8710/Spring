<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Flight Search</title>
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
<h1>Flight Search</h1>

<f:form action="flightSearchResults" method="get" modelAttribute="flight">  <!-- modelAttribute is name of class starting with lower case -->
<table>
    
    <tr>
    <td> <b>Departure City:</b> </td>
    <td>
    <f:select path="departureCity">
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
    <f:select path="arrivalCity">
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
    <td colspan="3" align="center"> <input type="submit" value="Search"/> </td>
    </tr>

</table>
</f:form>

<c:if test="${not empty listOfSearchedFlights}">
    <table border="1">
        <thead><tr>
            <td>ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
            <td>Price</td> <td>Capacity</td> <td>Booked</td> <td>Date</td>
            <td>Time</td> <td>Airlines</td>
        </tr></thead>

        <c:forEach items="${listOfSearchedFlights}" var="f">
            <tr>
            <td>${f.flightId}</td> <td>${f.flightNum}</td> <td>${f.departureCity.airportCity}</td> <td>${f.arrivalCity.airportCity}</td>
            <td>${f.ticketPrice}</td> <td>${f.capacity}</td> <td>${f.booked}</td> <td>${f.departureDate}</td>
            <td>${f.departureTime}</td> <td>${f.operatingAirlines.airlinesName}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%@ include file="footer.jsp" %>

</div>
</body>
</html>

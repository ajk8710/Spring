<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Flight Form</title>
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
<h1>Flight Form</h1>

<f:form action="saveFlight" method="post" modelAttribute="flight">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="flightId" value="${retrievedFlight.flightId}"/> </td>
    <td> <f:errors path="flightId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Flight Number:</b> </td>
    <td> <f:input path="flightNum" value="${retrievedFlight.flightNum}"/> </td>
    <td> <f:errors path="flightNum" cssClass="error"/> </td>
    </tr>
    
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
    <td> <b>Ticket Price:</b> </td>
    <td> <f:input path="ticketPrice" value="${retrievedFlight.ticketPrice}"/> </td>
    <td> <f:errors path="ticketPrice" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Capacity:</b> </td>
    <td> <f:input path="capacity" value="${retrievedFlight.capacity}"/> </td>
    <td> <f:errors path="capacity" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Booked Count:</b> </td>
    <td> <f:input path="booked" value="${retrievedFlight.booked}"/> </td>
    <td> <f:errors path="booked" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Departure Date:</b> </td>  <!-- A Calendar -->
    <td> <f:input type="date" path="departureDate" value="<%=java.time.LocalDate.now()%>"/> </td>
    <td> <f:errors path="departureDate" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Departure Time:</b> </td>
    <td> <f:input type="time" path="departureTime" value="<%=java.time.LocalTime.now()%>"/> </td>
    <td> <f:errors path="departureTime" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Operating Airlines:</b> </td>
    <td>
    <f:select path="operatingAirlines">
        <c:forEach items="${airlinesList}" var="a">
                <c:choose>
                    <c:when test="${selectedAirlines.equals(a)}">
                        <f:option value="${a.airlinesId}" label="${a.airlinesName}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${a.airlinesId}" label="${a.airlinesName}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="operatingAirlines" cssClass="error"/> </td>
    </tr>

    <tr>
    <td colspan="3" align="center"> <input type="submit" value="Submit"/> </td>
    </tr>

</table>
</f:form>

<c:if test="${not empty flights}">
    <table border="1">
        <thead><tr>
            <td>ID</td> <td>Flight Number</td> <td>Departure</td> <td>Arrival</td>
            <td>Price</td> <td>Capacity</td> <td>Booked</td> <td>Date</td>
            <td>Time</td> <td>Airlines</td>
            <td>Action</td>
        </tr></thead>

        <c:forEach items="${flights}" var="f">
            <tr>
            <td>${f.flightId}</td> <td>${f.flightNum}</td> <td>${f.departureCity.airportCity}</td> <td>${f.arrivalCity.airportCity}</td>
            <td>${f.ticketPrice}</td> <td>${f.capacity}</td> <td>${f.booked}</td> <td>${f.departureDate}</td>
            <td>${f.departureTime}</td> <td>${f.operatingAirlines.airlinesName}</td>
            <td> <a href="${pageContext.request.contextPath}/updateFlight?flightId=${f.flightId}"> Update </a> | <a href="${pageContext.request.contextPath}/deleteFlight?flightId=${f.flightId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%@ include file="footer.jsp" %>

</div>
</body>
</html>

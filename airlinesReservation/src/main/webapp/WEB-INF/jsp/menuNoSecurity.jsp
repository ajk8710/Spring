<%--
<div align="center">
<div class="row">
<table>
<tr>
<td><a href="${pageContext.request.contextPath}/">Home</a></td>
<td><a href="${pageContext.request.contextPath}/userForm">User Form</a></td>
<td><a href="${pageContext.request.contextPath}/roleForm">Role Form</a></td>
<td><a href="${pageContext.request.contextPath}/passengerForm">Passenger Form</a></td>
<td><a href="${pageContext.request.contextPath}/airlinesForm">Airlines Form</a></td>
<td><a href="${pageContext.request.contextPath}/airportForm">Airport Form</a></td>
<td><a href="${pageContext.request.contextPath}/flightForm">Flight Form</a></td>
<td><a href="${pageContext.request.contextPath}/reservationForm">Reservation Form</a></td>
</tr>
</table>
</div>

<div align="center" class="row">
<table>
<tr>
<td><a href="${pageContext.request.contextPath}/flightSearch">Find Your Trip</a></td>
<td><a href="${pageContext.request.contextPath}/myReservations">My Trips</a></td>
<td><a href="logout">Sign Out</a></td>
</tr>
</table>
</div>
</div>
--%>

<style>
    a.nav-link {
        display: flex;
        align-items: center;
    }
    a.navbar-brand {
        display: flex;
        align-items: center;
    }
</style>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#"><img src="images/airplane.jpg" alt="airplane" width="24" height="24"> Menu</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/"><img src="icons/house.svg" alt="home" width="18" height="18"> Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="${pageContext.request.contextPath}/flightSearch"><img src="icons/airplane.svg" alt="flight" width="18" height="18"> Find Your Trip</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="${pageContext.request.contextPath}/myReservations"><img src="icons/suitcase-lg.svg" alt="trips" width="18" height="18"> My Trips</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="logout"><img src="icons/power.svg" alt="sign out" width="18" height="18"> Sign Out</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link active dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="icons/file-text.svg" alt="forms" width="18" height="18"> Forms
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/userForm">User Form</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/roleForm">Role Form</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/passengerForm">Passenger Form</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/airlinesForm">Airlines Form</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/airportForm">Airport Form</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/flightForm">Flight Form</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reservationForm">Reservation Form</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>

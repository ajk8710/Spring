<%--
<div align="center">
<table>
<tr>

<td><a href="/">Home</a></td>

<td><a href="userForm">User Form</a></td>

<sec:authorize access="hasAuthority('Admin')">  <!-- sec tag available in header.jsp -->
<td><a href="roleForm">Role Form</a></td>
</sec:authorize>

<td><a href="customerForm">Customer Form</a></td>

<sec:authorize access="hasAuthority('Admin')">
<td><a href="branchForm">Branch Form</a></td>
</sec:authorize>

<td><a href="accountForm">Accounts</a></td>
<td><a href="depositForm">Deposit</a></td>
<td><a href="withdrawalForm">Withdrawal</a></td>
<td><a href="transferForm">Transfer</a></td>

<td><a href="logout">Log-Out</a></td>

</tr>
</table>
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
    <a class="navbar-brand" href="#"><img src="images/bank.png" alt="bank" width="24" height="24"> Menu</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/"><img src="icons/house.svg" alt="home" width="18" height="18"> Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="${pageContext.request.contextPath}/depositForm"><img src="icons/piggy-bank.svg" alt="deposit" width="18" height="18"> Deposit</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="${pageContext.request.contextPath}/withdrawalForm"><img src="icons/piggy-bank-fill.svg" alt="withdrawal" width="18" height="18"> Withdrawal</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="${pageContext.request.contextPath}/transferForm"><img src="icons/cash.svg" alt="transfer" width="18" height="18"> Transfer</a>
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
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/customerForm">Customer Form</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/branchForm">Branch Form</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/accountForm">Account Form</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/bankTransactionForm">Transaction Form</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>

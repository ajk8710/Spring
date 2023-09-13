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

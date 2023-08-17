<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>== Account Form ==</title>
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
<h1>Account Management Form</h1>

<f:form action="saveAccount" method="post" modelAttribute="account">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID (auto-generated if not specify):</b> </td>
    <td> <f:input path="accountId" value="${retrievedAccount.accountId}"/> </td>
    <td> <f:errors path="accountId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Account Holder:</b> </td>
    <td> <f:input path="accountHolder" value="${retrievedAccount.accountHolder}"/> </td>
    <td> <f:errors path="accountHolder" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Account Type:</b> </td>
    <td>
        <c:forEach items="${accountTypes}" var="t">
            <c:choose>
                <c:when test="${selectedAccountType.equals(t)}">
                    <f:radiobutton path="accountType" value="${t}" label="${t}" checked="true"></f:radiobutton>
                </c:when>
                <c:otherwise>
                    <f:radiobutton path="accountType" value="${t}" label="${t}"></f:radiobutton>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </td>
    <td> <f:errors path="accountType" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Date Open:</b> </td>  <!-- A Calendar -->
    <td> <f:input type="date" path="accountDateOpen" value="<%=java.time.LocalDate.now()%>"/> </td>
    <td> <f:errors path="accountDateOpen" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Balance:</b> </td>
    <td> <f:input path="accountBalance" value="${retrievedAccount.accountBalance}"/> </td>
    <td> <f:errors path="accountBalance" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Select a Branch:</b> </td>
    <td>
    <f:select path="accountBranch">
        <c:forEach items="${ListofAllBranches}" var="b">
                <c:choose>
                    <c:when test="${selectedBranch.equals(b)}">
                        <f:option value="${b.branchId}" label="${b.branchName}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${b.branchId}" label="${b.branchName}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="accountBranch" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Select a Customer:</b> </td>
    <td>
    <f:select path="accountCustomer">
        <c:forEach items="${ListofAllCustomers}" var="c">
                <c:choose>
                    <c:when test="${selectedCustomer.equals(c)}">
                        <f:option value="${c.customerId}" label="${c.customerName}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${c.customerId}" label="${c.customerName}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="accountCustomer" cssClass="error"/> </td>
    </tr>

    <tr>
    <td colspan="2" align="center"> <input type="submit" value="Submit"/> </td>
    </tr>

</table>
</f:form>

<c:if test="${not empty accounts}">
    <table border="1">
        <thead><tr>
            <td>ID</td> <td>Account Holder</td> <td>Account Type</td> <td>Date Open</td> <td>Balance</td>
            <td>Branch</td> <td>Customer</td> <td>Action</td>
        </tr></thead>

        <c:forEach items="${accounts}" var="a">
            <tr>
            <td>${a.accountId}</td> <td>${a.accountHolder}</td> <td>${a.accountType}</td> <td>${a.accountDateOpen}</td> <td>${a.accountBalance}</td>
            <td>${a.accountBranch.branchName}</td> <td>${a.accountCustomer.customerName}</td>
            <td> <a href="updateAccount?accountId=${a.accountId}"> Update </a> | <a href="deleteAccount?accountId=${a.accountId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</div>
</body>
</html>

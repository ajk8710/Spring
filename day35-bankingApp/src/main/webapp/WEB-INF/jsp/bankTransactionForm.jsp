<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>== Bank Transaction Form ==</title>
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
<h1>Bank Transaction Management Form</h1>

<f:form action="saveBankTransaction" method="post" modelAttribute="bankTransaction">  <!-- modelAttribute is name of class starting with lower case -->
<table>
    
    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="bankTransactionId" value="${retrievedBankTransaction.bankTransactionId}"/> </td>
    <td> <f:errors path="bankTransactionId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Select an Account From:</b> </td>
    <td>
    <f:select path="bankTransactionFromAccount">
        <c:forEach items="${ListofAllAccounts}" var="a">
            <c:choose>
                <c:when test="${retrievedBankTransaction.bankTransactionFromAccount.equals(a.accountId)}">  <!-- bankTransactionFromAccount is an integer, so checking equality with id -->
                    <f:option value="${a.accountId}" label="${a.accountHolder}" selected="true"></f:option>
                </c:when>
                <c:otherwise>
                    <f:option value="${a.accountId}" label="${a.accountHolder}"></f:option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="bankTransactionFromAccount" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Select an Account To:</b> </td>
    <td>
    <f:select path="bankTransactionToAccount">
        <c:forEach items="${ListofAllAccounts}" var="a">
            <c:choose>
                <c:when test="${retrievedBankTransaction.bankTransactionToAccount.equals(a.accountId)}">  <!-- bankTransactionToAccount is an integer, so checking equality with id -->
                    <f:option value="${a.accountId}" label="${a.accountHolder}" selected="true"></f:option>
                </c:when>
                <c:otherwise>
                    <f:option value="${a.accountId}" label="${a.accountHolder}"></f:option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="bankTransactionToAccount" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Transaction Amount:</b> </td>
    <td> <f:input path="transactionAmount" value="${retrievedBankTransaction.transactionAmount}"/> </td>
    <td> <f:errors path="transactionAmount" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Transaction Type:</b> </td>
    <td>
        <c:forEach items="${transactionTypes}" var="t">
            <c:choose>
                <c:when test="${retrievedBankTransaction.transactionType.equals(t)}">
                    <f:radiobutton path="transactionType" value="${t}" label="${t}" checked="true"></f:radiobutton>
                </c:when>
                <c:otherwise>
                    <f:radiobutton path="transactionType" value="${t}" label="${t}"></f:radiobutton>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </td>
    <td> <f:errors path="transactionType" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Transaction Date:</b> </td>  <!-- A Calendar -->
    <td> <f:input type="datetime-local" path="bankTransactionDateTime" value="<%=java.time.LocalDateTime.now()%>"/> </td>
    <td> <f:errors path="bankTransactionDateTime" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Comments:</b> </td>
    <td> <f:input path="comments" value="${retrievedBankTransaction.comments}"/> </td>
    <td> <f:errors path="comments" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td colspan="2" align="center"> <input type="submit" value="Submit"/> </td>
    </tr>

</table>
</f:form>

<c:if test="${not empty transactions}">
    <table border="1">
        <thead><tr>
            <td>ID</td> <td>Account From</td> <td>Account To</td> <td>Amount</td> <td>Type</td>
            <td>Date</td> <td>Comments</td> <td>Action</td>
        </tr></thead>

        <c:forEach items="${transactions}" var="t">
            <tr>
            <td>${t.bankTransactionId}</td> <td>${t.bankTransactionFromAccount}</td> <td>${t.bankTransactionToAccount}</td> <td>${t.transactionAmount}</td> <td>${t.transactionType}</td>
            <td>${t.bankTransactionDateTime}</td> <td>${t.comments}</td>
            <td> <a href="updateBankTransaction?bankTransactionId=${t.bankTransactionId}"> Update </a> | <a href="deleteBankTransaction?bankTransactionId=${t.bankTransactionId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</div>
</body>
</html>

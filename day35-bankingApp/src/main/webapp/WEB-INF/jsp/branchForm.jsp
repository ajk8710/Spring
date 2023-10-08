<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="common/header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title>== Branch Form ==</title>
    <style>
        .error{
            color:red;
            font-style:italic;
            font-weight:bold;
        }
    </style>
</head>

<body>
<%@ include file="common/menu.jsp" %>

<div align="center">
<h1>Branch Management Form</h1>

<f:form action="saveBranch" method="post" modelAttribute="branch">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="branchId" value="${retrievedBranch.branchId}"/> </td>
    <td> <f:errors path="branchId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Branch Name:</b> </td>
    <td> <f:input path="branchName" value="${retrievedBranch.branchName}"/> </td>
    <td> <f:errors path="branchName" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td colspan="3" align="center"><strong>Branch Address</strong></td>
    </tr>

    <tr>
    <td>Address Line1:</td>
    <td><f:input path="branchAddress.addressLine1" value="${retrievedBranch.branchAddress.addressLine1}"/></td>
    <td><f:errors path="branchAddress.addressLine1" cssClass="error"/></td>
    </tr>

    <tr>
    <td>Address Line2:</td>
    <td><f:input path="branchAddress.addressLine2" value="${retrievedBranch.branchAddress.addressLine2}"/></td>
    <td><f:errors path="branchAddress.addressLine2" cssClass="error"/></td>
    </tr>

    <tr>
    <td>City:</td>
    <td><f:input path="branchAddress.city" value="${retrievedBranch.branchAddress.city}"/></td>
    <td><f:errors path="branchAddress.city" cssClass="error"/></td>
    </tr>

    <tr>
    <td>State:</td>
    <td><f:input path="branchAddress.state" value="${retrievedBranch.branchAddress.state}"/></td>
    <td><f:errors path="branchAddress.state" cssClass="error"/></td>
    </tr>

    <tr>
    <td>Country:</td>
    <td><f:input path="branchAddress.country" value="${retrievedBranch.branchAddress.country}"/></td>
    <td><f:errors path="branchAddress.country" cssClass="error"/></td>
    </tr>

    <tr>
    <td>Zip Code:</td>
    <td><f:input path="branchAddress.zipCode" value="${retrievedBranch.branchAddress.zipCode}"/></td>
    <td><f:errors path="branchAddress.zipCode" cssClass="error"/></td>
    </tr>

    <tr>
    <td colspan="2" align="center"> <input type="submit" value="Submit" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>

<div class=container-md>
<c:if test="${not empty branches}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>ID</td> <td>Name</td> <td>City & State</td> <td>Action</td>
        </tr></thead>

        <c:forEach items="${branches}" var="b">
            <tr>
            <td>${b.branchId}</td> <td>${b.branchName}</td> <td>${b.branchAddress.city}, ${b.branchAddress.state}</td>
            <td> <a href="updateBranch?branchId=${b.branchId}"> Update </a> | <a href="deleteBranch?branchId=${b.branchId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>

</div>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>

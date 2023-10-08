<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="common/header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title>== Customer Form ==</title>
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
<h1>Customer Management Form</h1>

<f:form action="saveCustomer" method="post" modelAttribute="customer">  <!-- modelAttribute is name of class starting with lower case -->
<table>

    <tr>
    <td> <b>ID:</b> </td>
    <td> <f:input path="customerId" value="${retrievedCustomer.customerId}"/> </td>
    <td> <f:errors path="customerId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Name:</b> </td>
    <td> <f:input path="customerName" value="${retrievedCustomer.customerName}"/> </td>
    <td> <f:errors path="customerName" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Gender:</b> </td>
    <td> <f:input path="customerGender" value="${retrievedCustomer.customerGender}"/> </td>
    <td> <f:errors path="customerGender" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Date of Birth:</b> </td>  <!-- A Calendar -->
    <td> <f:input type="date" path="customerDob" value="<%=java.time.LocalDate.now()%>"/> </td>
    <td> <f:errors path="customerDob" cssClass="error"/> </td>
    </tr>

    <tr>
    <td> <b>Mobile:</b> </td>
    <td> <f:input path="customerMobileNum" value="${retrievedCustomer.customerMobileNum}"/> </td>
    <td> <f:errors path="customerMobileNum" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Real ID:</b> </td>
    <td> <f:input path="realId" value="${retrievedCustomer.realId}"/> </td>
    <td> <f:errors path="realId" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td> <b>Select a User:</b> </td>
    <td>
    <f:select path="user">
        <c:forEach items="${ListofAllUsers}" var="u">
                <c:choose>
                    <c:when test="${selectedUser.equals(u)}">
                        <f:option value="${u.userId}" label="${u.username}" selected="true"></f:option>
                    </c:when>
                    <c:otherwise>
                        <f:option value="${u.userId}" label="${u.username}"></f:option>
                    </c:otherwise>
                </c:choose>
        </c:forEach>
    </f:select>
    </td>
    <td> <f:errors path="user" cssClass="error"/> </td>
    </tr>
    
    <tr>
    <td colspan="3" align="center"><strong>Address</strong></td>
    </tr>

    <tr>
    <td>Address Line1:</td>
    <td><f:input path="customerAddress.addressLine1" value="${retrievedCustomer.customerAddress.addressLine1}"/></td>
    <td><f:errors path="customerAddress.addressLine1" cssClass="error"/></td>
    </tr>

    <tr>
    <td>Address Line2:</td>
    <td><f:input path="customerAddress.addressLine2" value="${retrievedCustomer.customerAddress.addressLine2}"/></td>
    <td><f:errors path="customerAddress.addressLine2" cssClass="error"/></td>
    </tr>

    <tr>
    <td>City:</td>
    <td><f:input path="customerAddress.city" value="${retrievedCustomer.customerAddress.city}"/></td>
    <td><f:errors path="customerAddress.city" cssClass="error"/></td>
    </tr>

    <tr>
    <td>State:</td>
    <td><f:input path="customerAddress.state" value="${retrievedCustomer.customerAddress.state}"/></td>
    <td><f:errors path="customerAddress.state" cssClass="error"/></td>
    </tr>

    <tr>
    <td>Country:</td>
    <td><f:input path="customerAddress.country" value="${retrievedCustomer.customerAddress.country}"/></td>
    <td><f:errors path="customerAddress.country" cssClass="error"/></td>
    </tr>

    <tr>
    <td>Zip Code:</td>
    <td><f:input path="customerAddress.zipCode" value="${retrievedCustomer.customerAddress.zipCode}"/></td>
    <td><f:errors path="customerAddress.zipCode" cssClass="error"/></td>
    </tr>

    <tr>
    <td colspan="2" align="center"> <input type="submit" value="Submit" class="btn btn-primary"/> </td>
    </tr>

</table>
</f:form>

<div class=container-md>
<c:if test="${not empty customers}">
    <table border="1" class="table table-striped">
        <thead><tr>
            <td>ID</td> <td>Name</td> <td>Gender</td> <td>DOB</td> 
            <td>Mobile</td> <td>Real ID</td> <td>Username</td> <td>City & State</td> <td>Action</td>
        </tr></thead>

        <c:forEach items="${customers}" var="c">
            <tr>
            <td>${c.customerId}</td> <td>${c.customerName}</td> <td>${c.customerGender}</td> <td>${c.customerDob}</td>
            <td>${c.customerMobileNum}</td> <td>${c.realId}</td> <td>${c.user.username}</td> <td>${c.customerAddress.city}, ${c.customerAddress.state}</td>
            <td> <a href="updateCustomer?customerId=${c.customerId}"> Update </a> | <a href="deleteCustomer?customerId=${c.customerId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</div>

</div>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>

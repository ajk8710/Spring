<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- tag library provided by JSP JSTL -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %> <!-- tag library provided by spring -->
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
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
    <td><f:input type="date" path="customerDob" value="<%=java.time.LocalDate.now()%>"/></td>
    <td><f:errors path="customerDob" cssClass="error"/></td>
    </tr>

    <tr>
    <td> <b>Mobile:</b> </td>
    <td><f:input path="customerMobileNum" value="${retrievedCustomer.customerMobileNum}"/></td>
    <td><f:errors path="customerMobileNum" cssClass="error"/></td>
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
                    <c:when test="${selectedUser.contains(u)}">
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
    <td><f:input path="customerAddress.addressLine1"/></td>
    <td><f:errors path="customerAddress.addressLine1" cssClass="error"/></td>
    </tr>

    <tr>
    <td>Address Line2:</td>
    <td><f:input path="customerAddress.addressLine2"/></td>
    <td><f:errors path="customerAddress.addressLine2" cssClass="error"/></td>
    </tr>

    <tr>
    <td>City:</td>
    <td><f:input path="customerAddress.city"/></td>
    <td><f:errors path="customerAddress.city" cssClass="error"/></td>
    </tr>

    <tr>
    <td>State:</td>
    <td><f:input path="customerAddress.state"/></td>
    <td><f:errors path="customerAddress.state" cssClass="error"/></td>
    </tr>

    <tr>
    <td>Country:</td>
    <td><f:input path="customerAddress.country"/></td>
    <td><f:errors path="customerAddress.country" cssClass="error"/></td>
    </tr>

    <tr>
    <td>Zip Code:</td>
    <td><f:input path="customerAddress.zipCode"/></td>
    <td><f:errors path="customerAddress.zipCode" cssClass="error"/></td>
    </tr>

    <tr>
    <td colspan="2" align="center"> <input type="submit" value="Submit"/> </td>
    </tr>

</table>
</f:form>

<c:if test="${not empty customers}">
    <table border="1">
        <thead><tr>
            <td>ID</td> <td>Name</td> <td>Gender</td> <td>DOB</td> 
            <td>Mobile</td> <td>Real ID</td> <td>City & State</td> <td>Username</td> <td>Action</td>
        </tr></thead>

        <c:forEach items="${customers}" var="c">
            <tr>
            <td>${c.customerId}</td> <td>${c.customerName}</td> <td>${c.customerGender}</td> <td>${c.customerDob}</td>
            <td>${c.customerMobileNum}</td> <td>${c.realId}</td> <td>${c.customerAddress.city}, ${c.customerAddress.state}</td> <td>${c.user.username}</td>
            <td> <a href="updateCustomer?customerId=${c.customerId}"> Update </a> | <a href="deleteCustomer?customerId=${c.customerId}"> Delete </a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Log In</title>
</head>

<body>
<div align="center">
<h1>Log In</h1>

<img src="images/Grapes.jpg" alt="picture of grape" style="height:3m; width:3cm;"/><br>

<form action="login" method="POST">

<table>

    <tr>
    <td> Name: </td>
    <td> <input type="text" name="username"/> </td>
    </tr>
    
    <tr>
    <td> Password: </td>
    <td> <input type="password" name="password"/> </td>
    </tr>
    
    <tr>
    <td colspan="2" align="center"> <input type="submit" value="Submit" class="btn btn-primary"/> </td>
    </tr>

</table>

</form>

<br/>${messageAfterLogInOut}

<%@ include file="footer.jsp" %>
</div>
</body>
</html>

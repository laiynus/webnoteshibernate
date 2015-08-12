<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <style type="text/css">
        #login-box {
            width: 400px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
    </style>
</head>
<body>
<h1 align="center">Registration page of web notes</h1>
<div id="login-box">
    <c:if test="${not empty param.message}">
        <div>${param.message}</div>
    </c:if>
    <form action="WebNotes" method="post" name="registrationForm">
        <INPUT type="hidden" name="command" value="registration">
        <table>
            <tr>
                <td> Username:</td>
                <td><input type="text" name="login" id="login" required="true"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" id="password" required="true"></td>
            </tr>
            <tr>
                <td>Enter password again:</td>
                <td><input type="password" name="reenterpassword" id="reenterpassword" required="true"></td>
            </tr>
            <tr>
                <td><a href="login.jsp">Already a member?</a></td>
            </tr>
            <tr>
                <td colspan='2'><input type="submit" value="Submit"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>

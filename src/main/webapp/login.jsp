<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Web notes</title>
    <style type="text/css">
        #login-box {
            width: 300px;
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
<h1 align="center">Login page of web notes</h1>

<div id="login-box">
    <c:if test="${not empty param.message}">
        <div>${param.message}</div>
    </c:if>
    <form action="WebNotes" method="post" name="loginForm">
        <INPUT type="hidden" name="command" value="login">
        <table>
            <tr>
                <td> Username:</td>
                <td><input type="text" name="login" id="login"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" id="password"></td>
            </tr>
            <tr>
                <td><a href="registration.jsp">Registration here!</a></td>
            </tr>
            <tr>
                <td colspan='2'><input type="submit" value="Submit"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>

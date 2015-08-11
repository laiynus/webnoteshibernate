<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${acptLogin==null}">
    <c:redirect url="login.jsp"/>
</c:if>
<html>
<head>
<title>Yor are already login</title>
</head>
<body>
<h1>Yor are already login</h1>
<h2>Hello, ${acptLogin}!</h2>

<form action="WebNotes" method="post">
    <INPUT type="hidden" name="command" value="logout">
    <input type="submit" value="Log Out">
</form>
<a href="index.jsp">Your last notes.</a>
</body>
</html>

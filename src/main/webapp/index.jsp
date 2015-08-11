<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Last notes</title>
    <jsp:include page="/WebNotes?command=table"/>
    <script type="text/javascript">
        function getNote(tmp,id){
           console.log(tmp);
           document.getElementById("note").innerHTML = tmp;
           document.getElementById("editButton").disabled  = false;
           document.getElementById("idEdit").value  = id;
        }
    </script>
</head>
<body>
<h2>Hello, ${acptLogin}!</h2>

<form action="WebNotes" method="post">
    <INPUT type="hidden" name="command" value="logout">
    <input type="submit" value="Log Out">
</form>
<form action="WebNotes" method="post">


    <p><b>Enter note:</b></p>

    <p><textarea rows="10" cols="45" name="note" required="true" id="note"></textarea></p>

    <p><input type="submit" value="Add" name="submit"></p>
    <p><input type="hidden" name="idEdit" id="idEdit">
    <p><input type="submit" value="Edit" disabled="true" id="editButton" name="submit">
</form>


<table border="1">
    <tr>
        <th>Note</th>
        <th>Date of change</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listNote}" var="listNotes">

        <tr>
            <td height="50" width="600" id="${listNotes.id}"
                    >${listNotes.note}</td>
            <td>${listNotes.dateTimeCreate}</td>
            <td>
                <form action="WebNotes?command=delete&id=${listNotes.id}" method="post">
                    <INPUT type="hidden" name="command" value="delete">
                    <input type="submit" value="Delete">
                </form>
                <input type="button" value="Select" name="${listNotes.id}" onclick='getNote(document.getElementById(this.name).innerHTML,this.name)'/>
        </tr>
    </c:forEach>
</table>
<a href="allnotes.jsp" align="left">All your notes.</a>
</body>
</html>

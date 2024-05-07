<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
     <form action="/confer" method="post" >
            <label for="eventID">Event Id</label>
            <input type="text" name="eventID" id="eventID" placeholder="Event id.."/>

            <label for="studentID">Student Id</label>
            <input type="text" name="studentID" id="studentID" placeholder="Student id.."/>

            <label for="awardID">Award Id</label>
            <input type="text" name="awardID" id="awardID" placeholder="Award id.."/>

            <input type="submit" value="Submit">
     </form:form>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/allUsers.tld" prefix="allUsers" %>
<html>
<head>
    <title>all users</title>
</head>
<body>

    ${allUsers:getAllUsers(requestScope["users"])}
</body>
</html>


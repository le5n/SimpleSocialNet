<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/allUsers.tld" prefix="allUsers" %>
<html>
<head>
    <title>all users</title>
</head>
<body>
<div align="right">
    <form action="/logout" method="POST">
        <button type="submit">logout</button>
    </form>
</div>
<div align="left">
    <form action="/page/">
        <button type="submit">my page</button>
    </form>
</div>
<div align="middle">
    ${allUsers:getAllUsers(requestScope["users"])}
</div>
</body>
</html>


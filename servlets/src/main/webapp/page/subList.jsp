<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/subscribesList.tld" prefix="followersList" %>
<html>
<head>
    <title>Your subscribes</title>
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
    <span>Subscribes</span>
    ${followersList:getSubscribesList(requestScope["subscribes"])}
</div>
</body>
</html>

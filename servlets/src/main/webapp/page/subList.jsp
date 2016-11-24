<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/subscribesList.tld" prefix="followersList" %>
<html>
<head>
    <title>Your subscribes</title>
</head>
<body>
<span>Subscribes</span>
${followersList:getSubscribesList(requestScope["subscribes"])}
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/subscribesList.tld" prefix="followersList" %>
<html>
<head>
    <title>Followers</title>
</head>
<body>
<span>Followers</span>
${followersList:getSubscribesList(requestScope["followers"])}
</body>
</html>
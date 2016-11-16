<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/followersList.tld" prefix="followersList" %>
<html>
<head>
    <title>Your followers</title>
</head>
<body>
<span>You follow</span>
${followersList:getFollowersList(requestScope["followers"])}
</body>
</html>

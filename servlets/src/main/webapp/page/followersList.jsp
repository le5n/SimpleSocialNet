<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 16.11.2016
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/followersList.tld" prefix="followersList" %>
<html>
<head>
    <title>Your followers</title>
</head>
<body>
<span>Your followers:</span>
${followersList:getFollowersList(requestScope["followers"])}
</body>
</html>

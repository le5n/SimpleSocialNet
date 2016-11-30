<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/subscribesList.tld" prefix="followersList" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Followers</title>
</head>
<body>
<fmt:setBundle basename="Bundle"/>
<fmt:message key="followers" var="followers"/>
<fmt:message key="logout" var="logout"/>
<fmt:message key="myPage" var="myPage"/>
<div align="right">
    <form action="/logout" method="POST">
        <button type="submit">${logout}</button>
    </form>
</div>
<div align="left">
    <form action="/page/">
        <button type="submit">${myPage}</button>
    </form>
</div>
<div align="middle">
    <span>${followers}</span>
    ${followersList:getSubscribesList(requestScope["followers"])}
</div>
</body>
</html>
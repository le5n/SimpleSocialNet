<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/subscribesList.tld" prefix="followersList" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Your subscribes</title>
</head>
<body>
<fmt:setBundle basename="Bundle"/>
<fmt:message key="subscribes" var="subscribes"/>
<fmt:message key="myPage" var="myPage"/>
<fmt:message key="logout" var="logout"/>
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
    <span>${subscribes}</span><br/>
    ${followersList:getSubscribesList(requestScope["subscribes"])}
</div>
</body>
</html>

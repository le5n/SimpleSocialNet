<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/allUsers.tld" prefix="allUsers" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>all users</title>
</head>
<body>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="Bundle"/>
<fmt:message key="logout" var="logout"/>
<fmt:message key="myPage" var="mypage"/>
<div align="right">
    <form action="/logout" method="POST">
        <button type="submit">${logout}</button>
    </form>
</div>
<div align="left">
    <form action="/page/">
        <button type="submit">${mypage}</button>
    </form>
</div>
<div align="middle">
    ${allUsers:getAllUsers(requestScope["users"])}
</div>
</body>
</html>


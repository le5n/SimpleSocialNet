<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/postOut.tld" prefix="postOut" %>
<%@ taglib uri="/WEB-INF/buttonWithId.tld" prefix="userID" %>
<%@ taglib uri="/WEB-INF/subButton.tld" prefix="subButton" %>
<%@ taglib uri="/WEB-INF/subscribesAmount.tld" prefix="countSubscribes" %>
<%@ taglib uri="/WEB-INF/username.tld" prefix="getUsername" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>${getUsername:getUsername(requestScope["userID"])}</title>
    <%--<link rel="stylesheet" href="../_styles/style.css">--%>
</head>
<body>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="Bundle"/>
<fmt:message key="logout" var="logout"/>
<fmt:message key="myPage" var="myPage"/>
<fmt:message key="allUsers" var="allUsers"/>
<fmt:message key="subscribes" var="subs"/>
<fmt:message key="followers" var="followers"/>
<fmt:message key="date" var="date"/>
<fmt:message key="text" var="text"/>
<fmt:message key="posts" var="posts"/>
<div align="right">
    <form action="/logout" method="POST">
        <button type="submit">${logout}</button>
    </form>
</div>

<span>${getUsername:getUsername(requestScope["userID"])}</span>
<div align="left">
    ${followers}: <a href="/followersList/?userID=${userID:getUserId(requestScope["userID"])}">
    ${countSubscribes:countSubscribes(requestScope["otherFollowers"])}</a>
    ${subs}: <a href="/subscribeList/?userID=${userID:getUserId(requestScope["userID"])}">
    ${countSubscribes:countSubscribes(requestScope["otherSubscribes"])}</a>
</div>
<div align="left">
    <form action="/page/">
        <button type="submit">${myPage}</button>
    </form>
    <form action="/GetAllUsers">
        <button type="submit">${allUsers}</button>
    </form>
</div>
<div align="middle">
    <form action="/SubscribeServlet/">
        <input type="hidden" value="${userID:getUserId(requestScope["userID"])}" name="idButton">
        <input type="submit" value="${subButton:getSubButton(requestScope["subButton"])}" name="subButton">
    </form>
    <span> ${posts} ${getUsername:getUsername(requestScope["userID"])}:</span> <br/>
    <table border="1">
        <tr>
            <th>${date}</th>
            <th>${text}</th>
        </tr>

        ${postOut:getUserPosts(requestScope["anotherPosts"])}

    </table>
    <br/>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/postOut.tld" prefix="postOut" %>
<%@ taglib uri="/WEB-INF/buttonWithId.tld" prefix="userID" %>
<%@ taglib uri="/WEB-INF/subButton.tld" prefix="subButton" %>
<%@ taglib uri="/WEB-INF/subscribesAmount.tld" prefix="countSubscribes" %>
<%@ taglib uri="/WEB-INF/username.tld" prefix="getUsername" %>
<html>
<head>
    <title>${getUsername:getUsername(requestScope["userID"])}</title>
    <%--<link rel="stylesheet" href="../_styles/style.css">--%>
</head>
<body>
<div align="right">
    <form action="/logout" method="POST">
        <button type="submit">logout</button>
    </form>
</div>

<span>${getUsername:getUsername(requestScope["userID"])}</span>
<div align="left">
    Followers: <a href="/followersList/?userID=${userID:getUserId(requestScope["userID"])}">
    ${countSubscribes:countSubscribes(requestScope["otherFollowers"])}</a>
    Subscribes: <a href="/subscribeList/?userID=${userID:getUserId(requestScope["userID"])}">
    ${countSubscribes:countSubscribes(requestScope["otherSubscribes"])}</a>
</div>
<div align="left">
    <form action="/page/">
        <button type="submit">My page</button>
    </form>
    <form action="/GetAllUsers">
        <button type="submit">All users</button>
    </form>
</div>
<div align="middle">
    <form action="/SubscribeServlet/">
        <input type="hidden" value="${userID:getUserId(requestScope["userID"])}" name="idButton">
        <input type="submit" value="${subButton:getSubButton(requestScope["subButton"])}" name="subButton">
    </form>
    <span>${getUsername:getUsername(requestScope["userID"])} posts:</span> <br/>
    <table border="1">
        <tr>
            <th>date</th>
            <th>text</th>
        </tr>

        ${postOut:getUserPosts(requestScope["anotherPosts"])}

    </table>
    <br/>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/postOut.tld" prefix="postOut"%>
<%@ taglib uri="/WEB-INF/buttonWithId.tld" prefix="userID"%>
<%@ taglib uri="/WEB-INF/subButton.tld" prefix="subButton"%>
<%@ taglib uri="/WEB-INF/subscribesAmount.tld" prefix="countSubscribes" %>
<%@ taglib uri="/WEB-INF/username.tld" prefix="getUsername" %>
<html>
<head>
    <title>another user page</title>
    <%--<link rel="stylesheet" href="../_styles/style.css">--%>
</head>
<body>
<span>${getUsername:getUsername(requestScope["userID"])}</span>
<div align="left">
    Followers:  <a href="/followersList/?userID=${userID:getUserId(requestScope["userID"])}">
    ${countSubscribes:countSubscribes(requestScope["otherFollowers"])}</a>
    Subscribes: <a href="/subscribeList/?userID=${userID:getUserId(requestScope["userID"])}">
    ${countSubscribes:countSubscribes(requestScope["otherSubscribes"])}</a>
</div>
<div align="middle">
    <form action="/SubscribeServlet/">
        <input type="hidden" value="${userID:getUserId(requestScope["userID"])}" name="idButton">
        <input type="submit" value="${subButton:getSubButton(requestScope["subButton"])}" name="subButton">
    </form>
    <span>user subscribes:</span> <br/>
    <table border="1">
        <tr>
            <th>date</th>
            <th>text</th>
        </tr>

        ${postOut:getUserPosts(requestScope["anotherPosts"])}

    </table> <br/>
</div>

</body>
</html>
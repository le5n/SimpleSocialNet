<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/postOut.tld" prefix="postOut" %>
<%@ taglib uri="/WEB-INF/subPostOut.tld" prefix="subPostOut" %>
<%@ taglib uri="/WEB-INF/subscribesAmount.tld" prefix="countSubscribes" %>
<%@ taglib uri="/WEB-INF/username.tld" prefix="getUsername" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>my page</title>
    <link rel="stylesheet" href="../_styles/pageStyle.css">
</head>
<body>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="Bundle"/>
<fmt:message key="logout" var="logout"/>
<fmt:message key="allUsers" var="allUsers"/>
<fmt:message key="hello" var="hello"/>
<fmt:message key="subscribes" var="subs"/>
<fmt:message key="followers" var="followers"/>
<fmt:message key="enterPost" var="enterPost"/>
<fmt:message key="post" var="post"/>
<fmt:message key="myPosts" var="myPosts"/>
<fmt:message key="mySubs" var="mySubs"/>
<fmt:message key="date" var="date"/>
<fmt:message key="userr" var="user"/>
<fmt:message key="text" var="text"/>



<span>${subs}: <a href="/subscribeList/"> ${countSubscribes:countSubscribes(requestScope["subscribes"])}</a></span>
<span>${followers}: <a href="/followersList/"> ${countSubscribes:countSubscribes(requestScope["followers"])}</a></span>
<div>
${hello}, ${getUsername:getUsername(sessionScope["userId"])}
    </div>
<div align="right">
    <form action="/logout" method="POST">
        <button type="submit">${logout}</button>
    </form>
    <form action="/locale/locale.jsp">
        <button type="submit">language</button>
    </form>
</div>
    <form action="/GetAllUsers">
        <button type="submit">${allUsers}</button>
    </form>
<div align="middle">
    <form action="/AddPostServlet">
        <textarea name="newPost" rows="4" cols="55" placeholder="${enterPost}"></textarea><br/><br/>
        <button type="submit">${post}</button>
    </form>
</div>
<div align="middle" id="left">
    <span>${myPosts}</span> <br/>
    <table border="1">
        <tr>
            <th>${date}</th>
            <th>${text}</th>
        </tr>

        ${postOut:getUserPosts(requestScope["posts"])}

    </table>
    <br/>
</div>
<div align="middle" id="right">
    <span>${mySubs}</span>
    <table border="1">
    <tr>
        <th>${user}</th>
        <th>${date}</th>
        <th>${text}</th>
    </tr>
    ${subPostOut:getPostList(requestScope["subPosts"])}

    </table> <br/>

</div>
</body>
</html>


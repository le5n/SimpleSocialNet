<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/postOut.tld" prefix="postOut" %>
<%@ taglib uri="/WEB-INF/subPostOut.tld" prefix="subPostOut" %>
<%@ taglib uri="/WEB-INF/subscribesAmount.tld" prefix="countSubscribes" %>
<%@ taglib uri="/WEB-INF/username.tld" prefix="getUsername" %>

<html>
<head>
    <title>my page</title>
    <link rel="stylesheet" href="../_styles/pageStyle.css">
</head>
<body>
<span>subscribes: <a href="/subscribeList/"> ${countSubscribes:countSubscribes(requestScope["subscribes"])}</a></span>
<span>followers: <a href="/followersList/"> ${countSubscribes:countSubscribes(requestScope["followers"])}</a></span>
<div>
Hello, ${getUsername:getUsername(sessionScope["userId"])}
    </div>
<div align="right">
    <form action="/logout" method="POST">
        <button type="submit">logout</button>
    </form>
</div>
    <form action="/GetAllUsers">
        <button type="submit">all users</button>
    </form>
<div align="middle">
    <form action="/AddPostServlet">
        <textarea name="newPost" rows="4" cols="55" placeholder="enter the post"></textarea><br/><br/>
        <button type="submit">Post</button>
    </form>
</div>
<div align="middle" id="left">
    <span>My posts</span> <br/>
    <table border="1">
        <tr>
            <th>date</th>
            <th>text</th>
        </tr>

        ${postOut:getUserPosts(requestScope["posts"])}

    </table>
    <br/>
</div>
<div align="middle" id="right">
    <span>My subscribes</span>
    <table border="1">
    <tr>
        <th>user</th>
        <th>date</th>
        <th>text</th>
    </tr>
    ${subPostOut:getPostList(requestScope["subPosts"])}

    </table> <br/>

</div>
</body>
</html>


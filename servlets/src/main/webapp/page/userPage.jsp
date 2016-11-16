<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/postOut.tld" prefix="postOut" %>
<%@ taglib uri="/WEB-INF/subPostOut.tld" prefix="subPostOut" %>
<%@ taglib uri="/WEB-INF/followers.tld" prefix="countFollowers" %>
<html>
<head>
    <title>my page</title>
    <link rel="stylesheet" href="../_styles/style.css">
</head>
<body>
<span>subscibes: <a href="/followersList"> ${countFollowers:countFollowers(requestScope["followers"])}</a></span>
<div>
    place for photo
    <form action="/logout" method="POST">
        <button type="submit">logout</button>
    </form>
    <form action="/GetAllUsers">
        <button type="submit">all users</button>
    </form>
</div>
<div align="left">
    <form action="/AddPostServlet">
        <textarea name="newPost" rows="4" cols="55" placeholder="enter the post"></textarea><br/><br/>
        <button type="submit">Post</button>
    </form>
</div>
<div align="left">
    <span>My followers</span> <br/>
    <table border="1">
        <tr>
            <th>date</th>
            <th>text</th>
        </tr>

        ${postOut:getUserPosts(requestScope["posts"])}

    </table>
    <br/>
</div>
<div align="middle">
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


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/postOut.tld" prefix="postOut"%>
<html>
<head>
    <title>my page</title>
    <link rel="stylesheet" href="../_styles/style.css">
</head>
<body>
<div>
    place for photo
    <form action="/logout" method="POST">
        <button type="submit">logout</button>
    </form>
    <form action="/GetAllUsers">
        <button type="submit">all users</button>
    </form>
</div>
<div align="middle">
<span>My posts:</span> <br/>
<table border="1">
    <tr>
        <th>date</th>
        <th>text</th>
    </tr>

    ${postOut:getUserPosts(requestScope["posts"])}

</table> <br/>
    </div>
<div align="left">
    <form action="/AddPostServlet">
        <textarea name="newPost" rows="4" cols="55" placeholder="enter the post"></textarea><br/><br/>
        <button type="submit">Post</button>
        </form>
</div>
</body>
</html>

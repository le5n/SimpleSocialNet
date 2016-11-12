<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11.11.2016
  Time: 02:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/postOut.tld" prefix="postOut"%>
<html>
<head>
    <title></title>
</head>
<body>
<div>
    place for photo
    <form action="/logout" method="POST">
        <button type="submit">logout</button>
    </form>
</div>
<div class="information" align="middle">
My posts: <br/>
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/postOut.tld" prefix="postOut"%>
<%@ taglib uri="/WEB-INF/buttonWithId.tld" prefix="userID"%>
<html>
<head>
    <title>another user page</title>
    <%--<link rel="stylesheet" href="../_styles/style.css">--%>
</head>
<body>
<div align="middle">
    <form action="/SubscribeServlet">
        <button type="submit" value="${userID:getUserId(requestScope["userID"])}" name="button"> subscribe </button>
    </form>
    <span>user posts:</span> <br/>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/postOut.tld" prefix="postOut"%>
<%@ taglib uri="/WEB-INF/buttonWithId.tld" prefix="userID"%>
<%@ taglib uri="/WEB-INF/subButton.tld" prefix="subButton"%>
<html>
<head>
    <title>another user page</title>
    <%--<link rel="stylesheet" href="../_styles/style.css">--%>
</head>
<body>
<div align="middle">
    <form action="/SubscribeServlet">
        <input type="hidden" value="${userID:getUserId(requestScope["userID"])}" name="idButton">${userID:getUserId(requestScope["userID"])}
        <input type="submit" value="${subButton:getSubButton(requestScope["subButton"])}" name="subButton"> ${subButton:getSubButton(requestScope["subButton"])}
    </form>
    <span>user followers:</span> <br/>
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
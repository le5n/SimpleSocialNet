<%@ page import="sql.SqlPostDao" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="social.Post" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/gunshop.tld" prefix="gunshop"%>
<html>
<head>
    <title>Каталог</title>
</head>
<body>

<%--<jsp:useBean id="posts" class="java.util.HashSet" scope="request"/>--%>

<table>
    <tr>
        <th>Name</th>
        <th>Caliber</th>
    </tr>

    ${gunshop:getList(requestScope["posts"])}
</table>
<a href="/GetUserServlet/?userHref="></a>
</body>
</html>

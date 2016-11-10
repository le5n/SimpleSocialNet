<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11.11.2016
  Time: 02:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/gunshop.tld" prefix="gunshop"%>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <tr>
        <th>Name</th>
        <th>Caliber</th>
    </tr>

    ${gunshop:getList(requestScope["posts"])}

</table>
</body>
</html>

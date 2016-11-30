<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="utf-8">
    <title>Success</title>
    <link rel="stylesheet" href="../_styles/style.css">
</head>

<body>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="Bundle"/>
<fmt:message key="success" var="success"/>
<fmt:message key="myPage" var="mypage"/>
<div align="middle">
    <form action="/page">
        ${success}<br/>
        <button class="btn btn-large btn-primary" type="submit">${mypage}</button>
    </form>

</div>
</body>
</html>
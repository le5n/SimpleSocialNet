<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="utf-8">
    <title>Fail</title>
    <link rel="stylesheet" href="../_styles/style.css">
</head>

<body>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="Bundle"/>
<fmt:message key="goBack" var="goBack"/>
<fmt:message key="wrong" var="wrong"/>
<fmt:message key="tryAgain" var="tryAgain"/>
<div align="middle">

    <form action="/login/login.jsp">
        <div>
            <span>${wrong}</span><br>
            <span>${tryAgain}</span><br> <br>
        </div>
        <button class="btn btn-large btn-primary" type="submit">${goBack}</button>
    </form>

</div>
</body>
</html>
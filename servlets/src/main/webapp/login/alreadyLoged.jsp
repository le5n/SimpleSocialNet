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
<fmt:message key="myPage" var="myPage"/>
<fmt:message key="alreadyLog" var="alreadyLog"/>
<div class="container" align="middle">

    <form action="/login/successLogin.jsp">
        <span>${alreadyLog}</span> <br/>
        <button class="btn btn-large btn-primary" type="submit">${myPage}</button>
    </form>

</div>
</body>
</html>
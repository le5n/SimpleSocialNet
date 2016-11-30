<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="utf-8">
    <title>Sk8</title>
    <link rel="stylesheet" href="../_styles/style.css">
    <fmt:requestEncoding value="UTF-8"/>
    <fmt:setBundle basename="Bundle"/>
    <fmt:message key="PleaseSignIn" var="pleaseSignIn"/>
    <fmt:message key="email" var="email"/>
    <fmt:message key="passwordd" var="password"/>
    <fmt:message key="signIn" var="signIn"/>
    <fmt:message key="register" var="register"/>
    <fmt:message key="signUp" var="signUp"/>


</head>

<body>
<div align="middle">
    <form action="/locale/locale.jsp">
        <button type="submit">Choose language</button>
    </form>
</div>
<div align="middle">
    <form method="post" action="/Login">
        <h2>${pleaseSignIn}</h2>
        <input type="text" class="input-block-level" placeholder="${email}" name="email"> <br/>
        <input type="password" class="input-block-level" placeholder="${password}" name="password"> <br/>
        <button class="btn btn-large btn-primary" type="submit">${signIn}</button>
    </form>
    <form action="/registration/register.html">
        <strong>${register}</strong> <br> <br>
        <button class="btn btn-large btn-primary" type="submit">${signUp}</button>
    </form>

</div>
</body>
</html>



 
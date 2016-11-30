<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="utf-8">
    <title>Sign up</title>
    <link rel="stylesheet" href="../_styles/style.css">
</head>
<body>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setBundle basename="Bundle"/>
<fmt:message key="alreadyReg" var="alreadyReg"/>
<fmt:message key="signIn" var="signIn"/>
<fmt:message key="username" var="username"/>
<fmt:message key="fillForm" var="fillForm"/>
<fmt:message key="passwordd" var="password"/>
<fmt:message key="repeatPas" var="repeatPas"/>
<fmt:message key="email" var="email"/>
<fmt:message key="name" var="name"/>
<fmt:message key="lastname" var="lastname"/>
<fmt:message key="signUp" var="signUp"/>

<div align="left">
    ${alreadyReg}
    <form action="/login/login.jsp">
        <button type="submit">${signIn}</button>
    </form>
</div>
<div align="middle">

    <form action="/AddUserServlet" method="POST">
        <h2>${fillForm}</h2>
        <input type="text" placeholder="${username}" name="username"> <br>
        <input type="password" placeholder="${password}" name="password"> <br>
        <input type="password" placeholder="${repeatPas}" name="repeatPassword"> <br>
        <input type="text" placeholder="${name}" name="name"> <br>
        <input type="text" placeholder="${lastname}" name="lastname"> <br>
        <input type="text" placeholder="${email}" name="email" id="email"> <br>
        <button class="btn btn-large btn-primary" type="submit" id=1>${signUp}</button>
    </form>

</div>
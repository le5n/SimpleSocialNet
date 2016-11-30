<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="utf-8">
    <title>Sk8</title>
    <link rel="stylesheet" href="../_styles/style.css">
</head>

<body>
<fmt:requestEncoding value="UTF-8" />
<fmt:setBundle basename="Bundle"/>
<fmt:message key="login" var="login"/>
<fmt:message key="thanksForReg" var="thanksForReg"/>
<div align="middle">
    <form action="/login/login.jsp">
        <span>${thanksForReg}</span><br/>
        <button class="btn btn-large btn-primary" type="submit">${login}</button>
    </form>
 </div>
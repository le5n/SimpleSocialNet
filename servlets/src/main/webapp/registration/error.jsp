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
<fmt:message key="goBack" var="goBack"/>
<fmt:message key="error" var="error"/>
<fmt:message key="reason1" var="reason1"/>
<fmt:message key="reason2" var="reason2"/>
<fmt:message key="reason3" var="reason3"/>
<fmt:message key="reason4" var="reason4"/>
<div align="middle">
    <div>
        <span>${error}</span><br>
        <ul>
            <li type="circle">${reason1}</li>
            <li type="circle">${reason2}</li>
            <li type="circle">${reason3}</li>
            <li type="circle">${reason4}</li>
        </ul>
    </div>
    <form action="/registration/register.jsp">
        <button class="btn btn-large btn-primary" type="submit">${goBack}</button>
    </form>
</div>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <meta charset="UTF-8">
    <title>Choose language</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<form action="/locale" method="post">
    <span>Выберите язык/Choose language</span>
    <select name="language" size="1">
        <option  value="ru_RU">Русский</option>
        <option selected value="en_US">English</option>
    </select>
    <br/><br/>
    <input type="submit" value="Выбрать/Choose">
</form>
<form action="/page">
    <button type="submit">Back/Назад</button>
</form>
</body>
</html>
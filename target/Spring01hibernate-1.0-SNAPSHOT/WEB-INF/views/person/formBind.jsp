<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05.07.2020
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Form Binded</title>
</head>
<body>

<form:form method="post" modelAttribute="person">

    <div>
        <label for="login">Login</label>
        <form:input id="login" path="login"/>
    </div>
    <div>
        <label for="email">email</label>
        <form:input id="email" path="email" type="email"/>
    </div>
    <div>
        <label for="password">password</label>
        <form:input id="password" path="password" type="password"/>
    </div>
    <div>
        <input type="submit">
    </div>

</form:form>

</body>
</html>

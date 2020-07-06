<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: soul
  Date: 7/5/20
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
</head>
<body>
<form:form method="post" modelAttribute="book">

    <div>
        <label for="title">Title</label>
        <form:input id="title" path="title" type="text"/>
    </div>

    <div>
        <label for="rating">Rating</label>
        <form:input id="rating" path="rating" type="text"/>
    </div>

    <div>
        <label for="description">Description</label>
        <form:input id="description" path="description" type="text"/>
    </div>

    <div>
        <label for="publisher">Publisher</label>
        <form:select id="publisher" path="publisher" items="${publishers}"/>
    </div>

    <div>
        <label for="authors">Publisher</label>
        <form:select id="authors" path="authors" items="${authors}" multiple="true"/>
    </div>
    <div>
        <input type="submit">
    </div>

</form:form>

</body>
</html>
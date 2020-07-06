<%--
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05.07.2020
  Time: 13:42
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student view</title>
</head>
<body>
&lt;%&ndash;

gender (radio button)
country (select z możliwością pojedynczego wyboru)
notes (textarea)
mailingList (checkbox)
programmingSkills (select z możliwością wyboru wielu opcji)
hobbies (grupa checkboxów)&ndash;%&gt;
<form method="post">
    <div>
        <label for="firstName">firstName</label>
        <form:input id="firstName" name="firstName" type="text"/>
    </div>
    <div>
        <label for="lastName">lastName</label>
        <form:input id="lastName" name="lastName" type="text"/>
    </div>
    <div>
        <label for="gender">gender</label>
        <form:radiobutton id="gender" name="gender" type=""/>
    </div>
    <div>
        <label for="country">country</label>
        <input id="country" name="country" type="submit">
    </div>
    <div>
        <label for="notes">notes</label>
        <input id="notes" name="notes" type="textarea">
    </div>
    <div>
        <label for="programingSkills">programingSkills</label>
        <input id="programingSkills" name="programingSkills" type="submit">
    </div>
    <div>
        <label for="hobbies">hobbies</label>
        <input id="hobbies" name="hobbies" type="checkbox">
    </div>
    <div>
        <input type="submit">
    </div>
</form>
</body>
</html>
--%>

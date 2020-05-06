<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>kkh company home page</title>
</head>
<body>
<h2>kkh company home page - Yoohoo!!!</h2>
<hr>
<p>
    Welcome to the kkh company home page!
</p>

<form:form action="${pageContext.request.contextPath}/authenticatedTheUser"
           method="POST">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>

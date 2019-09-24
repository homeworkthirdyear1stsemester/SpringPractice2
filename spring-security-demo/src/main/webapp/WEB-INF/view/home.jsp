<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>kkh Company Home Page</title>
</head>

<body>
<h2>kkh Company Home Page</h2>
<hr>

<p>
    Welcom to the kkh company home page!
</p>

<!-- Add a logout button -->
<form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>

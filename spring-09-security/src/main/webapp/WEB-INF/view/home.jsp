<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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

<hr>
<!-- display user name and role -->
<p>
    User : <security:authentication property="principal.username"/>
    <br/>
    <br/>
    Role (s) : <security:authentication property="principal.authorities"/>
</p>
<hr>
<!-- Add a link to point to /leaders ... this is for the manager -->
<p>
    <a href="${pageContext.request.contextPath}/leaders">LeaderShip Meeting</a>
    (Only for Manager peeps)
</p>

<!-- Add a link to point to /systems ... this is for the admins -->
<p>
    <a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
    (Only for Admin peeps)
</p>

<hr>

<!-- Add a logout button -->
<form:form action="${pageContext.request.contextPath}/logout"
           method="POST">
    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>

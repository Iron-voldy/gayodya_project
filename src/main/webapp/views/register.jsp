<%--webapp/views/register.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <img src="${pageContext.request.contextPath}/images/default-avatar.jpg" alt="Description">
    <title>User Registration</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Register</h2>
    <form action="register" method="post">
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password"><br>
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email"><br><br>
        <input type="submit" value="Register">
    </form>
    <p>Already have an account? <a href="login">Login</a></p>
</div>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
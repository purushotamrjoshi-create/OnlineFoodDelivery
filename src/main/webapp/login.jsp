<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

<h2>User Login</h2>

<form action="login" method="post">

    <label>Email</label><br>
    <input type="email" name="email" required><br><br>

    <label>Password</label><br>
    <input type="password" name="password" required><br><br>

    <input type="submit" value="Login">

</form>

<p>
Don't have an account?
<a href="register.jsp">Register</a>
</p>

</body>
</html>
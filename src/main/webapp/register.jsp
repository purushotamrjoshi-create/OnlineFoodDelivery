<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

<h2>User Registration</h2>

<form action="register" method="post">

    <label>Full Name</label><br>
    <input type="text" name="name" required><br><br>

    <label>Email</label><br>
    <input type="email" name="email" required><br><br>

    <label>Password</label><br>
    <input type="password" name="password" required><br><br>

    <label>Phone</label><br>
    <input type="text" name="phone" required><br><br>

    <label>Address</label><br>
    <textarea name="address" rows="4" cols="30" required></textarea><br><br>

    <input type="submit" value="Register">

</form>

<p>
Already have an account?
<a href="login.jsp">Login</a>
</p>

</body>
</html>
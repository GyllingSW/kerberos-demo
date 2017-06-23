<html>
<head>
    <title>Login</title>
</head>
<body>
<!--
Spring Security 3: - /j_spring_security_check
Spring Security 4: - /login
-->
<form method="POST" action="/login">
    <table style="vertical-align: middle;">
        <tr>
            <td>Username:</td>
            <td><input type="text" name="j_username" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="j_password" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Login" /></td>
        </tr>
    </table>
</form>
</body>
</html>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Login</title>
</head>
<body>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/scripts/jquery-3.4.1.js"></script>
<script type="text/javascript" src="/scripts/bootstrap.bundle.js"></script>
<script type="text/javascript" src="/scripts/bootstrap.js"></script>

<h1>Login</h1>

<form name='f' action="/j_spring_security_check" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' class="form-control" name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' class="form-control" name='password' /></td>
        </tr>

        <tr>
            <td><button name="submit" type="submit" class="btn btn-primary" value="submit">Submit</button></td>
        </tr>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    </table>
</form>

<br>
Username/pass:
<ul>
    <li>user 123</li>
    <li>admin 123</li>
</ul>

</body>

</html>
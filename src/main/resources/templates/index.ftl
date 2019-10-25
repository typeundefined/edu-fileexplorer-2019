<#ftl output_format="HTML">
<!doctype html>
<html>
<head>
    <title>Hello</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script type="text/javascript" src="/scripts/jquery-3.4.1.js"></script>
</head>
<body>
<div>
    <h1>This is just an example page</h1>
</div>
<div>
    Username:
    <span class="badge-success">${username}</span>
    привет
    <br>
    <br>
    <a onclick="return location.href = 'http://localhost:8080/testcss/'" class="btn btn-info" role="button">Test CSS</a>
</div>
</body>
</html>

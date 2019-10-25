<#ftl output_format="HTML">
<!doctype html>
<html>
  <head>
    <title>Hello</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<#--    <script type="text/javascript" src="/scripts/jquery-3.4.1.js">-->
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

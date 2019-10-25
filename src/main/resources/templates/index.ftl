<#ftl output_format="HTML">
<#setting url_escaping_charset="UTF-8">
<!doctype html>
<html>
<head>
    <title>File Manager</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script type="text/javascript" src="/scripts/jquery-3.4.1.js"></script>
</head>
<body>
<div>
    <h1>This is just an example page</h1>
</div>
<div>
    <span class="badge-success">${directory.directoryName}</span>
    привет
    <br>
    <br>
    <a onclick="return location.href = 'http://localhost:8080/testcss/'" class="btn btn-info" role="button">Test CSS</a>
</div>
<div class="container">
<#list directory.files as f>
<div class="card" style="width: 18rem; margin: 1rem;">
  <div class="card-body">
    <h5 class="card-title">${f.name}</h5>
    <p class="card-text">Some other attributes here...</p>
    <#if f.directory>
        <a href="/?path=${f.relativePath?url}" class="btn btn-primary">Open</a>
    </#if>
  </div>
</div>
</#list>
</div>
</body>
</html>

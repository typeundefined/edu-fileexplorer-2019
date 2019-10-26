<#ftl output_format="HTML">
<#setting url_escaping_charset="UTF-8">
<#macro show_file file_elem>
    <div class="card" style="width: 18rem; margin: 1rem;">
      <div class="card-body">
        <#if !file_elem.directory>
            <h5 class="card-title"><a href="/download?file=${file_elem.relativePath?url}">${file_elem.name}</a></h5>
            <p class="card-text">Size: ${file_elem.size}</p>
        </#if>
        <#if file_elem.directory>
            <h5 class="card-title">${file_elem.name}</h5>
            <a href="/?path=${file_elem.relativePath?url}" class="btn btn-primary">Open</a>
        </#if>
      </div>
    </div>
</#macro>
<!doctype html>
<html>
<head>
    <title>File Manager</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script type="text/javascript" src="/scripts/jquery-3.4.1.js"></script>
</head>
<body>
<div class="container">
<#list directory.files as f>
    <@show_file f/>
</#list>
</div>
</body>
</html>

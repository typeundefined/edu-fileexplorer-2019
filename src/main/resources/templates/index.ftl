<#ftl output_format="HTML">
<#setting url_escaping_charset="UTF-8">
<#macro show_file file_elem>
<#if !file_elem.directory>
   <div class="row bg-white p-3 noselect view" onclick="location.href='/download?file=${file_elem.relativePath?url}'">
        <div class="col media">
            <img class="pr-2" src="/img/file.svg" style="height: 24px;">
            ${file_elem.name}
        </div>
        <div class="col-6 row">
            <div class="col-8">${file_elem.lastModifiedTime?date}</div>
            <div class="col-4">${file_elem.size}</div>
        </div>
    </div>
</#if>
<#if file_elem.directory>
    <div class="row bg-white p-3 noselect view" onclick="location.href='/?path=${file_elem.relativePath?url}'">
        <div class="col media">
            <img class="pr-2" src="/img/folder.svg" style="height: 24px;">
            ${file_elem.name}
        </div>
          <div class="col-6 row">
            <div class="col-8">${file_elem.lastModifiedTime?date}</div>
            <div class="col-4">&ndash;</div>
        </div>
    </div>
</#if>
</#macro>
<!doctype html>
<html>
<head>
    <title>File Manager</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script type="text/javascript" src="/scripts/jquery-3.4.1.js"></script>

    <style>
      .noselect {
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
            -o-user-select: none;
            user-select: none;
            cursor: default;
        }
    </style>
</head>
<body>
<form name="uploadingForm" enctype="multipart/form-data" method="POST" >
    <p>
        <input id="fileInput" type="file" name="uploadingFiles" onchange="updateSize();" multiple>
        selected files: <span id="fileNum">0</span>;
        total size: <span id="fileSize">0</span>
        <input type="submit" value="Загрузить файлы">
    </p>
</form>
<script>
    function updateSize() {
        var nBytes = 0,
            oFiles = document.getElementById("fileInput").files,
            nFiles = oFiles.length;
        for (var nFileId = 0; nFileId < nFiles; nFileId++) {
            nBytes += oFiles[nFileId].size;
        }
        var sOutput = nBytes + " bytes";
        // optional code for multiples approximation
        for (var aMultiples = ["KiB", "MiB", "GiB", "TiB", "PiB", "EiB", "ZiB", "YiB"], nMultiple = 0, nApprox = nBytes / 1024; nApprox > 1; nApprox /= 1024, nMultiple++) {
            sOutput = nApprox.toFixed(3) + " " + aMultiples[nMultiple] + " (" + nBytes + " bytes)";
        }
        // end of optional code
        document.getElementById("fileNum").innerHTML = nFiles;
        document.getElementById("fileSize").innerHTML = sOutput;
    }
</script>
<div class="container">
<div class="row bg-white p-3 border-bottom">
    <div class="col">
        <strong>Name</strong>
    </div>
    <div class="col-6 row">
        <div class="col-8"><strong>Date of change</strong></div>
        <div class="col-4"><strong>Size</strong></div>
    </div>
</div>
<#list directory.files as f>
    <@show_file f/>
</#list>
</div>
</body>
</html>

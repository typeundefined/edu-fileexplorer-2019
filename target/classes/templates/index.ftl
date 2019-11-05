<#ftl output_format="HTML">
<#setting url_escaping_charset="UTF-8">
<#macro show_file file_elem>
    <#switch file_elem.type>
<#case "DIRECTORY">
    <div class="row bg-white p-3 noselect view" onclick="location.href='/directories/?path=${file_elem.relativePath?url}&sort=DEFAULT'">
        <div class="col media">
            <img class="pr-2" src="/img/folder.svg" style="height: 24px;">
            ${file_elem.name}
        </div>
          <div class="col-6 row">
            <div class="col-8">${file_elem.lastModifiedTime?date}</div>
            <div class="col-4">&ndash;</div>
        </div>
    </div>
<#break>
        <#case "IMAGE" >
        <form method="post">
            <div class="row bg-white p-3 noselect view"  onclick="location.href='/download?file=${file_elem.relativePath?url}'">
                <div class="col media">
                    <img class="pr-2" src="/img/image.svg" style="height: 24px;">
                    ${file_elem.name}
                </div>
                <div class="col-6 row">
                    <div class="col-8">${file_elem.lastModifiedTime?date}</div>
                    <div class="col-4">${file_elem.size}</div>
                </div>
            </div>
        </form>
            <#break >
        <#case "TEXT" >
            <div class="row bg-white p-3 noselect view" onclick="location.href='/download?file=${file_elem.relativePath?url}'">
                <div class="col media">
                    <img class="pr-2" src="/img/text.svg" style="height: 24px;">
                    ${file_elem.name}
                </div>
                <div class="col-6 row">
                    <div class="col-8">${file_elem.lastModifiedTime?date}</div>
                    <div class="col-4">${file_elem.size}</div>
                </div>
            </div>
            <#break >
        <#case "MUSIC" >
            <div class="row bg-white p-3 noselect view" onclick="location.href='/download?file=${file_elem.relativePath?url}'">
                <div class="col media">
                    <img class="pr-2" src="/img/music.svg" style="height: 24px;">
                    ${file_elem.name}
                </div>
                <div class="col-6 row">
                    <div class="col-8">${file_elem.lastModifiedTime?date}</div>
                    <div class="col-4">${file_elem.size}</div>
                </div>
            </div>
            <#break >
        <#case "ARCHIVE" >
            <div class="row bg-white p-3 noselect view" onclick="location.href='/download?file=${file_elem.relativePath?url}'">
                <div class="col media">
                    <img class="pr-2" src="/img/archive.svg" style="height: 24px;">
                    ${file_elem.name}
                </div>
                <div class="col-6 row">
                    <div class="col-8">${file_elem.lastModifiedTime?date}</div>
                    <div class="col-4">${file_elem.size}</div>
                </div>
            </div>
            <#break >
        <#default >
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
        <#break >
    </#switch>
</#macro>

<!doctype html>
<html>
<head>
    <title>File Manager</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
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

<div class="container">
    <div class="box">
        <div class="container-1">
            <form method="POST" action="/search?path=${RequestParameters.path?url}"  >
            <span class="icon"><i class="fa fa-search"></i></span>
            <input type="search" name="search" placeholder="Search..." />
            </form>
        </div>
    </div>
<div class="row bg-white p-3 border-bottom">
    <#if RequestParameters.path==RequestParameters.path?keep_before_last('\\')>
        <#assign backURL="">
    <#else>
         <#assign backURL=RequestParameters.path?keep_before_last('\\')>
    </#if>

        <img src="/img/back.png"  height="30px" width="30px" onclick="location.href='/directories/?path=${backURL?url}&sort=DEFAULT'"/>
    <div class="col">
        <strong>Name</strong>
        <img src="/img/sort-down.png" width="20px" height="20px" onclick="location.href='/directories/?path=${RequestParameters.path?url}&sort=NAME'"/>
    </div>
    <div class="col-6 row">
        <div class="col-8"><strong  >Date of change</strong> <img src="/img/sort-down.png" width="20px" height="20px" onclick="location.href='/directories/?path=${RequestParameters.path?url}&sort=DATE'"/></div>
        <div class="col-4"><strong>Size</strong><img src="/img/sort-down.png" width="20px" height="20px"
                                                     onclick="location.href='/directories/?path=${RequestParameters.path?url}&sort=SIZE'"/></div>
    </div>

</div>
<#list directory.files as f>
    <@show_file f/>
</#list>
    <form method="POST" action="/upload?path=${RequestParameters.path?url}" enctype="multipart/form-data" >
    <p style="font-style:bold">Upload new files in this directory:</p>
    <input id="fileUpload" type="file" name="file"  />
    <input type="image" src="/img/upload.png" width="25px" height="25px" onclick="parentNode.submit()"
    </form>
</div>

</body>
</html>

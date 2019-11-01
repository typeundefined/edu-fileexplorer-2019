<#ftl output_format="HTML">
<#setting url_escaping_charset="UTF-8">
<#macro show_file file_elem>
    <#if !file_elem.directory>
        <div class="row bg-white p-3 noselect view"
             onclick="location.href='/download?file=${file_elem.relativePath?url}'">
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
<#macro show_all dir>
    <#if dir.parentDirectoryName !="">
        <#if dir.parentDirectoryName =="%root%">
            <#assign path = '/'>
            <#assign dirName = "\\">
        <#else >
            <#assign path = '/?path='+dir.parentDirectoryName?url>
            <#assign dirName = "\\"+dir.parentDirectoryName>
        </#if>
        <div class="row bg-white p-3 noselect view" onclick="location.href='${path}'">
            <div class="col media">
                <img class="pr-2" src="/img/folder.svg" style="height: 24px;">
                ${dirName}
            </div>
        </div>
    </#if>
    <#list directory.files as f>
        <@show_file f/>
    </#list>
</#macro>

<#list directory.files as f>
    <@show_file f/>
</#list>

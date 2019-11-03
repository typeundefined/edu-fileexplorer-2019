<#ftl output_format="HTML">
<#setting url_escaping_charset="UTF-8">
<#macro show_file file_elem>
    <#if !file_elem.directory>
        <#assign  click_action =  '/download?file=${file_elem.relativePath?url}' />
        <#assign  picture_source =  'file.svg' />
        <#assign  size =  file_elem.size/>

    </#if>
    <#if file_elem.directory>
        <#assign  click_action =  '/?path=${file_elem.relativePath?url}' />
        <#assign  picture_source =  'folder.svg' />
        <#assign  size = "—"/>
    </#if>
    <div class="row bg-white p-3 noselect view" >
        <div class="col-0 "  onclick="location.href='/download?file=${file_elem.relativePath?url}'">
            <img class="pr-2 " src="/img/download.svg" style="height: 14px;">
        </div>
        <div class="col  mx-auto " onclick="location.href='${click_action}'">
            <img class="pr-2" src="/img/${picture_source}" style="height: 24px;">
               ${file_elem.name}
        </div>
        <div class="col-6 row">
            <div class="col-8">${file_elem.lastModifiedTime?date}</div>
            <div class="col-4">${size}</div>
        </div>
    </div>
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

<#--<#list directory.files as f>-->
<#--    <@show_file f/>-->
<#--</#list>-->
<@show_all directory/>

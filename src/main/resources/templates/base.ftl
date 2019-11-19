<#ftl output_format="HTML">
<#setting url_escaping_charset="UTF-8">
<#import "/spring.ftl" as spring />

<#macro common_page_head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">
    <script type="text/javascript" src="/scripts/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="/scripts/bootstrap.bundle.js"></script>
    <script type="text/javascript" src="/scripts/bootstrap.js"></script>
</#macro>
<#macro page_head>
    <@common_page_head />
</#macro>

<#macro render_whole_page>
    <!doctype html>
    <html>
    <head>
        <@page_head />
    </head>
    <body>
    <@page_body />

    </body>
    </html>
</#macro>

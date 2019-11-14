<#include "base.ftl">
<#macro page_head>
    <@common_page_head/>
    <title>File Manager - Error</title>
</#macro>

<#macro page_body>
<div class="container">
  <div class="alert">${errorMessage}</div>
</div>
</#macro>
<@render_whole_page />

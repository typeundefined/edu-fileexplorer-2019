<#include "base.ftl">
<#macro show_error text>
  <div class="error-message" role="alert">
    ${text}
  </div>
</#macro>
<#macro page_head>
    <@common_page_head/>
    <title>File Manager - Error</title>
</#macro>

<#macro page_body>
<div class="container">
    <form action="login" method="post">
        <div class="form-group">
            <div>
                <label for="exampleInputEmail1"> Username <input input type="text" name="username" class="form-control"
                placeholder="Enter login"/> </label>
            </div>
            <div>
                <label for="exampleInputPassword1"> Password <input type="password" name="password" class="form-control"
                 placeholder="Password"/> </label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div>
                 <button type="submit" class="btn btn-primary"/>Sign in</button>
                 <a href="/register">Registration</a>
            </div>
       </div>
    </form>
</div>

</#macro>
<@render_whole_page />
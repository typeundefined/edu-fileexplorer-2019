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
    <form name="login-form" action="register" method="POST">
      <div class="row">
        <div class="login-panel">
          <div class="form-group">
            <label for="username">Username</label>
            <@spring.bind "account.username"/>
            <input class="input-main" id="username" type="text" name="${spring.status.expression}" value="${spring.status.value!?html}">

            <#list spring.status.errorMessages as error>
              <@show_error error />
            </#list>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="login-panel">
          <div class="form-group">
            <label for="full-name">Username</label>
            <@spring.bind "account.fullName"/>
            <input class="input-main" type="text" id="full-name" name="${spring.status.expression}" value="${spring.status.value!?html}">

            <#list spring.status.errorMessages as error>
              <@show_error error />
            </#list>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="login-panel">
          <div class="form-group">
            <label for="password">Username</label>
            <@spring.bind "account.password"/>
            <input class="input-main" type="text" id="password" name="${spring.status.expression}" value="${spring.status.value!?html}">

            <#list spring.status.errorMessages as error>
              <@show_error error />
            </#list>
          </div>
        </div>
      </div>

      <div class="row">
        <div class="login-panel">
          <div class="form-group">
            <label for="password-confirm">Username</label>
            <@spring.bind "account.confirmPassword"/>
            <input class="input-main" type="text" id="password-confirm" name="${spring.status.expression}" value="${spring.status.value!?html}">

            <#list spring.status.errorMessages as error>
              <@show_error error />
            </#list>
          </div>
        </div>
      </div>
      <div class="row">
        <a class="btn btn-cancel" href="/login">Cancel</a>
        <input class="btn btn-main" name="sign-up" type="submit" value="Register" />
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </div>

    </form>
  </div>
</#macro>
<@render_whole_page />

<#macro permit code roleId content id url>
      <#list code as c>
      	<#if c==roleId>
      		<li><a id="${id}" href="javascript:void(0);" onclick="showContent(this,'${url}')">${content}</a></li>
	   	<#else>
	   	</#if>
      </#list>
</#macro> 
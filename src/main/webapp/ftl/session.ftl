<#macro ass a>
	<#if a??>
		<#compress>
			${a}
		</#compress>
	<#else>
		<#compress>
			123
		</#compress>
	</#if>
</#macro>
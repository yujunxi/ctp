<#macro greet person>  
     Hello ${person}!
</#macro>  

<#macro admin username>
	<#compress>
		${username}
	</#compress>
</#macro>
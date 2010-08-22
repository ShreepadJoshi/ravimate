<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>


<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="10" bgcolor="#F0F0FF">
	<tr>
		<td width="120" align="left" valign="top" colspan="2"><span style="color: red"><html:errors/></span></td>
	</tr>
	<tr>
		<td width="108" align="left" valign="top">UserName:<span style="color: red">&#42;</td>
		<td width="120" align="left" valign="top">
			<html:form action="/sessionFaliure" method="post" onsubmit="return validateSessionFalirePage()">
			<html:text styleId="username" property="username" styleClass="input_field" maxlength="20" size="20"/>
			<div id="username_ErrMsg" style="display: none;color: red">Username is Required </div>
		</td>
	</tr>
	<tr>
		<td align="left" valign="top">password:<span style="color: red">&#42;</td>
		<td align="left" valign="top">			
			<html:password styleId="password" property="password" styleClass="input_field" maxlength="20" size="20"/>
			<div id="password_ErrMsg" style="display: none;color: red"> Password is Required </div>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="left" valign="top">		
		<html:submit value="Login"/></html:form>	
		</td>
	</tr>
</table>
<%@ taglib uri="http://struts.apache.org/tags-html"  prefix="html" %>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="9" bgcolor="#F0F0FF">
		
	<tr>
		<td colspan="2">Admin Runtime Exception Page</td>
	</tr>
	<tr>
		<td width="29%" class="input_label">
		<html:form action="/exceptionHandler" method="post" styleId="registrationpage_formID">
		ErrorID:</td>
		<td width="71%"><html:text property="errorId" styleClass="input_field" 
			size="5" readonly="true" value="<%= (String)request.getAttribute(\"errorId\") %>"/></td>
	</tr>
	<tr>
		<td width="29%" class="input_label">User Notes:</td>
		<td> <html:textarea property="userNotes" styleClass="input_field" 
			rows="5" cols="50"/> </td>
	</tr>
	<tr>
		<td width="29%" class="input_label">Exception Details:</td>
		<td> <html:textarea property="stackTrace" styleClass="input_field" 
			rows="5" cols="50" readonly="true" value="<%= (String)request.getAttribute(\"stackTrace\") %>" /> </td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>
			<html:submit property="action" value="MailAdmin"/>
			</html:form>
		</td>
	</tr>
</table>
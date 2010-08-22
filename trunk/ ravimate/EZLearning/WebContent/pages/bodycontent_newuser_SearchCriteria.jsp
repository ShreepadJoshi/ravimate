<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">
	<tr>
		<td width="20%" class="input_label">First Name:</td>
		<td colspan="2">
			<label>
			<html:form styleId="userListFormID" action="<%= request.getParameter("action").toString() %>" method="POST">
			<html:text property="firstName" styleClass="input_field" maxlength="20" size="20" />				
			</label></td>
		
		<td colspan="1" height="30" align="right" valign="middle">
			<a onclick="javascript:alert('do submit');document.getElementById('userListFormID').submit();">
				<img border="0" src="<html:rewrite page="/images/search.jpg"/>"
					valign="middle" alt="Search" width="20" height="20" />
			</a>
		</td>

	</tr>

	<tr>
		<td class="input_label">Last Name:</td>
		<td colspan="1">
			<label><html:text property="lastName" styleClass="input_field" maxlength="20" size="20" /></label>			
		</td>


		<td class="input_label">RegisTration date:</td>
		<td colspan="0">
			<label>
				<html:text property="registrationDate" styleClass="input_field" maxlength="20" size="20" />
			 </label></html:form>
		</td>
	</tr>
</table>



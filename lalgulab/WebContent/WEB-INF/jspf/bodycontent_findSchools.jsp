<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="com.education.util.EducationConstant"%>

<%
	pageContext.setAttribute("roleType",EducationConstant.AGENT_USER_ROLE);
%>

<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">
<html:form styleId="userListFormID" action="/findSchool" method="POST">
	<tr>
		<td width="22%" class="input_label">School Name:</td>
		<td colspan="2" align="left">
			<label><html:text property="schoolName" styleClass="input_field" maxlength="20" size="20" /></label>
		</td>
		<td colspan="1" height="30" align="right" valign="middle">
			<a onclick="javascript:document.getElementById('userListFormID').submit();">
			<img border="0" src="<html:rewrite page="/images/search.jpg"/>"	valign="middle" alt="Search" width="20" height="20" />
			</a>
		</td>
	</tr>

	<tr>
		<td class="input_label">School Board:</td>
		<td colspan="2" align="left">
			<label><html:text property="schoolBoard" styleClass="input_field" maxlength="20" size="20" /></label>			
		</td>
		<td colspan="1">&nbsp;</td>
	</tr>
	
	<tr>
		<td width="22%" class="input_label">School Postal Address:</td>
		<td colspan="2" align="left">
			<label><html:text property="schoolPostalAddr" styleClass="input_field" maxlength="20" size="20" /></label>
		</td>
		<td colspan="1" height="30" align="right" valign="middle"></td>
	

	</tr>
	<tr>
		<td class="input_label">Pin Code:</td>
		<td colspan="2" align="left">
			<label><html:text property="schoolPinCode" styleClass="input_field" maxlength="20" size="20" /></label>			
		</td>
		<td colspan="1">&nbsp;</td>
	</tr>	
	<tr>
		<td class="input_label">District:</td>
		<td colspan="2" align="left">
		<html:text property="schoolDistrict" styleClass="input_field" maxlength="50" size="30" />
		</td>
		<td colspan="1">&nbsp;</td>
	</tr>
	<tr>
		<td class="input_label">State:</td>
		<td colspan="2" align="left">
		<html:text property="schoolState" styleClass="input_field" maxlength="50" size="30" />
		</td>
		<td colspan="1">&nbsp;</td>
	</tr>
	
	</html:form>
	</table>
	
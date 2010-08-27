<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">
<tr>
	<td height="2" align="left" valign="middle"></td>
</tr>
<tr>
	<td width="192" height="22" align="left" valign="middle"
		class="">		
		<html:link forward="manageQuestionLink" styleClass="url">add Question</html:link>		
 	</td>
</tr>
<tr>
	<td width="192" height="22" align="left" valign="middle"
		class="">		
		<html:link forward="adminQuestionList" styleClass="url">Manage Question</html:link>		
 	</td>
</tr>

</table>



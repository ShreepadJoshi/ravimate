<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<table width="192" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="30" align="left" valign="middle" class="heading"><img
			src="<html:rewrite page='/images/arrow.jpg'/>" width="11" height="11" hspace="15" border="0" />Services</td>
	</tr>
	<tr>
		<td height="2" align="left" valign="middle"
			background="<html:rewrite page='/images/dotted_line.jpg'/>"></td>
	</tr>
	<tr><td>&nbsp;</td></tr>	
	<tr>
		<td width="192" height="22" align="left" valign="middle">
			<html:link action="/showStudyMaterialStaticContent" styleClass="url" >Study Material</html:link>
		</td>
	</tr>
	<tr>
		<td height="2" align="left" valign="middle"></td>
	</tr>
	<tr>
		<td width="192" height="22" align="left" valign="middle">			
			 <html:link action="/showMindMappingStaticContent"  styleClass="url" >Mind Mapping</html:link>
		</td>
	</tr>
	<tr>
		<td height="2" align="left" valign="middle"></td>
	</tr>
	<tr>
		<td width="192" height="22" align="left" valign="middle">
			<html:link forward="find_schools" styleClass="url">Find Schools</html:link>
		</td>
	</tr>
	<tr>
		<td height="2" align="left" valign="middle"></td>
	</tr>
	<tr>
		<td width="192" height="22" align="left" valign="middle" >
			 <html:link action="/showAssessmentStaticContent" styleClass="url" >Assessment</html:link>
		</td>
	</tr>
	<tr>
		<td height="2" align="left" valign="middle"></td>
	</tr>
	<tr>
		<td width="192" height="22" align="left" valign="middle" >
			<html:link action="/showParentsSectionStaticContent" styleClass="url" >Parent Section</html:link>
		</td>
	</tr>
	<tr>
		<td height="2" align="left" valign="middle"></td>
	</tr>
	<tr>
		<td width="192" height="22" align="left" valign="middle" >			
			<html:link action="/showVirtualClassroomStaticContent" styleClass="url" >Virtual Classrooms</html:link>
		</td>
	</tr>
	<tr>
		<td height="2" align="left" valign="middle"></td>
	</tr>
</table>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<br>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="33%" height="100" align="center" valign="middle" class="servbg">
			<html:link action="/showStudyMaterialStaticContent" >
				<div style="padding: 12px;">
					<span class="serv_heading">Study Material</span><br /><br />
					<img src="<html:rewrite page='/images/study_material.jpg'/>"
						 width="60" height="50" hspace="15" border="0" />
				</div>
			</html:link>
		<td align="left" valign="top"></td>
		
		<td width="33%" align="center" valign="middle" class="servbg">
			<html:link action="/showMindMappingStaticContent" >
				<div style="padding: 12px;"><span class="serv_heading">	Mind Mapping </span><br /><br />
					<img src="<html:rewrite page='/images/mind_map.jpg'/>" width="100"
							 height="50" hspace="15" border="0" />
				</div>
			</html:link>
		<td align="left" valign="top"></td>
		
		<td width="33%" align="center" valign="middle" class="servbg">
		<html:link forward="find_schools" >
			<div style="padding: 12px;"><span class="serv_heading">Finding Schools</span><br /><br />
				<img src="<html:rewrite page='/images/find_school.jpg'/>" width="100"
						height="50" hspace="15" border="0" />
			</div>
		</html:link>
	</tr>

	<tr>
		<td width="31%" height="100" align="center" valign="middle" class="servbg">
			<html:link action="/showAssessmentStaticContent" >
				<div style="padding: 12px;"><span class="serv_heading">Assessment</span><br /><br />
					<img src="<html:rewrite page='/images/Self_assessment.jpg'/>"
							width="100" height="50" hspace="15" border="0" />
				</div>
			</html:link>
		<td align="left" valign="top"></td>
		
		<td width="31%" align="center" valign="middle" class="servbg">
			<html:link action="/showParentsSectionStaticContent" >
				<div style="padding: 12px;"><span class="serv_heading">Parent Section</span><br /><br />
					<img src="<html:rewrite page='/images/parent_section.jpg'/>"
					     width="100" height="50" hspace="15" border="0" />
				</div>
			</html:link>
		<td align="left" valign="top"></td>
		
		<td width="31%" align="center" valign="middle" class="servbg">
			<html:link action="/showVirtualClassroomStaticContent" >
				<div style="padding: 12px;"><span class="serv_heading">Virtual Classroom </span><br />
					<img src="<html:rewrite page='/images/virtual_classroom.jpg'/>"
							width="100" height="50" hspace="15" border="0" />
				</div>
			</html:link>
	</tr>
</table>
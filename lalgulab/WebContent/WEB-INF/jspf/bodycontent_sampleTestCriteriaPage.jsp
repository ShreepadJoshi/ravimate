<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>

<html:form action="/sampleTest.do" method="post">

	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="10" bgcolor="#CAE2FE">
		<tr>
			<td align="right">Please Select Class :</td>
			<td align="left"><html:select styleClass="input_field"
				styleId="classID" property="sch_classID">
				<html:optionsCollection property="classTypeOptions" />
			</html:select></td>
		</tr>
		<tr>
			<td align="right">Please Select Subject :</td>
			<td align="left"><html:select styleClass="input_field"
				styleId="classID" property="subjectID">
				<html:optionsCollection property="subjectTypeOptions" />
			</html:select></td>
		</tr>		
		<tr>
			<td colspan="2" align="center"><html:submit property="action" value="TakeTest" /></td>
		</tr>
	</table>
</html:form>
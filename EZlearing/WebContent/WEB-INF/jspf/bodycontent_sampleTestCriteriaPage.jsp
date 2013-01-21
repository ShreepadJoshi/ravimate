<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html:form action="/sampleTest.do" method="post">

	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="10" bgcolor="#CAE2FE">
		<tr>
			<td align="right">Please Select Class :</td>
			<td align="left"><html:select styleClass="input_field"
				styleId="sch_classID" property="sch_classID">
				<html:optionsCollection property="classTypeOptions" />
			</html:select>
			<div id="sch_classType_ErrMsg" style="display: none;color: red"> Class is Required </div>	
			</td>
		</tr>
		<tr>
			<td align="right">Please Select Subject :</td>
			<td align="left"><html:select styleClass="input_field"
				styleId="subjectID" property="subjectID">
				<html:optionsCollection property="subjectTypeOptions" />
			</html:select>
			<div id="sch_subject_ErrMsg" style="display: none;color: red"> Subject is Required </div>
			</td>
		</tr>		
		<tr>
			<td colspan="2" align="center"><html:submit property="action" value="TakeTest" onclick="return validateInputFields()"/></td>
		</tr>
	</table>
</html:form>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@page import="com.education.transferobj.LinkQTOClassTO"%>

<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="10" bgcolor="#F0F0FF">
	
	<tr>
		<td width="35%" class="input_label">Question Id:</td>
		<td colspan="1" align="left">
			<html:form action="/linkQtoClass" method="post">
			 <html:text property="link_questionID" name="LinkQtoClassBean" styleClass = "input_field" maxlength="5" size="5" readonly="true"/>
		</td>
	</tr>
	<tr>
		<td class="input_label" >Subject:</td><td colspan="3" align="left">
			<!-- <html:text property="link_subject" name="LinkQtoClassBean" styleClass = "input_field" maxlength="50" size="35" readonly="true"/> -->
			<html:select property="link_subject" name="LinkQtoClassBean" styleClass="input_field" disabled="disabled">
				<html:optionsCollection property="subjectOptions" label="label" value="value"/>
			</html:select>	
		</td>
	</tr>
	<tr>
		<td class="input_label">Sub Topic:</td>
		<td colspan="3" align="left">		
			<html:text property="link_subtopic"  styleClass = "input_field"  maxlength="50" size="35" name="LinkQtoClassBean" readonly="true" />
		</td>
	</tr>
	<tr>
		<td class="input_label">Question:</td>
		<td colspan="3" align="left" >
			<html:textarea property="link_question" rows="4" cols="50" styleClass = "input_field"  readonly="true" name="LinkQtoClassBean"/>			
		</td>
	</tr>
	<tr>
		<td colspan="3" align="right">		 
			<html:submit property="action" value="QuestionDetails" styleClass="url"/>
		</td>
	</tr>
	<tr>
		<td colspan="4" class="blue_bold">Assign Question Complexity</td>
		<td height="30" align="center" valign="middle">
			<html:submit property="action" value="Add" styleClass="url" ></html:submit>
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center" >		
			<display:table id="row" name="LinkQtoClassBean.link_rowData" requestURI="/linkQtoClass.do">
				<display:column style="width:50" title="Class">
					<html:select property="classList" name="LinkQtoClassBean" styleClass="input_field"
							value="<%=((LinkQTOClassTO)row).getClassId()%>">
						<html:optionsCollection property="classTypeOptions" label="label" value="value"/>
					</html:select>					
				</display:column>
				<display:column title="Complexity">
					<html:select property="complexityList" name="LinkQtoClassBean" styleClass="input_field"
								value="<%=((LinkQTOClassTO)row).getComplexityId()%>">
						<html:optionsCollection property="complexityOptions" label="label" value="value"/>
					</html:select>
				</display:column>
			</display:table>
		</td>
	<tr>
	<tr>
		<td colspan="4" align="center">
			<html:submit property="action" value="Save" styleClass="url"/>
			<html:submit property="action" value="Close" styleClass="url"/>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<html:hidden property="questionId"/>
			<html:hidden property="question"/>
			<html:hidden property="subject"/>
			<html:hidden property="subTopic"/>
			
			<html:hidden property="topic"/>
			<html:hidden property="isGraphics"/>
			<html:hidden property="questionStatus"/>
			<html:hidden property="isVerified"/>
			<html:hidden property="createdBy"/>
			<html:hidden property="verifiedBy"/>
			<html:hidden property="verificationRemark"/>
			<html:hidden property="answerDiscription"/>
			<html:hidden property="option1"/>
			<html:hidden property="option2"/>
			<html:hidden property="option2"/>
			<html:hidden property="option3"/>
			<html:hidden property="option4"/>
			<html:hidden property="option5"/>
			<html:hidden property="link_rowCount"/>
			<logic:notEmpty property="linkStatus" name="LinkQtoClassBean">
				<logic:iterate id="data" property="linkStatus" name="LinkQtoClassBean">
					<input type="hidden" name="linkStatus" value="<%=data%>" />
				</logic:iterate>
			</logic:notEmpty>
			</html:form>
		</td>
	</tr>	
</table>


	

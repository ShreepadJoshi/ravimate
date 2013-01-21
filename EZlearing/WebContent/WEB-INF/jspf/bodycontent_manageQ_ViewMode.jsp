<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.education.Session.SessionConstants"%>
<%@page import="com.education.formbeans.AddQuestionForm"%>
<%@page import="com.education.util.EducationConstant"%>
<html:form action="/manageQuestion">
<table width="100%" border="0" align="left" cellpadding="0"
	cellspacing="0" bgcolor="#F0F0FF">
	<tr>
		<td align="left" valign="top">
		<table width="100%" border="0" align="left" cellpadding="5" cellspacing="1">			
			<tr><td colspan="4">
				<html:errors/>
				<html:hidden property="navigationFromGrid"/></td>
				<html:hidden property="mode"/>
				<html:hidden property="questionId"/>				
				</td>
			</tr>
			<tr>
				<td width="22%" class="input_label">Question Id:</td>
				<td colspan="3"><label>
				 <html:text property="questionId" styleClass="input_field" readonly="true" />
				 </label></td>
			</tr>
			<tr>
				<td class="input_label">Subject:<span style="color: red">&#42;</span></td>
				<td colspan="3">				
				<html:select styleClass="input_field" styleId="subject" property="subject" disabled="disabled">		 
					<html:optionsCollection property="subjectOptions"/>
				</html:select>
				<div id="subject_ErrMsg" style="display:none;color: red" >Subject is Required</div>
				</td>
			</tr>
			<tr>
				<td class="input_label">Topic:<span style="color: red">&#42;</span></td>
				<td colspan="3">
					<html:text property="topic" styleClass="input_field" styleId="topic" readonly="true"/>
					<div id="topic_ErrMsg" style="display:none;color: red" >Topic is Required</div>
				</td>
			</tr>
			<tr>
				<td class="input_label">Sub Topic:<span style="color: red">&#42;</span></td>
				<td colspan="3">
				<html:text property="subTopic" styleClass="input_field" styleId="subtopic" readonly="true"/>	
				<div id="subtopic_ErrMsg" style="display:none;color: red" >Sub Topic is Required</div>	
				</td>
			</tr>
			<tr>
				<td class="input_label">Is Graphics:</td>
				<td width="20%" colspan="3">
					<logic:notEmpty property="isGraphics" name="ManageQuestionBean">
					 	<logic:equal value="1" property="isGraphics" name="ManageQuestionBean" >
					 		Yes
					 	</logic:equal>
					 	<logic:equal value="0" property="isGraphics" name="ManageQuestionBean" >
					 		No
					 	</logic:equal>
				 </logic:notEmpty>
				</td>				
			</tr>			
			<tr>
				<td class="input_label">Question:<span style="color: red">&#42;</span></td>
				<td colspan="3" >&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">					
					<html:textarea property="question" cols="50" rows="3" styleClass="input_field" styleId="question" readonly="true"/>
					 <br/>					 
					 <img src="<html:rewrite page='/imageRender.do'/>?questionId=21&imagefor=<%=EducationConstant.IMAGE_FOR_QUESTION%>"
					  		   alt="NoImage" height="100" width="100"/>					 
					<div id="question_ErrMsg" style="display:none;color: red" >Question is Required</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">Option 1:<span style="color: red">&#42;</span></td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option1" cols="50" rows="3" styleClass="input_field" styleId="option1" readonly="true"/>
					<br/><img src="<html:rewrite page='/imageRender.do'/>?questionId=21&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION1%>"
					  		   alt="NoImage" height="100" width="100"/>
					<div id="option1_ErrMsg" style="display:none;color: red" >Option1 is Required</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">Option 2:<span style="color: red">&#42;</span></td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option2" cols="50" rows="3" styleClass="input_field" styleId="option2" readonly="true"/>
					<br/><img src="<html:rewrite page='/imageRender.do'/>?questionId=21&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION2%>"
					  		   alt="NoImage" height="100" width="100"/>
					<div id="option2_ErrMsg" style="display:none;color: red" >Option2 is Required</div>
				</td>
			</tr>

			<tr>
				<td colspan="4">Option 3:<span style="color: red">&#42;</span></td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option3" cols="50" rows="3" styleClass="input_field" styleId="option3" readonly="true"/>
					<br/><img src="<html:rewrite page='/imageRender.do'/>?questionId=21&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION3%>"
					  		   alt="NoImage" height="100" width="100"/>
					<div id="option3_ErrMsg" style="display:none;color: red" >Option3 is Required</div>
				</td>
			</tr>

			<tr>
				<td colspan="4"> Option 4:<span style="color: red">&#42;</span></td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option4" cols="50" rows="3" styleClass="input_field" styleId="option4" readonly="true"/>
					<br/><img src="<html:rewrite page='/imageRender.do'/>?questionId=21&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION4%>"
					  		   alt="NoImage" height="100" width="100"/>
					<div id="option4_ErrMsg" style="display:none;color: red" >Option4 is Required</div>
				</td>
			</tr>

			<tr>
				<td colspan="4">Option 5:<span style="color: red">&#42;</span></td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option5" cols="50" rows="3" styleClass="input_field" styleId="option5" readonly="true"/>
					<br/><img src="<html:rewrite page='/imageRender.do'/>?questionId=21&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION5%>"
					  		   alt="NoImage" height="100" width="100"/>
					<div id="option5_ErrMsg" style="display:none;color: red" >Option5 is required</div>
				</td>
			</tr>

			<tr>
				<td class="input_label">Answer:<span style="color: red">&#42;</span></td>
				<td colspan="3">
					<html:select property="answer" styleId="answer" styleClass="input_field" disabled="disabled">
						<html:option value="">Please Specify</html:option>
						<html:option value="Option1">Option1</html:option>
						<html:option value="Option2">Option2</html:option>
						<html:option value="Option3">Option3</html:option>
						<html:option value="Option4">Option4</html:option>
						<html:option value="Option5">Option5</html:option>
					</html:select>
					<div id="answer_ErrMsg" style="display: none;color: red">Answer is Required</div>
				</td>
			</tr>
			<tr>
				<td class="input_label">Answer Explanation:<span style="color: red">&#42;</span></td>
				<td colspan="3">
					<html:textarea property="answerDiscription" cols="45" styleClass="input_field" styleId="explanation" readonly="true"/>
					<br/><img src="<html:rewrite page='/imageRender.do'/>?questionId=21&imagefor=<%=EducationConstant.IMAGE_FOR_ANSWER_EXPLANATION%>"
					  		   alt="NoImage" height="100" width="100"/>
					 <div id="explanation_ErrMsg" style="display:none;color: red" >Answer Explanation is Required</div> 
				</td>
			</tr>
			<tr>
				<td width="29%" class="input_label">Created By:</td>
				<td width="28%"><label> <bean:write property="createdBy" name="ManageQuestionBean" /></label></td>
				<td width="29%" align="left" class="input_label">Created On:</td>
				<td >
				<label> <bean:write property="createdOn_dispFormat" name="ManageQuestionBean" /></label>
				</td>
			</tr>
			
			<tr>
				<td align="center" valign="middle" colspan="4">
					<logic:notEmpty property="navigationFromGrid" name="ManageQuestionBean">
						<logic:equal value="<%= SessionConstants.SCH_RESULTS_REVIEWER_QUESTION_LIST %>" 
									 name="ManageQuestionBean" property="navigationFromGrid">
							<html:submit property="action" value="Approve"/>
							<html:submit property="action" value="Reject"/>
				 			<html:submit property="action" value="LinkToClass"/>
				 			<html:submit property="action" value="Back"/>
						</logic:equal>
						<logic:equal value="<%= SessionConstants.LINKPAGE_QUESTION_DETAIL %>" 
									 name="ManageQuestionBean" property="navigationFromGrid">							
				 			<html:submit property="action" value="Back"/>
						</logic:equal>						
					</logic:notEmpty>
				</td>
			</tr>
		</table>
</html:form>
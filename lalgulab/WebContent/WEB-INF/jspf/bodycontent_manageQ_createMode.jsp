<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.education.util.EducationConstant"%>

<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>
<table width="100%" border="0" align="left" cellpadding="0"	cellspacing="0" bgcolor="#F0F0FF">
	<tr>
		<td align="left" valign="top">
		<table width="100%" border="0" align="left" cellpadding="5" cellspacing="1">			
			<tr><td colspan="4">
				<html:form action="/addQuestion" enctype="multipart/form-data">				
				<html:hidden property="navigationFromGrid"/>
				<html:hidden property="questionId"/>
				<html:hidden property="mode"/></td>
			</tr>
			<tr>
				<td width="22%" class="input_label">Question Id:</td>
				<td colspan="3"><label>				 
				 	<bean:write property="questionId" name="ManageQuestionBean"/>
				 </label></td>
			</tr>
			<tr>
				<td class="input_label">class/Cert:<span style="color: red">&#42;</span></td>
				<td colspan="3">
				<html:select styleClass="input_field" styleId="cert" property="cert">
					<html:optionsCollection property="classOptions"/>
				</html:select>
				<div id="cert_ErrMsg" style="display:none;color: red" >class/cert is Required</div>	
				</td>
			</tr>
			<tr>
				<td class="input_label">Subject:<span style="color: red">&#42;</span></td>
				<td colspan="3">
				
				<html:select styleClass="input_field" styleId="subject" property="subject">
					<html:optionsCollection property="subjectOptions"/>
				</html:select>
				<div id="subject_ErrMsg" style="display:none;color: red" >Subject is Required</div>
				</td>
			</tr>
			<tr>
				<td class="input_label">Topic:<span style="color: red">&#42;</span></td>
				<td colspan="3">
					<div id="ManageQtopic_divID">
						<html:select styleClass="input_field" styleId="topic" property="topic">
							<html:optionsCollection property="topicOptions"/>
						</html:select>							
					</div>
					<div id="topic_ErrMsg" style="display:none;color: red" >Topic is Required</div>
				</td>
			</tr>
			<tr>
				<td class="input_label">Sub Topic:<span style="color: red">&#42;</span></td>
				<td colspan="3">
				<div id="ManageQsubTopic_divID">
				<html:select styleClass="input_field" styleId="subTopic" property="subTopic">
					<html:optionsCollection property="subTopicOptions"/>
				</html:select>			
				</div>	
				<div id="subtopic_ErrMsg" style="display:none;color: red" >Sub Topic is Required</div>	
				</td>
			</tr>			
			<tr>
				<td class="input_label">Complexity:<span style="color: red">&#42;</span></td>
				<td colspan="3">
				<html:select styleClass="input_field" styleId="complexity" property="complexity" >
					<html:optionsCollection property="complexityOptions" />
				</html:select>	
				<div id="complexity_ErrMsg" style="display:none;color: red" >Question complexity is Required</div>	
				</td>
			</tr>
			<tr>
				<td class="input_label">Is Graphics:</td>
				<td width="20%" colspan="3">
					 <html:radio property="isGraphics" value="1" onclick="displayFileUpload_Cntrl('1')">Yes</html:radio>
				 	<html:radio property="isGraphics" value="0" onclick="displayFileUpload_Cntrl('0')">No</html:radio>
				</td>
				
			</tr>
			
			<tr>
				<td class="input_label">Question:<span style="color: red">&#42;</span></td>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">					
					<html:textarea property="question" cols="50" rows="3" styleClass="text" styleId="question"/>
					<html:file styleId="questionImage" style="display:none" property="questionImage"/>					
					<div id="question_ErrMsg" style="display:none;color: red" >Question is Required</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">Option 1:<span style="color: red">&#42;</span></td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option1" cols="50" rows="3" styleClass="text" styleId="option1" 
						onblur="setAnswerDD_ManageQ_CreatePg('answer')" />
					<html:file styleId="ansOneImage" style="display:none" property="ansOneImage"
						onblur="setAnswerDD_ManageQ_CreatePg('answer')"/>					
					<div id="option1_ErrMsg" style="display:none;color: red" >Option1 is Required</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">Option 2:<span style="color: red">&#42;</span></td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option2" cols="50" rows="3" styleClass="text" styleId="option2"
						onblur="setAnswerDD_ManageQ_CreatePg('answer')"/>
					<html:file styleId="ansTwoImage" style="display:none" property="ansTwoImage"
						onblur="setAnswerDD_ManageQ_CreatePg('answer')"/>					
					<div id="option2_ErrMsg" style="display:none;color: red" >Option2 is Required</div>
				</td>
			</tr>

			<tr>
				<td colspan="4">Option 3:</td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option3" cols="50" rows="3" styleClass="text" styleId="option3"
						onblur="setAnswerDD_ManageQ_CreatePg('answer')"/>
					<html:file styleId="ansThreeImage" style="display:none" property="ansThreeImage"
						onblur="setAnswerDD_ManageQ_CreatePg('answer')"/>					
					<div id="option3_ErrMsg" style="display:none;color: red" >Option3 is Required</div>
				</td>
			</tr>

			<tr>
				<td colspan="4">Option 4:</td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option4" cols="50" rows="3" styleClass="text" styleId="option4"
						onblur="setAnswerDD_ManageQ_CreatePg('answer')"/>
					 <html:file styleId="ansFourImage" style="display:none" property="ansFourImage"
					 	onblur="setAnswerDD_ManageQ_CreatePg('answer')"/>					
					<div id="option4_ErrMsg" style="display:none;color: red" >Option4 is Required</div>
				</td>
			</tr>

			<tr>
				<td colspan="4">Option 5:</td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option5" cols="50" rows="3" styleClass="text" styleId="option5"
						onblur="setAnswerDD_ManageQ_CreatePg('answer')"/>
					<html:file styleId="ansFiveImage" style="display:none" property="ansFiveImage"
						onblur="setAnswerDD_ManageQ_CreatePg('answer')"/>					
					<div id="option5_ErrMsg" style="display:none;color: red" >Option5 is required</div>
				</td>
			</tr>

			<tr>
				<td class="input_label">Answer:<span style="color: red">&#42;</span></td>
				<td colspan="3">
					<!-- <html:textarea property="answer" cols="45" styleClass="text" styleId="answer"/> -->
					<html:select property="answer" styleId="answer" styleClass="input_field">
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
				<td class="input_label">Answer Explanation:</td>
				<td colspan="3">
					<html:textarea property="answerDiscription" cols="45" styleClass="text" styleId="explanation"/>
					<html:file styleId="ansExplanation" style="display:none" property="ansExplanation"/>					
					<div id="explanation_ErrMsg" style="display:none;color: red" >Answer Explanation is Required</div> 
				</td>
			</tr>
			<tr>
				<td width="29%">Created By:</td>
				<td width="28%"><label> <bean:write property="createdBy" name="ManageQuestionBean" /></label></td>
				<td width="22%" align="left"><!-- Created On: --></td>
				<td></td>
			</tr>
			<tr>
				<td align="center" valign="middle" colspan="4">
					<html:submit property="action" styleClass="url" 
						 onclick="return validateManageQ()" >Save & Next Question</html:submit>
					<html:submit property="action" styleClass="url"  
						onclick="return validateManageQ()" >Save & Close</html:submit>
				</td>
			</tr>
			<tr>
				<td align="center" valign="middle" colspan="4">				
				<!-- <html:submit property="action" styleClass="url" value="Save" onclick="return validateManageQ()" /> -->
				<input	name="Submit2" type="reset" class="url" value="Reset" onclick="resetManageQ()"/>
				<html:submit property="action" styleClass="url" value="Close"/>
				<!-- <logic:present property="navigationFromGrid" name="ManageQuestionBean">
					<logic:notEmpty property="navigationFromGrid" name="ManageQuestionBean">
					<html:submit property="action" styleClass="url" value="Close"/>	
					</logic:notEmpty>
				</logic:present>
				<logic:empty property="navigationFromGrid" name="ManageQuestionBean">
					<input	name="Submit2" type="reset" class="url" value="Reset" onclick="resetManageQ()"/>
				</logic:empty> -->				 
				</td>
			</tr>
		</table>
</html:form>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="com.education.util.EducationConstant"%>
<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>
<table width="100%" border="0" align="left" cellpadding="0"	cellspacing="0" bgcolor="#F0F0FF">
	<tr>
		<td align="left" valign="top">
		<table width="100%" border="0" align="left" cellpadding="5" cellspacing="1">			
			<tr><td colspan="4">
				<html:form action="/manageQuestion"  enctype="multipart/form-data">
				<html:errors/>
				<html:hidden property="navigationFromGrid"/>
				<html:hidden property="questionId"/>
				<html:hidden property="mode"/>
				<html:hidden property="pageOpen_Mode"/>
				</td>
			</tr>
			<tr>
				<td width="22%" class="input_label">Question Id:</td>
				<td colspan="3"><label>				 
				 	<bean:write property="questionId" name="ManageQuestionBean"/>
				 </label></td>
			</tr>
			<tr>
				<td class="input_label">Subject:<span style="color: red">&#42;</span></td>
				<td colspan="3">
				<!-- <html:text property="subject" styleClass="text" styleId="subject" /> -->
				<!--<html:select styleClass="input_field" styleId="subject" property="subject"
					 onchange="populateDropdown('topic','subjectId='+this.value,'topiclist','ManageQtopic_divID',''+MANAGEQPG_TOPICDD)">
					<html:optionsCollection property="subjectOptions"/>
				</html:select> -->
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
						<html:select styleClass="input_field" styleId="topic" property="topicId">
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
					<html:select styleClass="input_field" styleId="topic" property="subTopicId">
						<html:optionsCollection property="subTopicOptions"/>
					</html:select>						
				</div>	
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
					 <html:hidden property="isGraphics" />
				</td>
				
			</tr>
			
			<tr>
				<td>Question:<span style="color: red">&#42;</span>
					<%
						String qId = request.getAttribute("qId")!=null ? request.getAttribute("qId").toString() : "-1";
						//out.println("QUESTIONID:"+qId);
					%>
				</td>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4">
										
					<html:textarea property="question" cols="50" rows="3" styleClass="input_field" styleId="question"/>					
					 <logic:equal value="1" property="isGraphics" name="ManageQuestionBean" >
					 	<br/><img id="question_ImageValue" src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_QUESTION%>"
					  		   alt="NoImage" height="100" width="100"/>					  	
					 	<br/><html:radio property="questionImg_editAction" value="update" 
					 			onclick="enableImgInputForID('questionImage')">Add/UpdateImage</html:radio>
					 	&nbsp;<html:radio property="questionImg_editAction" value="remove" 
					 			onclick="disableImgInputForID('questionImage')">RemoveImage</html:radio>
					 	&nbsp;<html:radio property="questionImg_editAction" value="none" 
					 			onclick="disableImgInputForID('questionImage')">Do Nothing</html:radio>			  
					 	<br/><html:file styleId="questionImage" property="questionImage" style="display:none" />
					 </logic:equal>					 					  
					<div id="question_ErrMsg" style="display:none;color: red" >Question is Required</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">Option 1:<span style="color: red">&#42;</span></td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option1" cols="50" rows="3" styleClass="input_field" styleId="option1"/>
					 <logic:equal value="1" property="isGraphics" name="ManageQuestionBean" >
						<br/><img id="option1_ImageValue" src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION1%>"
						  		   alt="NoImage" height="100" width="100"/>
						 <br/><html:radio property="option1Img_editAction" value="update" 
						 			onclick="enableImgInputForID('ansOneImage')">Add/UpdateImage</html:radio>
						 &nbsp;<html:radio property="option1Img_editAction" value="remove" 
						 			onclick="disableImgInputForID('ansOneImage')">RemoveImage</html:radio>
						 &nbsp;<html:radio property="option1Img_editAction" value="none" 
						 			onclick="disableImgInputForID('ansOneImage')">Do Nothing</html:radio>
						<html:file styleId="ansOneImage" style="display:none" property="ansOneImage"/>
					</logic:equal>					
					<div id="option1_ErrMsg" style="display:none;color: red" >Option1 is Required</div>
				</td>
			</tr>
			<tr>
				<td colspan="4">Option 2:<span style="color: red">&#42;</span></td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option2" cols="50" rows="3" styleClass="input_field" styleId="option2"/>
					 <logic:equal value="1" property="isGraphics" name="ManageQuestionBean" >
						<br/> <img id="option2_ImageValue" src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION2%>"
						  		   alt="NoImage" height="100" width="100"/>
						 <br/><html:radio property="option2Img_editAction" value="update" 
						 		onclick="enableImgInputForID('ansTwoImage')">Add/UpdateImage</html:radio>
						 &nbsp;<html:radio property="option2Img_editAction" value="remove" 
						 		onclick="disableImgInputForID('ansTwoImage')">RemoveImage</html:radio>
						 &nbsp;<html:radio property="option2Img_editAction" value="none" 
						 		onclick="disableImgInputForID('ansTwoImage')">Do Nothing</html:radio>
						<html:file styleId="ansTwoImage" style="display:none" property="ansTwoImage"/>
					</logic:equal>					
					<div id="option2_ErrMsg" style="display:none;color: red" >Option2 is Required</div>
				</td>
			</tr>

			<tr>
				<td colspan="4">Option 3:</td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option3" cols="50" rows="3" styleClass="input_field" styleId="option3"/>
					<logic:equal value="1" property="isGraphics" name="ManageQuestionBean" >
						<br/><img id="option3_ImageValue" src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION3%>"
						  		   alt="NoImage" height="100" width="100"/>
						<br/><html:radio property="option3Img_editAction" value="update" 
								onclick="enableImgInputForID('ansThreeImage')">Add/UpdateImage</html:radio>
						 &nbsp;<html:radio property="option3Img_editAction" value="remove" 
						 		onclick="disableImgInputForID('ansThreeImage')">RemoveImage</html:radio>
						 &nbsp;<html:radio property="option3Img_editAction" value="none" 
						 		onclick="disableImgInputForID('ansThreeImage')">Do Nothing</html:radio>
						<html:file styleId="ansThreeImage" style="display:none" property="ansThreeImage"/>
					</logic:equal>					
					<div id="option3_ErrMsg" style="display:none;color: red" >Option3 is Required</div>
				</td>
			</tr>

			<tr>
				<td colspan="4">Option 4:</td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option4" cols="50" rows="3" styleClass="input_field" styleId="option4"/>
					<logic:equal value="1" property="isGraphics" name="ManageQuestionBean" >
						<br/><img id="option4_ImageValue" src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION4%>"
						  		   alt="NoImage" height="100" width="100"/>
						<br/><html:radio property="option4Img_editAction" value="update" 
								onclick="enableImgInputForID('ansFourImage')">Add/UpdateImage</html:radio>
						 &nbsp;<html:radio property="option4Img_editAction" value="remove" 
						 		onclick="disableImgInputForID('ansFourImage')">RemoveImage</html:radio>
						 &nbsp;<html:radio property="option4Img_editAction" value="none" 
						 		onclick="disableImgInputForID('ansFourImage')">Do Nothing</html:radio>					  		   
						 <html:file styleId="ansFourImage" style="display:none" property="ansFourImage"/>
					</logic:equal>					
					<div id="option4_ErrMsg" style="display:none;color: red" >Option4 is Required</div>
				</td>
			</tr>

			<tr>
				<td colspan="4">Option 5:</td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="option5" cols="50" rows="3" styleClass="input_field" styleId="option5"/>
					<logic:equal value="1" property="isGraphics" name="ManageQuestionBean" >
						<br/><img src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION5%>"
						  		   alt="NoImage" height="100" width="100"/>
						<br/><html:radio property="option5Img_editAction" value="update" 
								onclick="enableImgInputForID('ansFiveImage')">Add/UpdateImage</html:radio>
						 &nbsp;<html:radio property="option5Img_editAction" value="remove" 
						 		onclick="disableImgInputForID('ansFiveImage')">RemoveImage</html:radio>
						 &nbsp;<html:radio property="option5Img_editAction" value="none" 
						 		onclick="disableImgInputForID('ansFiveImage')">Do Nothing</html:radio>
						<html:file styleId="ansFiveImage" style="display:none" property="ansFiveImage"/>
					</logic:equal>					
					<div id="option5_ErrMsg" style="display:none;color: red" >Option5 is required</div>
				</td>
			</tr>

			<tr>
				<td class="input_label">Answer:<span style="color: red">&#42;</span></td>
				<td colspan="3">
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
					<html:textarea property="answerDiscription" cols="45" styleClass="input_field" styleId="explanation"/>
					<logic:equal value="1" property="isGraphics" name="ManageQuestionBean" >
						<br/><img id="ansExp_ImageValue" src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_ANSWER_EXPLANATION%>"
						  		   alt="NoImage" height="100" width="100"/>
						<br/><html:radio property="ansExpImg_editAction" value="update" 
								onclick="enableImgInputForID('ansExplanation')">Add/UpdateImage</html:radio>
						 &nbsp;<html:radio property="ansExpImg_editAction" value="remove" 
						 		onclick="disableImgInputForID('ansExplanation')">RemoveImage</html:radio>
						 &nbsp;<html:radio property="ansExpImg_editAction" value="none" 
						 		onclick="disableImgInputForID('ansExplanation')">Do Nothing</html:radio>
						<html:file styleId="ansExplanation" style="display:none" property="ansExplanation"/>
					</logic:equal>					
					<div id="explanation_ErrMsg" style="display:none;color: red" >Answer Explanation is Required</div>
				</td>
			</tr>
			<tr>
				<td width="29%" class="input_label">Created By:</td>
				<td width="28%">
					<label> <bean:write property="createdBy" name="ManageQuestionBean" /></label>
					<html:hidden property="createdBy"/>
				</td>
				<td width="29%" align="left" class="input_label">Created On:</td>
				<td>
					<label> <bean:write property="createdOn_dispFormat" name="ManageQuestionBean" /></label>
					<html:hidden property="createdOn"/>
				</td>
			</tr>
			<logic:present property="navigationFromGrid" name="ManageQuestionBean">
				<logic:empty property="navigationFromGrid" name="ManageQuestionBean">
					<tr>
						<td>Save Options</td>				
						<td colspan="1">
						<html:radio property="nextOperation" value="nextQ">Save Insert Next Q</html:radio>
						</td>
						<td>
						<html:radio property="nextOperation" value="close">Save Close</html:radio>
						</td>				
					</tr>
				</logic:empty>
			</logic:present>
			<tr>
				<td align="center" valign="middle" colspan="4">				
				<html:submit property="action" styleClass="url" value="Save" onclick="return validateManageQ()"/>
				<html:submit property="action" styleClass="url" value="Close"/>				
				<logic:empty property="navigationFromGrid" name="ManageQuestionBean">
					<input	name="Submit2" type="reset" class="url" value="Reset" onclick="resetManageQ()"/>
				</logic:empty>				 
				</td>
			</tr>
		</table>
</html:form>
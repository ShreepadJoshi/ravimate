<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<%@page import="com.education.util.EducationConstant"%>
<html:form action="/takeTest.do" method="post">
	<html:hidden property="questionNumber" />
	<html:hidden property="questionID" />
	
	<%
		String qId = request.getAttribute("qId")!=null ? request.getAttribute("qId").toString() : "-1";
		String questionNumber = request.getAttribute("questionNumber")!=null ? request.getAttribute("questionNumber").toString() : "-1";
		String maxNoOfQuestions = request.getAttribute("maxNoOfQuestions")!=null ? request.getAttribute("maxNoOfQuestions").toString() : "-1";
		String totalNoQuesDisplayed = request.getAttribute("totalNoQuesDisplayed") != null ? request.getAttribute("totalNoQuesDisplayed").toString():"-1";
	
		//out.println("QUESTIONID:"+qId);		
		//out.println(" Number:"+questionNumber);
		//out.println(" max:"+maxNoOfQuestions);
	%>	
	
<table width="100%" border="0" cellpadding="0"
	cellspacing="10" bgcolor="#F0F0FF">
	<tr>
		<td colspan="2" >
			<div class="qa_row">
				Question : <bean:write name="TestQuestionBean" property="questionNumber"/> 
				<br/><br/>
				<bean:write name="TestQuestionBean" property="questionDescription"/>
			</div>
			
		</td>
	</tr>
	<tr>
		<td width="40%" align="right">1.&nbsp; <html:radio property="answer" value="Option1"/></td>
		<td width="60%" align="left"><bean:write name="TestQuestionBean" property="option1"/></td>
	</tr>
	<logic:equal value="1" name="TestQuestionBean" property="hasOption1Img">	
		<tr>
			<td colspan="2">
				<br/>
				<img id="option1_ImageValue" src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION1%>"
					 alt="NoImage" height="100" width="100"/>
			</td>
		</tr>
	</logic:equal>
	<tr>
		<td width="40%" align="right">2.&nbsp; <html:radio property="answer" value="Option2"/></td>
		<td width="60%" align="left"><bean:write name="TestQuestionBean" property="option2"/></td>
	</tr>
	<logic:equal value="1" name="TestQuestionBean" property="hasOption2Img">
		<tr>
			<td colspan="2">
				<br/>
				<img id="option2_ImageValue" src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION2%>"
					 alt="NoImage" height="100" width="100"/>
			</td>
		</tr>
	</logic:equal>	
	<tr>
		<td width="40%" align="right">3.&nbsp; <html:radio property="answer" value="Option3"/></td>
		<td width="60%" align="left"><bean:write name="TestQuestionBean" property="option3"/></td>
	</tr>
	<logic:equal value="1" name="TestQuestionBean" property="hasOption3Img">
		<tr>
			<td colspan="2">
				<br/>
				<img id="option3_ImageValue" src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION3%>"
					 alt="NoImage" height="100" width="100"/>
			</td>
		</tr>
	</logic:equal>
	<logic:notEmpty name="TestQuestionBean" property="option4">
		<tr>
			<td width="40%" align="right">4.&nbsp; <html:radio property="answer" value="Option4"/></td>
			<td width="60%" align="left"><bean:write name="TestQuestionBean" property="option4"/></td>
		</tr>
		<logic:equal value="1" name="TestQuestionBean" property="hasOption4Img">
			<tr>
				<td colspan="2">
					<br/>
					<img id="option4_ImageValue" src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION4%>"
						 alt="NoImage" height="100" width="100"/>
				</td>
			</tr>
		</logic:equal>
	</logic:notEmpty>
	<logic:notEmpty name="TestQuestionBean" property="option5">
		<tr>
			<td width="40%" align="right">5.&nbsp; <html:radio property="answer" value="Option5"/></td>
			<td width="60%" align="left"><bean:write name="TestQuestionBean" property="option5"/></td>
		</tr>
		<logic:equal value="1" name="TestQuestionBean" property="hasOption5Img">	
			<tr>
				<td colspan="2">
					<br/>
					<img id="option5_ImageValue" src="<html:rewrite page='/imageRender.do'/>?questionId=<%=qId %>&imagefor=<%=EducationConstant.IMAGE_FOR_OPTION5%>"
						 alt="NoImage" height="100" width="100"/>
				</td>
			</tr>
		</logic:equal>
	</logic:notEmpty>
	<tr>
		<td align="center" colspan="2">
			<logic:equal value="1" name="TestQuestionBean" property="questionNumber">
				<html:submit property="action" value="Previous"  disabled="true" />&nbsp;
			</logic:equal>
			<logic:greaterThan value="1" name="TestQuestionBean" property="questionNumber">
				<html:submit property="action" value="Previous" />&nbsp;
			</logic:greaterThan>
			<%if(questionNumber.equals(totalNoQuesDisplayed)){  %>			
				<html:submit property="action" value="Next" disabled="true" />&nbsp;
			<%}else{%>
				<html:submit property="action" value="Next" onclick="javascript:testSubmit('<%=questionNumber%>');" />&nbsp;
			<%} %>
			<html:submit property="action" value="Save & Next" />&nbsp;
			<html:submit property="action" value="Revisit" />&nbsp;
			<span style="text-align: right;">
			<html:submit property="action" value="TestStatus" />&nbsp;
			<html:submit property="action" value="Finish" />
			</span>
		</td>
	</tr>
	
</table>
</html:form>
<%@page import="com.education.Session.UserSessionInfo"%>
<%@page import="com.education.Session.SessionConstants"%>
<%@page import="com.education.util.EducationConstant"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="com.education.util.EducationConstant"%>
<link type="text/css" href="http://localhost:8080/displaytag-examples-1.2/css/alternative.css" rel="stylesheet" />
<html:form action="/teacherQuestionList" styleId="questionListFormID" method="post">

<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>	
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">
	<tr>
		<td class="input_label">Class / Cert:</td>
		<td colspan="2"  align ="left" width="10"> 
			
			<label>
				<html:select property="sch_classType" styleClass="input_field" onchange="populateDropdown('sch_subject','classId='+this.value,'0','0','subjectlist','subjectDropdown',''+QUESTIONLIST_ADD_REC_SUBJECTDD)">>
					<html:optionsCollection property="classTypeOptions"/>
				</html:select>
				
			</label></td>
		
		<td colspan="1" height="30" align="right" valign="middle">
			<a onclick="javascript:document.getElementById('questionListFormID').submit();">
				<img border="0" src="<html:rewrite page="/images/search.jpg"/>"
					valign="middle" alt="Search" width="20" height="20" />
			</a>
		</td>

	</tr>
	<tr>
		<td class="input_label">Subject:</td>
		<td colspan="1" align ="left" width="10">
			<label>
			<div id="subjectDropdown">
				<html:select property="sch_subject" styleClass="input_field"
					onchange="populateDropdown('sch_topic','classId='+document.getElementById('sch_classType').value+'','subjectId='+document.getElementById('sch_subject').value+'','0','topiclist','topicDropdown',''+QUESTIONLIST_ADD_REC_TOPICDD)">
					<html:optionsCollection property="subjectOptions"/>
				</html:select>
			</div>	
			</label>
		</td>


		<td class="input_label">Topic:</td>
		<td colspan="5" width="15" align="left">
			<label>
				<div id="topicDropdown">
					<html:select property="sch_topic" styleClass="input_field">
						<html:optionsCollection property="topicOptions"/>
					</html:select>
				</div>
			 </label>
		</td>
	</tr>
	<tr>
		<td class="input_label">Status:</td>
		<td colspan="1" align ="left" width="10">
			<label>
				<html:select property="sch_questionStatus" styleClass="input_field">
					<html:optionsCollection property="questionStatusOptions"/>
				</html:select>
			 </label>
		</td>
	</tr>
	<tr>
		<td class="input_label" >Is Graphics:</td>
		<td colspan="2" align ="left" >
			<html:checkbox property="sch_isGraphics" styleClass="input_field" value="1"/>
		</td>
	</tr>
	<tr>
		<td class="input_label">From Date:</td>
		<td colspan="0">
			<html:text styleId="sch_fromDate" property="sch_fromDate" 
					styleClass="input_field" maxlength="11" size="11" readonly="true"/>
			<img border="0" src="<html:rewrite page="/images/dateImg.gif"/>"
					valign="middle" title='Click Here' alt='Click Here'
					onclick='scwShow(scwID("sch_fromDate"),event);' />
			<br/>
			<label><%=EducationConstant.DISPLAY_DATE_FORMAT %></label>
		</td>
		<td class="input_label">To Date:</td>
		<td colspan="0">
			 <html:text styleId="sch_toDate" property="sch_toDate" 
				 	styleClass="input_field" maxlength="11" size="11" readonly="true" />
			<img border="0" src="<html:rewrite page="/images/dateImg.gif"/>"
					valign="middle" title='Click Here' alt='Click Here'
					onclick='scwShow(scwID("sch_toDate"),event);' />
			<br/>
			<label><%=EducationConstant.DISPLAY_DATE_FORMAT %></label>			
		</td>
	</tr>
</table>
<table width="100%" border="1" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">
<tr><td>
<display:table id="row" name="AdminQuestionListBean.pgSearchResults" 
	 requestURI="/adminQuestionList.do" style="width:100%"
	decorator="com.education.displaytag.TeacherQuestionListTableDecorator" class="its">	
	<display:column property="questionId" title="<input type='checkbox' id='allchkBoxID' onclick='checkAll(this.id,\"hiddenIdList\")'/>" />
	<display:column title="Question Description" property="question" maxLength="48" style="text-align:left;"/>
	<display:column property="questionStatusId" title="Status" />
	<display:column property="createdOn" title="Creation Date" decorator="com.education.displaytag.DateColumnDecorator"/>
	<display:setProperty name="basic.empty.showtable" value="true"/>
	<display:footer>
		<html:submit property="action" value="Delete" styleClass="url"/>&nbsp;&nbsp;&nbsp;&nbsp;
		<html:submit property="action" value="MarkAsInActive" styleClass="url"/>
	</display:footer>
</display:table>
</td></tr>
</table>
</html:form>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="com.education.util.EducationConstant"%>
<link type="text/css" href="http://localhost:8080/displaytag-examples-1.2/css/alternative.css" rel="stylesheet" />
<html:form action="/adminQuestionList" styleId="questionListFormID" method="post">
<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>	
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">

	<tr>
		<td class="input_label">Class / Cert:</td>
		<td colspan="2"  align ="left" width="10"> 
			
			<label>
				<html:select property="sch_classType" styleClass="input_field">
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
			<html:select property="sch_subject" styleClass="input_field">
					<html:optionsCollection property="subjectOptions"/>
				</html:select>
			</label>
		</td>


		<td class="input_label">Topic:</td>
		<td colspan="5" width="15" align="left">
			<label>
				<!-- <html:text property="sch_topic" styleClass="input_field" maxlength="20" size="20" /> -->
				<html:select property="sch_topic" styleClass="input_field">
					<html:optionsCollection property="topicOptions"/>
				</html:select>
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
	decorator="com.education.displaytag.AdminQuestionListTableDecorator" class="its">
	
	<display:column property="questionId" title="<input type='checkbox' id='allchkBoxID' onclick='checkAll(this.id,\"hiddenIdList\")'/>" />
	<display:column title="Question Description" property="question" maxLength="48"/>
	<display:column property="questionStatusId" title="Status" />
	<display:column property="createdOn" title="Creation Date" decorator="com.education.displaytag.DateColumnDecorator"/>
	<display:setProperty name="basic.empty.showtable" value="true"/>
		
	<display:footer>
		<html:submit property="action" value="Approve" styleClass="url"/>&nbsp;&nbsp;&nbsp;&nbsp;
		<html:submit property="action" value="Reject" styleClass="url"/>&nbsp;&nbsp;&nbsp;&nbsp;	
		<html:submit property="action" value="Delete" styleClass="url"/>&nbsp;&nbsp;&nbsp;&nbsp;
		<html:submit property="action" value="MarkAsInActive" styleClass="url"/>
	</display:footer>
</display:table>

</td></tr>
</table>
</html:form>


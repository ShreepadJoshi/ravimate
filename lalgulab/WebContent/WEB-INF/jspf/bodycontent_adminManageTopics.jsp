<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="com.education.transferobj.TopicSubTopicTO"%>

<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">	
<html:form styleId="userListFormID" action="/manageTopics.do" method="POST" >	
	<tr>
		<td width="30%" class="input_label">
			Class/Cert:
		</td>
		<td colspan="2" width="10">			
			<html:select property="sch_classType" styleId="sch_classType" styleClass="input_field"
					onchange="populateDropdown('sch_subject','classId='+this.value,'subjectlist','subjectDropdown',''+CONTENTUPLOADPG_ADD_REC_SUBJECTDD)">
					<html:optionsCollection property="classOptions"/>
			</html:select>
			<div id="sch_classType_ErrMsg" style="display: none;color: red"> Class is Required </div>	
		</td>
	</tr>
	<tr>
		<td width="30%" class="input_label">
			Subject:
		</td>
		<td colspan="2" width="10">
			<html:hidden property="newRecordIndex"/>
			<div id="subjectDropdown">
				<html:select property="sch_subject" styleId="sch_subject" styleClass="input_field">
						<html:optionsCollection property="subjectOptions"/>
				</html:select>
			</div>
			<div id="sch_subject_ErrMsg" style="display: none;color: red"> Subject is Required </div>
		</td>

		<td height="30" align="right" valign="middle">
		<a onclick="valManageTopic_SubTopicSchPg('userListFormID');">
			 <img border="0" src="<html:rewrite page='/images/search.jpg'/>"
			 	 valign="middle" alt="Search" width="20" height="20" />
		</a>
		</td>
	</tr>

	<tr>

		<td class="blue_bold">Subject Topic List</td>
		<td align="right" valign="middle"  colspan="5" >
		<html:submit property="action" value="Add" styleClass="url"/> </td>
	</tr>

	<tr>


		<td colspan="4" align="center">
			<!-- Used to Enable and disable the Action Buttons on Page -->
			<input type="hidden" id="actionBtnList" value="addorUpdate,delete"/>
					
			<display:table id="row" name="ManageTopicBean.pgSearchResults" 
				requestURI="/manageTopics.do" decorator="com.education.displaytag.ManageTopicsTableDecorator">
				<display:column property="subjectTopicId" 
					title="<input type='checkbox' id='allchkBoxID' 
					onclick='checkAll(this.id,\"hiddenIdList\",\"actionBtnList\")'/>"/>										
				<display:column style="width:50" title="Topic">
					<%if( ((TopicSubTopicTO)row).getSubjectTopicId() > 0 ){ %>								
						<html:text property="topicList" name="ManageTopicBean" styleClass="input_field" 
							value="<%=((TopicSubTopicTO)row).getTopicvalue()%>"/>
					<%}else{ %>
						<html:text style="color: red;border-color: red;border-width: thin; " styleClass="input_field"
							 property="topicList" name="ManageTopicBean" 
							 value="<%=((TopicSubTopicTO)row).getTopicvalue()%>"/>
					<%}%>
					<input type="hidden" name="topicIds" value="<%=((TopicSubTopicTO)row).getTopicId() %>" />
				</display:column>
				<display:column title="SubTopic">
					<%if( ((TopicSubTopicTO)row).getSubjectTopicId() > 0 ){ %>
							<html:text property="subTopicList" name="ManageTopicBean" styleClass="input_field"
								value="<%=((TopicSubTopicTO)row).getSubTopicValue()%>"/>
					<%}else{ %>
							<html:text style="color: red;border-color: red;border-width: thin; " styleClass="input_field"
								property="subTopicList" name="ManageTopicBean" 
								value="<%=((TopicSubTopicTO)row).getSubTopicValue()%>"/>
					<%}%>
					<input type="hidden" name="subTopicIds" value="<%=((TopicSubTopicTO)row).getSubjectTopicId() %>" />
				</display:column>
			</display:table>
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
		<html:submit styleId="addorUpdate" property="action" value="AddOrUpdate" styleClass="url"/>
		<html:submit styleId="delete" property="action" value="Delete" styleClass="url"/>
		 <html:submit styleId="close" property="action" value="Close" styleClass="url"/>	
		</td>
	</tr>
	<tr>
		<td>
		</td>
	</tr>	
	</html:form>	
</table>


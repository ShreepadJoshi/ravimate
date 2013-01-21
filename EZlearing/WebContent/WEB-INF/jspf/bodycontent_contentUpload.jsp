
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@page import="com.education.transferobj.ContentUploadTO"%>
<%@page import="com.education.formbeans.ContentUploadActionForm"%>
<%@page import="com.education.util.Utilities"%>
<%@ page language="java" session="true"%>
<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>
<html:form styleId="userListFormID" action="/contentUpload.do" method="POST" 
				enctype="multipart/form-data">
<table width="100%" border="0" id="row" align="center" cellpadding="0"
	cellspacing="10" bgcolor="#F0F0FF">			
	<logic:empty property="actionPerformed" name="ContentUploadBean">		
	<tr>

		<td width="30%" class="input_label">Class / Certification:</td>
		<td colspan="2" align="left">
			<html:hidden property="newRecordIndex"/>
			<html:select property="sch_classType" styleClass="input_field" name="ContentUploadBean" 
								onchange="">
				<html:optionsCollection property="classTypeOptions" label="label" value="value" />
				
			</html:select>
		</td>
	</tr>

	<tr>
		<td class="input_label">Subject:</td>
		<td colspan="2" align="left">
			<html:select property="sch_subject" styleClass="input_field">
				<html:optionsCollection property="subjectOptions" />
			</html:select>
		</td>

		<td height="30" align="right" valign="middle">
			<a onclick="javascript:document.getElementById('userListFormID').submit();">
				 <img border="0" src="<html:rewrite page='/images/search.jpg'/>"
			 	 valign="middle" alt="Search" width="20" height="20" />
			</a>
		</td>
	</tr>
</logic:empty>
	<tr>

		<td colspan="4" align="center">
			<!--  Table will go here -->
			<display:table id="row" name="ContentUploadBean.pgSearchResults" 
				requestURI="/contentUpload.do" decorator="com.education.displaytag.ContentUploadTableDecorator">
				<display:column style="width:20"  property="contentId" title=	""/>										
					
				<display:column style="width:120px" title="Topic">
					 <span class="text"><%=((ContentUploadTO)row).getTopicValue()%></span>
				</display:column>
				<display:column  style="width:20"  title="SubTopic">
					<span class="text"><%=((ContentUploadTO)row).getSubTopicValue()%></span>
				</display:column>
				<display:column  style="width:20"  title="Type">
					<span class="text"><%= Utilities.getContentType_AsString(
							( (ContentUploadTO)row).getContentTypeID() )%></span>
				</display:column>
			
				<display:column title="ContentName" >
					<bean:define id="roleType"  property="affiliateRole" 
						value="<%=((ContentUploadTO)row).getContentId()  %>" /> 
					<html:link target="_blank" forward="ContentRenderer" paramId="contentId" 
						property="affiliateRole" paramName="roleType">
						<span class="text"><%=((ContentUploadTO)row).getContentFileName()%></span>
					</html:link> 
					
				</display:column>
				<display:column title="Supporting File">
					<span class="text">
					<%if( ((ContentUploadTO)row).getSupportingtFileName() ==null || ((ContentUploadTO)row).getSupportingtFileName().equals("") ){%>
						 N/A
					<%}else{ %>
						<%=((ContentUploadTO)row).getSupportingtFileName()%>
					<%}
					
					%>
					 </span>
				</display:column>				
			</display:table>
		</td>
		</tr>
	<tr>
		<td colspan="4" align="center">
			<logic:notEmpty property="actionPerformed" name="ContentUploadBean">
				<logic:equal value="updatecontent" property="actionPerformed" name="ContentUploadBean">
					Content Update Panel
				</logic:equal>
				<logic:equal value="addcontent" property="actionPerformed" name="ContentUploadBean">
					Add Content Panel
				</logic:equal>
			</logic:notEmpty>
			<logic:empty property="actionPerformed" name="ContentUploadBean">			
				<div id="actionPanel">
					<html:submit property="action" value="AddContent" styleClass="url" />
					<html:submit property="action" value="updateContent" styleClass="url" />
					<html:submit property="action" value="Delete" styleClass="url" />
					<html:submit property="action" value="close" styleClass="url" />
				</div>
			</logic:empty>			
		</td>
	</tr>	
</table>
</html:form>


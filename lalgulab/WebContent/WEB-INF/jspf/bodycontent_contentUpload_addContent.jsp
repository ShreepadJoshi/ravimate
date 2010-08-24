<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@page import="com.education.transferobj.ContentUploadTO"%>
<%@page import="com.education.formbeans.ContentUploadActionForm"%>

<center><span style="color: red; background-color: #F0F0FF"><html:errors /></span></center>
<table width="100%" border="0" align="left" cellpadding="0"
	cellspacing="10" bgcolor="#F0F0FF">
	

	<tr>
		<td COLSPAN="1" align="left"><html:form
			styleId="userListFormID" action="/contentUpload.do" method="POST"
			enctype="multipart/form-data">
			<logic:notEmpty property="actionPerformed" name="ContentUploadBean">
				<logic:equal value="addcontent" property="actionPerformed"
					name="ContentUploadBean">
					
					<display:table id="row" name="contentUpload.pgSearchResults" requestURI="/contentUpload.do">
				<display:column style="padding-left:10px;" >
					<html:select property="classList" name="ContentUploadBean" styleClass="input_field"
							value="<%=((ContentUploadTO)row).getClassId()%>">							
						<html:optionsCollection property="classTypeOptions" label="label" value="value"/>
					</html:select>					
				</display:column>
				</display:table>
						<tr>
							<td width="10" class="input_label" colspan="1" align="left">Class/Certificate<span
								style="color: red">&#42;</span></td>
								<td colspan="1" align="left">
							<html:select
								styleId="uptClassId" property="uptClassId"
								name="ContentUploadBean" styleClass="input_field"
								onchange="populateDropdown('uptsubjectId','classId='+this.value,'subjectlist','subjectDropdown',''+CONTENTUPLOADPG_ADD_REC_SUBJECTDD)">
								<html:optionsCollection property="classTypeOptions" label="label" value="value" />
							</html:select>
							
							<div id="uptClassId_ErrMsg" style="display: none; color: red">Class
							is Required</div>
							</td>
						</tr>
						<tr>
							<td width="20%" class="input_label" colspan="1" align="left">Subject<span style="color: red">&#42;</span></td>
							<td colspan="2" align="left">
							<div id="subjectDropdown"><html:select
								styleId="uptsubjectId" property="uptsubjectId"
								name="ContentUploadBean" styleClass="input_field"
								onchange="populateDropdown('uptTopicId','subjectId='+this.value,'topiclist','topicDropdown',''+CONTENTUPLOADPG_ADD_REC_TOPICDD)">
								<html:optionsCollection property="subjectOptions" label="label"
									value="value" />
							</html:select></div>
							<div id="uptsubjectId_ErrMsg" style="display: none; color: red">Subject
							is Required</div>
							</td>
						</tr>
						<tr>
							<td class="input_label">Topic<span style="color: red">&#42;</span></td>
							<td colspan="2" align="left">
							<div id="topicDropdown"><html:select styleId="uptTopicId"
								property="uptTopicId" name="ContentUploadBean"
								styleClass="input_field"
								onchange="populateDropdown('uptsubTopicId','subjectId='+document.getElementById('uptsubjectId').value+',topicValue='+this.value,'subtopiclist','subTopicDropdown',''+CONTENTUPLOADPG_ADD_REC_SUBTOPICDD)">
								<html:optionsCollection property="topicOptions" label="label"
									value="value" />
							</html:select></div>
							<div id="uptTopic_ErrMsg" style="display: none; color: red">Topic
							is Required</div>
							</td>
						</tr>
						<tr>
							<td class="input_label" colspan="1" align="left">SubTopic<span style="color: red">&#42;</span></td>
							<td colspan="1" align="left">
							<div id="subTopicDropdown"><html:select
								styleId="uptsubTopicId" property="uptsubTopicId"
								name="ContentUploadBean" styleClass="input_field">
								<html:optionsCollection property="subTopicOptions" label="label"
									value="value" />
							</html:select></div>
							<div id="uptsubTopic_ErrMsg" style="display: none; color: red">SubTopic
							is Required</div>
							</td>
						</tr>
						<tr>
							<td class="input_label" colspan="1" align="left">Content Type<span style="color: red">&#42;</span></td>
							<td colspan="1" align="left"><html:select styleId="uptContentType"
								property="uptContentTypeID" styleClass="input_field">
								<html:optionsCollection property="contentTypeOptions" />
							</html:select>
							<div id="uptContentType_ErrMsg" style="display: none; color: red">ContentType
							is Required</div>
							</td>
						</tr>
						<tr>
							<td width="10%" class="input_label" colspan="1" align="left">ContentFile<span style="color: red">&#42;</span></td>
							<td colspan="1" align="left"><html:file styleId="contentFileStream"
								property="contentFileStream" styleClass="input_field" />
							<div id="contentFileStream_ErrMsg"
								style="display: none; color: red">Content is Required</div>
							</td>
						</tr>
						<tr>
							<td class="input_label">SupportingFile</td>
							<td colspan="1" align="left"  ><html:file styleId="suppFileStream"
								property="suppFileStream" styleClass="input_field" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><html:submit
								property="action" value="Add" styleClass="url"
								onclick="return valContentUploadPg('add');" /> <html:submit
								property="action" value="Cancel" styleClass="url" /></td>
						</tr>
					
				</logic:equal>
			</logic:notEmpty></td>
	</tr>
	
	<tr>
		<td></html:form></td>
	</tr>
	<script type="text/javascript">		
			var varUptsubjectId = document.getElementById("uptsubjectId");			
			populateDropdown('uptTopicId','subjectId='+varUptsubjectId.value,'topiclist','topicDropdown',''+CONTENTUPLOADPG_ADD_REC_TOPICDD);
	</script>
</table>



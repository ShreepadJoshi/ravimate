
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>	
	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="10" bgcolor="#F0F0FF">
		<tr>
			<td colspan="4" align="center">
			<html:form styleId="userListFormID" action="/contentUpload.do"
				method="POST" enctype="multipart/form-data">
			<html:hidden property="contentTypeID"/>
					<div id="updateContentPanel">
					<table width="50%" border="1" align="center" cellpadding="0"
						cellspacing="10" bgcolor="#F0F0FF">
						<tr>
							<td class="input_label">Class/Certificate<span style="color: red">&#42;</span></td>
							<td><html:hidden property="uptContentid" /> <html:select
								styleId="uptClassId" property="uptClassId"
								name="ContentUploadBean" styleClass="input_field"
								onchange="populateDropdown('uptsubjectId','classId='+document.getElementById('uptClassId').value+'','0','0','subjectlist','subjectDropdown',''+CONTENTUPLOADPG_ADD_REC_SUBJECTDD)">
								<html:optionsCollection property="classTypeOptions"
									label="label" value="value" />
							</html:select>
							<div id="uptClassId_ErrMsg" style="display: none; color: red">Class
							is Required</div>
							</td>
						</tr>
						<tr>
							<td class="input_label">Subject<span style="color: red">&#42;</span></td>
							<td>
							<div id="subjectDropdown"><html:select
								styleId="uptsubjectId" property="uptsubjectId"
								name="ContentUploadBean" styleClass="input_field"
								onchange="populateDropdown('uptTopicId','classId='+document.getElementById('uptClassId').value+'','subjectId='+document.getElementById('uptsubjectId').value+'','0','topiclist','topicDropdown',''+CONTENTUPLOADPG_ADD_REC_TOPICDD)">
								<html:optionsCollection property="subjectOptions" label="label"
									value="value" />
							</html:select></div>
							<div id="uptsubjectId_ErrMsg" style="display: none; color: red">Subject
							is Required</div>
							</td>
						</tr>
						<tr>
							<td class="input_label">Topic<span style="color: red">&#42;</span></td>
							<td>
							<div id="topicDropdown"><html:select styleId="uptTopicId"
								property="uptTopicId" name="ContentUploadBean"
								styleClass="input_field"
								onchange="populateDropdown('uptsubTopicId','classId='+document.getElementById('uptClassId').value+'','subjectId='+document.getElementById('uptsubjectId').value+'','topicId='+document.getElementById('uptTopicId').value+'','subtopiclist','subTopicDropdown',''+END)" onfocus="populateDropdown('uptTopicId','classId='+document.getElementById('uptClassId').value+'','subjectId='+document.getElementById('uptsubjectId').value+'','0','topiclist','topicDropdown',''+CONTENTUPLOADPG_ADD_REC_TOPICDD)">
								<html:optionsCollection property="topicOptions" label="label"
									value="value" />
							</html:select></div>
							<div id="uptTopic_ErrMsg" style="display: none; color: red">Topic
							is Required</div>
							</td>
						</tr>
						<tr>
							<td class="input_label">SubTopic<span style="color: red">&#42;</span></td>
							<td>
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
							<td class="input_label">Content Type<span style="color: red">&#42;</span></td>
							<td>
							<html:select styleId="uptContentType"
								property="uptContentTypeID" styleClass="input_field">
								<html:optionsCollection property="contentTypeOptions"/>
							</html:select>
							<div id="uptContentType_ErrMsg" style="display: none; color: red">ContentType
							is Required</div>
							</td>
						</tr>
						<tr>
							<td class="input_label">ContentFileName</td>
							<td>
							<html:text property="uptcontentFileName" readonly="true"  styleClass="input_field"/>						
							</td>
						</tr>
						<tr>
							<td class="input_label">SupportingFileName</td>
							<td>
								<logic:empty property="uptsuppFileName" name="ContentUploadBean">
									<input type="text" class="input_field" value="N/A"/>
									<html:hidden property="uptsuppFileName" />
								</logic:empty>
								<logic:notEmpty property="uptsuppFileName" name="ContentUploadBean">
									<html:text property="uptsuppFileName" readonly="true"  styleClass="input_field"/>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td class="input_label">New ContentFile</td>
							<td><html:file property="contentFileStream"
								styleClass="input_field" /></td>
						</tr>
						<tr>
							<td class="input_label">New SupportingFile</td>
							<td><html:file property="suppFileStream"
								styleClass="input_field" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><html:submit
								property="action" value="Update" styleClass="url"
								onclick="return valContentUploadPg('update');" /> <html:submit
								property="action" value="Cancel" styleClass="url" /></td>								
						</tr>
					</table>
					</div>
				</td>
		</tr>
		<tr>
			<td></html:form></td>
		</tr>
	</table>



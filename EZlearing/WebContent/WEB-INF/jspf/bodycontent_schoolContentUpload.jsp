<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@page import="com.education.transferobj.ContentUploadTO"%>
<center><span style="color: red; background-color: #F0F0FF"><html:errors /></span></center>
	<form action="schoolContentUpload.do" method="POST"
			enctype="multipart/form-data"">
				<table width="50%" border="1" align="center" cellpadding="0"
						cellspacing="10" bgcolor="#F0F0FF">
					<tr>
							<td class="input_label">ContentFile<span style="color: red">&#42;</span></td>
							<td><input type="file" class="input_field" name="contentFileStream">
							<div id="contentFileStream_ErrMsg" 
								style="display: none; color: red">Content is Required</div>
							</td>
						</tr>						
						<tr>
							<td colspan="2" align="center"><input type="submit" value="Add" name="action" class="url"> 
							<input type="submit" value="Cancel" name="action" class="url">
							</td>
						</tr>
					
			
				</table>
	</form>

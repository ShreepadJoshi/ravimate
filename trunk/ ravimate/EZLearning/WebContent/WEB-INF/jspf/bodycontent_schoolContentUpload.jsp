<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@page import="com.education.transferobj.ContentUploadTO"%>



<html:form action="/schoolContentUpload.do"
	enctype="multipart/form-data">
 Select File : <html:file styleId="contentFileStream"
		property="contentFileStream" styleClass="input_field"></html:file>
	<tr>
		<td colspan="2" align="center"><input type="submit" value="Add"
			name="action" class="url"
			onclick="return valSchoolContentUploadPg('add');"> <input
			type="button" value="Cancel" name="" class="url"
			onclick="return validateCancel()"></td>
	</tr>
</html:form>

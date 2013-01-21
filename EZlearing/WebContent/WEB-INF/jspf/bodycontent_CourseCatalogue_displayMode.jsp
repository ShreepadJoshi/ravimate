<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.education.util.EducationConstant"%>
<%@page import="com.education.util.Utilities"%>
<%@page import="java.util.Date"%>
<%@page import="com.education.transferobj.CourseCatalogueTO"%>
<%@page import="java.util.ArrayList"%>

<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">	
	<tr>		
		<html:form action="/courseCatalogue" method="post" styleId="registrationpage_formID" 
		onsubmit="return validateAgentFullReg()">
		
		<tr>
			<td class="input_label">class:<span style="color: red">&#42;</span></td>
			<td class="input_label">Subject:<span style="color: red">&#42;</span></td>
			<td class="input_label">Cost:<span style="color: red">&#42;</span></td>
			<td class="input_label">Board:<span style="color: red">&#42;</span></td>
		</tr>
		<%
			ArrayList list = (ArrayList)session.getAttribute("CourseCatalogueTO");
			if(list != null){
			for(int i = 0; i<list.size(); i++){
				CourseCatalogueTO to = (CourseCatalogueTO)list.get(i);	
		%>		
		<td>
			<input type="text" name="class" readonly="true" maxlength="50" value="<%=to.getClassName() %>" id="lastName" class="input_field">
			<div id="lastName_ErrMsg" style="display: none;color: red">LastName is Required </div>
		</td>
		<td>
			<input type="text" name="Subject" readonly="true" maxlength="50" value="<%=to.getSubjectName()%>" id="lastName" class="input_field">
			<div id="lastName_ErrMsg" style="display: none;color: red">LastName is Required </div>
		</td>	
		<td>
			<input type="text" name="Cost" readonly="true" maxlength="50" value="<%=to.getSubjectCost()%>" id="lastName" class="input_field">
			<div id="lastName_ErrMsg" style="display: none;color: red">LastName is Required </div>
		</td>	
		<td>
			<input type="text" name="Board" readonly="true" maxlength="50" value="<%=to.getBoardName() %>" id="lastName" class="input_field">
			<div id="lastName_ErrMsg" style="display: none;color: red">LastName is Required </div>
		</td>	
		<td>
		<input type="checkbox" name="checkbox_id" maxlength="50" value="<%=i%>" id="lastName" class="input_field">
			<div id="lastName_ErrMsg" style="display: none;color: red">select </div>
		</td>
		 
	</tr>		
		<%		
			}	
			}
		%>
		
		
	<tr>
		<td colspan="2">
			 <html:submit value="Save" styleClass="url" />
			 <input type="reset" value="Reset" class="url"/>			
			 </html:form>
		</td>
	</tr>
</table>

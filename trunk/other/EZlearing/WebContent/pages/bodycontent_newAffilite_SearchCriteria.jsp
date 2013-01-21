<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="com.education.transferobj.AffiliateTO"%>
<html:form styleId="userListFormID" action="/affiliateRegistration" method="POST">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">
	<tr>
		<td width="20%" class="input_label">First Name:</td>
		<td colspan="2">
			<label>
				<html:text property="firstName" styleClass="input_field" maxlength="20" size="20" />				
			</label>
		</td>
		<td colspan="1" height="30" align="right" valign="middle">
			<a onclick="javascript:document.getElementById('userListFormID').submit();">
				<img border="0" src="<html:rewrite page="/images/search.jpg"/>"
					valign="middle" alt="Search" width="20" height="20" />
			</a>
		</td>
		<html:hidden property="action" value="search"/>
	</tr>

	<tr>
		<td class="input_label">Last Name:</td>
		<td colspan="1">
			<label><html:text property="lastName" styleClass="input_field" maxlength="20" size="20" /></label>			
		</td>


		<td class="input_label">Organization Name:</td>
		<td colspan="0">
			<label><html:text property="lastName" styleClass="input_field" maxlength="20" size="20" /></label>			
		</td>
	</tr>
	<tr>
	<td colspan="4" align="center">
			<!-- Used to Enable and disable the Action Buttons on Page -->
			<input type="hidden" id="actionBtnList" value="addorUpdate,delete"/>
					
			<display:table id="row" name="AffilliateForm.pgSearchResults" 
				requestURI="/affiliateRegistration.do" decorator="com.education.displaytag.ManageAffiliateTableDecorator">
				<display:column property="subjectTopicId" 
					title="<input type='checkbox' id='allchkBoxID' 
					onclick='checkAll(this.id,\"hiddenIdList\",\"actionBtnList\")'/>"/>										
				<display:column style="width:50" title="First Name">
					<%if( ((AffiliateTO)row).getAffiliateId() != null ){ %>								
						<html:text property="firstName" name="AffiliateForm" styleClass="input_field" 
							value="<%=((AffiliateTO)row).getFirstName()%>"/>
					<%}else{ %>
						<html:text style="color: red;border-color: red;border-width: thin; " styleClass="input_field"
							 property="firstName" name="AffiliateForm" 
							 value="<%=((AffiliateTO)row).getFirstName()%>"/>
					<%}%>
					<input type="hidden" name="affiliateId" value="<%=((AffiliateTO)row).getAffiliateId() %>" />
				</display:column>
				<display:column title="Last Name">
					<%if( ((AffiliateTO)row).getLastName() != null ){ %>
							<html:text property="lastName" name="AffiliateForm" styleClass="input_field"
								value="<%=((AffiliateTO)row).getLastName()%>"/>
					<%}else{ %>
							<html:text style="color: red;border-color: red;border-width: thin; " styleClass="input_field"
								property="lastName" name="AffiliateForm" 
								value="<%=((AffiliateTO)row).getLastName()%>"/>
					<%}%>
					<input type="hidden" name="affiliateId" value="<%=((AffiliateTO)row).getAffiliateId() %>" />
				</display:column>
			</display:table>
		</td>
	</tr>
</table>
</html:form>


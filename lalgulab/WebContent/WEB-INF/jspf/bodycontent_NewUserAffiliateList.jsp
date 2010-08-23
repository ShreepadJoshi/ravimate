<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="com.education.util.EducationConstant"%>
<%
	pageContext.setAttribute("roleType",EducationConstant.AFFILIATE_USER_ROLE);
%>


<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">
	<tr>
		<td  class="input_label">First Name:</td>
		<td colspan="1" width="10" >
			<label>
			<html:form styleId="userListFormID" action="/newUserAffiliateListAction.do" method="POST">
			<html:text property="sch_FirstName" styleClass="input_field" maxlength="20" size="20" />				
			</label></td>
		
		<td colspan="1" height="30" align="right" valign="middle">
			<a onclick="javascript:document.getElementById('userListFormID').submit();">
				<img border="0" src="<html:rewrite page="/images/search.jpg"/>"
					valign="middle" alt="Search" width="20" height="20" />
			</a>
		</td>

	</tr>

	<tr>
		<td class="input_label">Last Name:</td>
		<td colspan="1" width="10">
			<label><html:text property="sch_LastName" styleClass="input_field" maxlength="20" size="20" /></label>			
		</td>


		<td>&nbsp;</td>
		<td colspan="0">
			
		</td>
	</tr>
	<tr>
		<td class="input_label">EmailID:</td>
		<td colspan="1" width="10">
		<html:text property="sch_EmailID" styleClass="input_field" maxlength="50" size="30" />
		</td>
	</tr>
	<tr>
		<td class="input_label">Reg Status:</td>
		<td colspan="1" width="10">
		<html:select property="sch_RegStatus" styleClass="input_field">
				<html:optionsCollection property="regStatusOptions"/>
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="input_label">Reg FromDate:</td>
		<td colspan="0" align="left">
			<html:text styleId="sch_fromDate" property="sch_fromDate" 
					styleClass="input_field" maxlength="11" size="11" readonly="true"/>
			<img border="0" src="<html:rewrite page="/images/dateImg.gif"/>"
					valign="middle" title='Click Here' alt='Click Here'
					onclick='scwShow(scwID("sch_fromDate"),event);' />
			<br/>
			<label><%=EducationConstant.DISPLAY_DATE_FORMAT %></label>
		</td>

		<td class="input_label">Reg ToDate:</td>
		<td align="left" >
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
	<display:table id="row" name="NewUserBean.pgSearchResults" 
	 requestURI="/newUserAffiliateListAction.do" decorator="com.education.displaytag.NewUserListTableDecorator" style="width:100%">
	
		<display:column property="userId" title="<input type='checkbox' id='allchkBoxID' onclick='checkAll(this.id,\"hiddenIdList\")'/>" />
		 	<display:column property="firstName" title="Name" maxLength="48"/>
			<display:column property="isApproved" title="Reg Status" decorator="com.education.displaytag.RegistrationStatusColumnDecorator" />
			<display:column property="registrationDate" title="Reg Date" decorator="com.education.displaytag.DateColumnDecorator"/>
		<display:footer>
			
		
		<html:submit property="action" value="ApproveLogin" styleClass="url"/>&nbsp;&nbsp;&nbsp;&nbsp;
		<html:submit property="action" value="DisableLogin" styleClass="url"/>&nbsp;&nbsp;&nbsp;&nbsp;		
		<html:submit property="action" value="DeleteAffiliate" styleClass="url"/>
	</display:footer>
</display:table></html:form>
</td></tr>
</table>




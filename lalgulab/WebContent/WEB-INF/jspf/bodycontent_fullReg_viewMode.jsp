<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="com.education.util.EducationConstant"%>
<%@page import="com.education.Session.SessionConstants"%>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">	
	<tr>
		<td width="29%" class="input_label">
		<html:form action="/fullRegistration" method="post" styleId="registrationpage_formID">
		First Name :<span style="color: red">&#42;</span></td>
		<td colspan="1" align="left"><label>
			<html:hidden property="roleType"/>
			<html:hidden property="navFromPage"/>
			<html:hidden property="userId"/>			
			<html:text property="firstName" styleClass="input_field" styleId="firstName" readonly="true"/>
			<div id="firstName_ErrMsg" style="display: none;color: red"> FirstName is Required </div>
		 </label></td>
	</tr>
	<tr>
		<td class="input_label">Last Name:<span style="color: red">&#42;</span></td>
		<td colspan="1" align="left">
			<html:text property="lastName" styleClass="text" styleId="lastName" styleClass="input_field" readonly="true"/>
			<div id="lastName_ErrMsg" style="display: none;color: red">LastName is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">Email:<span style="color: red">&#42;</span></td>
		<td colspan="1" align="left">
			<html:text property="emailID" styleClass="input_field" styleId="emailID" readonly="true"/>
			<div id="emailID_ErrMsg" style="display: none;color: red">Invalid EmailID</div>
		</td>
	</tr>
	<tr>
		<td class="input_label">Password:<span style="color: red">&#42;</span></td>
		<td colspan="1" align="left">
			<html:password property="password" styleClass="input_field" styleId="password" readonly="true"/>
			<div id="password_ErrMsg" style="display: none;color: red">Password is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">Sex:</td>
		<td colspan="1" align="left"><label>
		 <html:radio property="sex" value="male" disabled="true" styleClass="input_field" ></html:radio> Male
		  <html:radio property="sex" value="female" disabled="true" styleClass="input_field" ></html:radio> Female </label></td>
	</tr>
	<tr>
		<td class="input_label">Role:</td>
		<td colspan="1" align="left">
		<label>
		 <bean:write name="FullRegistrationBean" property="roleName" />
        </label></td>
	</tr>
	<tr>
		<td class="input_label">Registration Date:</td>
		<td colspan="1" align="left"><bean:write name="FullRegistrationBean" property="regDate_displayFormat" /></td>
	</tr>
	
	<tr>
		<td colspan="1" align="left"><strong>Contact
		Information: </strong></td>
	</tr>
	<tr>
		<td class="input_label">Address:<span style="color: red">&#42;</span></td>
		<td colspan="1" align="left">
			<html:textarea property="address" styleClass="input_field" styleId="address" readonly="true"/>
			<div id="address_ErrMsg" style="display: none;color: red">Address is Required </div>			
		</td>
	</tr>
	<tr>
		<td class="input_label">Country:<span style="color: red">&#42;</span></td>
		<td colspan="1" align="left">
			<html:text property="country" styleClass="input_field" styleId="country" readonly="true"/>
			<div id="country_ErrMsg" style="display: none;color: red">Country is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">State:<span style="color: red">&#42;</span></td>
		<td colspan="1" align="left">
			<html:text property="state" styleClass="input_field" styleId="state" readonly="true"/>
			<div id="country_ErrMsg" style="display: none;color: red">state is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">City:<span style="color: red">&#42;</span></td>
		<td colspan="1" align="left">
			<html:text property="city" styleClass="input_field" styleId="city" readonly="true"/>
			<div id="city_ErrMsg" style="display: none;color: red">City is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">PIN:<span style="color: red">&#42;</span></td>
		<td colspan="1" align="left">
			<html:text property="pin" styleClass="input_field" size="8" maxlength="6" styleId="pin" readonly="true"/>
			<div id="pin_ErrMsg" style="display: none;color: red">Pin is Required </div>
		</td>
	</tr>
	
	<tr>
		<td class="input_label">Mobile No.:<span style="color: red">&#42;</span></td>
		<td colspan="1" align="left">
		<html:text property="mobNoPart1" styleClass="input_field" size="5" maxlength="4" styleId="mobNoPart1" value="+91" readonly="true"/>
		<html:text property="mobNoPart2" styleClass="input_field" size="10" maxlength="10" styleId="mobNoPart2" readonly="true"/>
		<div id="mobileno_ErrMsg" style="display: none;color: red">MobileNo is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">Land LineNo.:</td>
		<td colspan="1" align="left">
			<html:text  property="isdCode" styleId="isd" styleClass="input_field"
				size="5" maxlength="4" /> -
			<html:text property="stdCode" styleId="std" styleClass ="input_field"
				size="5" maxlength="4" />-
			<html:text  property="landlineNo" styleId="landline" styleClass="input_field"
			  	size="10" maxlength="8" />
			 <div id="landlineno_ErrMsg" style="display: none;color: red">Invaild LandlineNo</div>
		 </td>
	</tr>
	<tr>
		<td class="input_label">Alternate Email:</td>
		<td colspan="1" align="left">
			<html:text property="alternateEmailID" styleClass="input_field" styleId="alternateEmailID" readonly="true"/>
			<div id="alternateEmailID_ErrMsg" style="display: none;color: red">Invalid EmailID </div>
		</td>
	</tr>
	
	<tr>
		<td colspan="2"><strong>Hobbies:</strong></td>
	</tr>
	<tr>
		<td colspan="2">
			<html:textarea property="hobbies" cols="50" rows="3" styleClass="input_field" styleId="hobbies" readonly="true"/>
			<div id="hobbies_ErrMsg" style="display: none;color: red">Invalid Entry </div>
		</td>
	</tr>
	<tr>
		<td colspan="2">			
			<logic:notEmpty property="navFromPage" name="FullRegistrationBean">
				<logic:equal value="<%=EducationConstant.AGENT_USER_ROLE %>" property="navFromPage" name="FullRegistrationBean">
					<html:submit property="action" value="ApproveLogin" styleClass="url"/>
					<html:submit property="action" value="DisableLogin" styleClass="url"/>
				</logic:equal>
				<logic:equal value="<%=EducationConstant.AFFILIATE_USER_ROLE %>" property="navFromPage" name="FullRegistrationBean">
					<html:submit property="action" value="ApproveLogin" styleClass="url"/>
					<html:submit property="action" value="DisableLogin" styleClass="url"/>
				</logic:equal>
				<logic:equal value="<%=EducationConstant.TEACHER_USER_ROLE %>" property="navFromPage" name="FullRegistrationBean">
					<html:submit property="action" value="ApproveLogin" styleClass="url"/>
					<html:submit property="action" value="DisableLogin" styleClass="url"/>
					<html:submit property="action" value="MarkAsReviewer" styleClass="url"/>
				</logic:equal>
				<logic:equal value="<%=EducationConstant.REVIEWER_USER_ROLE %>" property="navFromPage" name="FullRegistrationBean">
					<html:submit property="action" value="ApproveLogin" styleClass="url"/>
					<html:submit property="action" value="DisableLogin" styleClass="url"/>
				</logic:equal>
			</logic:notEmpty>
			<html:submit property="action" value="Back" styleClass="url"/>
		 	</html:form>
		</td>
	</tr>
</table>

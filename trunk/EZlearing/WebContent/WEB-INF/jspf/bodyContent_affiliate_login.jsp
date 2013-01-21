<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="com.education.transferobj.AffiliateTO"%>
<%@page import="com.education.util.Utilities,java.util.Date"%>
<center><span style="color: red;background-color:#F0F0FF "><html:errors/></span></center>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">
<html:form action="/affiliateRegistration.do" method="post" styleId="registrationpage_formID">
	<tr>		
		<td class="input_label">First Name :<span style="color: red">&#42;</span></td>
		<td width="10">
			<html:hidden property="roleType"></html:hidden>			
			<html:text property="firstName" styleClass="input_field" styleId="firstName" maxlength="45" ></html:text>
			<div id="firstName_ErrMsg" style="display: none;color: red"> FirstName is Required </div>
		 </td>
	</tr>
	<tr>
		<td class="input_label">Last Name:<span style="color: red">&#42;</span></td>
		<td width="10">
			<html:text property="lastName" styleClass="input_field" styleId="lastName" maxlength="50"/>
			<div id="lastName_ErrMsg" style="display: none;color: red">LastName is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">Email:<span style="color: red">&#42;</span></td>
		<td width="10">
			<html:text property="emailID" styleClass="input_field" styleId="emailID" maxlength="50" disabled="true"/>
			<div id="emailID_ErrMsg" style="display: none;color: red">Invalid EmailID</div>
		</td>
	</tr>
	<tr>
		<td class="input_label">Password:<span style="color: red">&#42;</span></td>
		<td width="10">
			<html:password property="password" styleClass="input_field" styleId="password" maxlength="50" disabled="true"/>
			<div id="password_ErrMsg" style="display: none;color: red">Password is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">ReType Password:<span style="color: red">&#42;</span></td>
		<td width="10">
			<html:password property="repassword" styleClass="input_field" styleId="repassword" maxlength="50" disabled="true"/>
			<div id="repassword_ErrMsg" style="display: none;color: red">ReType Password is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">Sex:</td>
			<td align="left"> <html:radio property="sex" value="male"></html:radio> Male
		  	<html:radio property="sex" value="female" ></html:radio> Female</td>
	</tr>	
	<tr>
		<td class="input_label">Registration Date:</td>
		<td align="left">
		<%
			out.print(Utilities.getDate_displayFormat(new Date()));
		%>
		</td>
	</tr>
	<tr>
		<td class="input_label">Role:</td>
		<td><bean:write name="FullRegistrationBean" property="roleName" /></td>
	</tr>
	<tr>
		<td colspan="2" align="left"><strong>Contact Information: </strong></td>
	</tr>
	<tr>
		<td class="input_label">Address:<span style="color: red">&#42;</span></td>
		<td width="10">
			<html:textarea property="address" styleClass="input_field" styleId="address" rows="2" cols="50" />
			<div id="address_ErrMsg" style="display: none;color: red">Address is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">Region:<span style="color: red">&#42;</span></td>
		<td width="10">
			<select onchange="set_country(this,country,state)" size="1" name="region" id="region" class="input_field">
				<option value="" selected="selected">SELECT REGION</option>
				<script type="text/javascript">
					setRegions(this);
				</script>
			</select>				
			<div id="region_ErrMsg" style="display: none;color: red">Region is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">Country:<span style="color: red">&#42;</span></td>
		<td width="10">
			<html:select property="country" styleClass="input_field" 
				onchange="set_city_state(this,state)" styleId="country">
			</html:select>				
			<div id="country_ErrMsg" style="display: none;color: red">Country is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">State:<span style="color: red">&#42;</span></td>
		<td width="10">
			<!-- <html:text property="country" styleClass="input_field" styleId="country"/> -->
			<html:select property="state" styleClass="input_field" styleId="state">
			</html:select>				
			<div id="state_ErrMsg" style="display: none;color: red">State is Required</div>
		</td>
	</tr>
	<tr>
		<td class="input_label">City:<span style="color: red">&#42;</span></td>
		<td width="10">
			<html:text property="city" styleClass="input_field" styleId="city"/>
			<div id="city_ErrMsg" style="display: none;color: red">City is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">PIN:<span style="color: red">&#42;</span></td>
		<td width="10">
			<html:text property="pin" styleClass="input_field" size="8" maxlength="6" styleId="pin"/>
			<div id="pin_ErrMsg" style="display: none;color: red">Pin is Required </div>
		</td>
	</tr>	
	<tr>
		<td class="input_label">Mobile No.:<span style="color: red">&#42;</span></td>
		<td width="10">
			<html:text property="mobNoPart1" styleClass="input_field"
				 size="5" maxlength="4" styleId="mobNoPart1" value="+91"/>-
			<html:text property="mobNoPart2" styleClass="input_field" value="NUMBER"
				onfocus="javascript:if(this.value=='NUMBER'){this.value=''};"
				onblur="javascript:if(this.value==''){this.value='NUMBER';}"
				 size="10" maxlength="10" styleId="mobNoPart2"/>
			<div id="mobileno_ErrMsg" style="display: none;color: red">MobileNo is Required </div>
		</td>
	</tr>
	<tr>
		<td class="input_label">Land LineNo:</td>
		<td width="10">
			<html:text  property="isdCode" styleId="isd" styleClass="input_field" value="ISD"
				onfocus="javascript:if(this.value=='ISD'){this.value=''};"
				onblur="javascript:if(this.value==''){this.value='ISD';}"
				 size="5" maxlength="4" /> -
			<html:text property="stdCode" styleId="std" styleClass ="input_field" value="STD"
				onfocus="javascript:if(this.value=='STD'){this.value=''};"
				onblur="javascript:if(this.value==''){this.value='STD';}"
				size="5" maxlength="4" />-
			<html:text  property="landlineNo" styleId="landline" styleClass="input_field" value="LANDLINE"
				 onfocus="javascript:if(this.value=='LANDLINE'){this.value=''};"
			 	onblur="javascript:if(this.value==''){this.value='LANDLINE';}"
			  	size="10" maxlength="8" />
			 <div id="landlineno_ErrMsg" style="display: none;color: red">Invaild LandlineNo</div>
		 </td>
	</tr>
	<tr>
		<td class="input_label">Alternate Email:</td>
		<td width="10">
			<html:text property="alternateEmailID" styleClass="input_field" styleId="alternateEmailID" maxlength="50"/>
			<div id="alternateEmailID_ErrMsg" style="display: none;color: red">Invalid EmailID </div>
		</td>
	</tr>	
	<tr>
		<td width="30%" class="input_label">
			Organization Name:
		</td>
		<td colspan="2" width="10">
			<html:text property="affiliateOrgName" styleId="sch_classType" styleClass="input_field"/>
			<div id="sch_classType_ErrMsg" style="display: none;color: red">Organisation Name is Required </div>
		</td>
	</tr>
	<tr>
		<td width="30%" class="input_label">
			URL:
		</td>
		<td colspan="2">
			<html:text property="affiliateeUrl" styleId="sch_classType" styleClass="input_field" maxlength="255" size="60"/>
			<div id="sch_classType_ErrMsg" style="display: none;color: red">URL is Required </div>
		</td>
	</tr>
	<tr>
		<td width="30%" class="input_label">
			Type:
		</td>
		<td colspan="2" width="10">
			<html:select property="affiliateType" styleId="sch_classType" styleClass="input_field">
				<html:option value="I">InBound</html:option>
				<html:option value="O">OutBound</html:option>
			</html:select>
		</td>
	</tr>
	<tr>
		<td width="30%" class="input_label">
			Contact Person:
		</td>
		<td colspan="2" width="10">
			<html:text property="affContactPerson" styleId="sch_classType" styleClass="input_field"/>
		</td>
	</tr>	
	<tr>
		<td width="30%" class="input_label">
			Account Details:
		</td>
		<td colspan="2" width="10">
			<html:text property="affAcctDtls" styleId="sch_classType" styleClass="input_field"/>			
		</td>
	</tr>
	<tr>
		<td width="30%" class="input_label">
			Subscription Type:
		</td>
		<td colspan="2" width="10">
			<html:select property="affSubsType" styleId="sch_classType" styleClass="input_field">
				<html:option value="R">Click Revenue</html:option>
				<html:option value="S">Click And Sale Revenue</html:option>
				<html:option value="Y">Yearly</html:option>
			</html:select>
		</td>
	</tr>
	<tr>
		<td width="30%" class="input_label">
			Base Subscription Fee:
		</td>
		<td colspan="2" width="10">
			<html:text property="affBaseFee" styleId="sch_classType" styleClass="input_field"/>
		</td>
	</tr>
		<tr>
		<td colspan="4" align="center">
		<html:submit styleId="addorupdate" property="action" value="Add" styleClass="url" onclick="return validateAffiliateFullReg()"/>
		 <html:submit styleId="close" property="action" value="Close" styleClass="url"/>	
		</td>
	</tr>
	<tr>
		<td>
		</td>
	</tr>	
	</html:form>	
</table>


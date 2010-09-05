<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
	 <%@page import="com.education.Session.UserSessionInfo"%>
<%@page import="com.education.Session.SessionConstants"%>
	  
<%@page import="com.education.util.EducationConstant"%>
<tr>
        <td align="left" valign="top">&nbsp;</td>
      </tr>
      <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg"><a href="#" class="url" onclick="login()">My Login</a></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
      
      <%
		UserSessionInfo objUserinfo = (UserSessionInfo)( session.getAttribute(SessionConstants.user_info) == null ?
									null : session.getAttribute(SessionConstants.user_info) );
      	if( objUserinfo != null){%>
     		
			<tr>
				<td width="192" height="22" align="left" valign="middle"
					class="leftnavbg">		
				<html:link forward="myPageLink" styleClass="url">My Page</html:link>
 				</td>
			</tr>
			<tr>
        		<td height="2" align="left" valign="middle" ></td>
      		</tr>
     		 		
     <%}%>
     <%if(objUserinfo != null && (objUserinfo.getRoleId().equals(EducationConstant.TEACHER_USER_ROLE) == false)){%>
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg">
        	<html:link forward="takeSampleTestLink" styleClass="url" >Take a sample test</html:link>
        </td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg"><html:link forward="courseCatalogue" styleClass="url" >Course catalogue</html:link></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg"><html:link forward="find_schools" styleClass="url" >Find school</html:link></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
      <%} else if(objUserinfo == null) {%>
       <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg">
        	<html:link forward="takeSampleTestLink" styleClass="url" >Take a sample test</html:link>
        </td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg"><html:link forward="courseCatalogue" styleClass="url" >Course catalogue</html:link></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg"><html:link forward="find_schools" styleClass="url" >Find school</html:link></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
      <%} %>
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg"><a href="#" class="url">News letter</a></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
      <tr>
        <td height="46" align="center" valign="middle" >&nbsp;<img src="<html:rewrite page='/images/icons.jpg'/>" alt="" width="172" height="26" border="0" usemap="#Map" /></td>
      </tr>
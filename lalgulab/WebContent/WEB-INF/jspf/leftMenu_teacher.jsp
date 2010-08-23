<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@page import="com.education.Session.UserSessionInfo"%>
<%@page import="com.education.Session.SessionConstants"%>
<%
		UserSessionInfo objUserinfo = (UserSessionInfo)( session.getAttribute(SessionConstants.user_info) == null ?
									null : session.getAttribute(SessionConstants.user_info) );
      	if( objUserinfo != null){%>
     		<tr>
				<td height="2" align="left" valign="middle"></td>
			</tr>
			<tr>
				<td width="192" height="22" align="left" valign="middle"
					class="leftnavbg">		
				<html:link forward="myPageLink" styleClass="url">My Page</html:link>
 				</td>
			</tr>
     		 		
 <%}%>

<tr>
	<td height="2" align="left" valign="middle"></td>
</tr>
<tr>
	<td width="192" height="22" align="left" valign="middle"
		class="leftnavbg">		
		<html:link forward="manageQuestionLink" styleClass="url">Manage Question</html:link>
 	</td>
</tr>
<tr>
	<td height="2" align="left" valign="middle"></td>
</tr>
<tr>
	<td width="192" height="22" align="left" valign="middle"
		class="leftnavbg"><a href="#" class="url">Course catalogue</a></td>
</tr>
<tr>
	<td height="2" align="left" valign="middle"></td>
</tr>
<tr>
	<td width="192" height="22" align="left" valign="middle"
		class="leftnavbg"><a href="#" class="url">Find school</a></td>
</tr>
<tr>
	<td height="2" align="left" valign="middle"></td>
</tr>
<tr>
	<td width="192" height="22" align="left" valign="middle"
		class="leftnavbg"><a href="#" class="url">News letter</a></td>
</tr>
<tr>
	<td height="2" align="left" valign="middle"></td>
</tr>
<tr>
	<td height="46" align="center" valign="middle">&nbsp;<img
		src="<html:rewrite page='/images/icons.jpg'/>" alt="" width="172"
		height="26" border="0" usemap="#Map" /></td>
</tr>
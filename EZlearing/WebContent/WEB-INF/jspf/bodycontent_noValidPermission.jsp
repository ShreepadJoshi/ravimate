<%@page import="com.education.Session.UserSessionInfo"%>
<%@page import="com.education.Session.SessionConstants"%>
<%@page import="com.education.util.EducationConstant"%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="10" bgcolor="#F0F0FF">
	<tr>
		<td width="108" align="left" valign="top">
			You are not Authorized to access this page.
			Please try to Login with correct role. 
		</td>		
	</tr>
</table>
<%
UserSessionInfo  objUserinfo = 
	(UserSessionInfo)( session.getAttribute(SessionConstants.user_info) == null ?
							null : session.getAttribute(SessionConstants.user_info) );
objUserinfo.setRoleId(EducationConstant.GUEST_USER_ROLE);%>
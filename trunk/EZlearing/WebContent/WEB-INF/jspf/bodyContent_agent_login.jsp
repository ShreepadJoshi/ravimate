<%@page import="com.education.Session.UserSessionInfo"%>
<%@page import="com.education.Session.SessionConstants"%>
<%@page import="com.education.util.EducationConstant"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%
	UserSessionInfo objUserinfo = (UserSessionInfo)( session.getAttribute(SessionConstants.user_info) == null ?
										null : session.getAttribute(SessionConstants.user_info) );
if(objUserinfo == null || !objUserinfo.getRoleId().equals(EducationConstant.AGENT_USER_ROLE) ){ %>
	Agent's Text Here<br/>
	<bean:define id="roleType"  property="agentRole" value="<%= EducationConstant.AGENT_USER_ROLE %>" /> 
	<html:link forward="registrationLink" paramId="registrationFor" property="agentRole" paramName="roleType">Register As Agent</html:link>	
<%}else {%>
	Agent Default screen

<%}%>

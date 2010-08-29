<%@page import="com.education.Session.UserSessionInfo"%>
<%@page import="com.education.Session.SessionConstants"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@page import="com.education.util.EducationConstant"%>

<%@page import="com.education.Session.SessionConstants"%>
	   <%
		UserSessionInfo  objUserinfo = 
			(UserSessionInfo)( session.getAttribute(SessionConstants.user_info) == null ?
									null : session.getAttribute(SessionConstants.user_info) );
	   if( objUserinfo != null && objUserinfo.getUserloginName()  != null){%>
	      	<table><tr><td colspan="15" width="10">
				<span style="text-align:center;"><font size="4" color="red" style="font-family:Times New Roman"><b><i>Welcome
	      		<%=objUserinfo.getUserloginName()%></i></b></font></span></td>
	      	</tr></table>
	      	
  		<%} %>   
  		<% if( objUserinfo != null && objUserinfo.getUserloginName()  != null){%> 
      	<html:link styleClass="nav" forward="home">Home</html:link>
		|
		<html:link styleClass="nav" action="/showAboutUsStaticContent">About Us</html:link>
		|
		<html:link styleClass="nav" action="/affiliate">Affiliates</html:link>
		|
		<html:link styleClass="nav" action="/teacher">Teacher</html:link>
		|
		<html:link styleClass="nav" action="/agent">Agents</html:link>
		|
		<html:link styleClass="nav" action="/reviewer">Reviewer</html:link>
		|
		<html:link styleClass="nav" action="/showFaqStaticContent">FAQ</html:link>
		|
		<html:link styleClass="nav" action="/showContactUsStaticContent">Contact Us</html:link>	
		|
		<html:link action="/signOut" >SignOut</html:link>
      	<% } else if(objUserinfo == null) { %>
		<html:link styleClass="nav" forward="home">Home</html:link>
		|
		<html:link styleClass="nav" action="/showAboutUsStaticContent">About Us</html:link>
		|
		<html:link styleClass="nav" action="/affiliate">Affiliates</html:link>
		|
		<html:link styleClass="nav" action="/teacher">Teacher</html:link>
		|
		<html:link styleClass="nav" action="/agent">Agents</html:link>
		|
		<html:link styleClass="nav" action="/reviewer">Reviewer</html:link>
		|
		<html:link styleClass="nav" action="/showFaqStaticContent">FAQ</html:link>
		|
		<html:link styleClass="nav" action="/showContactUsStaticContent">Contact Us</html:link>
		<%} %>


<%@page import="com.education.Session.UserSessionInfo"%>
<%@page import="com.education.Session.SessionConstants"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@page import="com.education.util.EducationConstant"%>

<%@page import="com.education.Session.SessionConstants"%>
	   <%
		UserSessionInfo  objUserinfo = 
			(UserSessionInfo)( session.getAttribute(SessionConstants.user_info) == null ?
									null : session.getAttribute(SessionConstants.user_info) );
	   if( objUserinfo != null){%>
	      	<table align="center"> <tr ><td colspan="4" align="right">
				<span style="text-align:center;"><font size="4" color="red" style="font-family:Times New Roman"><b><i>Welcome
	      		<%=objUserinfo.getUserloginName()%></i></b></font></span></td>
	      	</tr></table>
	      	
  		<%} %>    
      	<html:link styleClass="nav" action="/signOut">Home</html:link>
		|
		<html:link styleClass="nav" forward="aboutus">About Us</html:link>
		|
		<html:link styleClass="nav" action="/affiliate">Affiliates</html:link>
		|
		<html:link styleClass="nav" action="/teacher">Teacher</html:link>
		|
		<html:link styleClass="nav" action="/agent">Agents</html:link>
		|
		<html:link styleClass="nav" action="/reviewer">Reviewer</html:link>
		|
		<html:link styleClass="nav" forward="faq">FAQ</html:link>
		|
		<html:link styleClass="nav" forward="contactus">Contact Us</html:link>	
		|
		<html:link action="/signOut" >SignOut</html:link>
      	<% if(objUserinfo == null) { %>
		<html:link styleClass="nav" action="/signOut">Home</html:link>
		|
		<html:link styleClass="nav" forward="aboutus">About Us</html:link>
		|
		<html:link styleClass="nav" action="/affiliate">Affiliates</html:link>
		|
		<html:link styleClass="nav" action="/teacher">Teacher</html:link>
		|
		<html:link styleClass="nav" action="/agent">Agents</html:link>
		|
		<html:link styleClass="nav" action="/reviewer">Reviewer</html:link>
		|
		<html:link styleClass="nav" forward="faq">FAQ</html:link>
		|
		<html:link styleClass="nav" forward="contactus">Contact Us</html:link>
		<%} %>


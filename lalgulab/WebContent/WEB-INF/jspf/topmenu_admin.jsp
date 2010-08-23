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
      		<span class="input_field"><font size="2" color="red" style="font-family:Arial"><b><i>Welcome
      		<%
      			out.print(objUserinfo.getUserloginName());
      		}%></i></b></font></span>&nbsp;
      
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
      	<% if(objUserinfo == null) { 
	System.out.println("----33333333333333333333----------------"+objUserinfo);%>
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


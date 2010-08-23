<%@page import="com.education.Session.UserSessionInfo"%>
<%@page import="com.education.Session.SessionConstants"%>
<%@page import="com.education.util.EducationConstant"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%
UserSessionInfo  objUserinfo = 
	(UserSessionInfo)( session.getAttribute(SessionConstants.user_info) == null ?
							null : session.getAttribute(SessionConstants.user_info) );
	if((objUserinfo != null) && (!objUserinfo.getFirstName().equals(""))){
		%>	
		<table align="center" > <tr ><td colspan="1" align="center">
			<span style="text-align:center;"><font size="4" color="red" style="font-family:Times New Roman"><b><i>Welcome
      		<%=objUserinfo.getUserloginName()%></i></b></font></span></td>
      	</tr></table>
      	&nbsp;<br>
		<html:link styleClass="nav" forward="home">Home</html:link>
		|
		<html:link styleClass="nav" forward="aboutus">About Us</html:link>
		|
		<html:link styleClass="nav" forward="contactus">Contact Us</html:link>
		|
		<html:link action="/signOut" >SignOut</html:link>
	<% } else if(objUserinfo == null) {
		%>

		<html:link styleClass="nav" forward="home">Home</html:link>
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
	
<%} else if((objUserinfo != null) && (!objUserinfo.getFirstName().equals("")) && (objUserinfo.getRoleId().equals(EducationConstant.ADMIN_USER_ROLE)) || (objUserinfo.getRoleId().equals(EducationConstant.GUEST_USER_ROLE))) {%>
	<table align="center" > <tr ><td colspan="1" align="center">
			<span style="text-align:center;"><font size="4" color="red" style="font-family:Times New Roman"><b><i>Welcome
      		<%=objUserinfo.getUserloginName()%></i></b></font></span></td>
      	</tr></table>
      	&nbsp;<br>
     <%if(objUserinfo.getRoleId().equals(EducationConstant.ADMIN_USER_ROLE)) {%>
	<html:link styleClass="nav" action="/signOut">Home</html:link>
	|
	<%} else if(objUserinfo.getRoleId().equals(EducationConstant.GUEST_USER_ROLE)) { %>
	<html:link styleClass="nav" forward="home">Home</html:link>
	|
	<%}%>
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
<%} else {%>
	<html:link styleClass="nav" forward="home">Home</html:link>
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
<%}%>




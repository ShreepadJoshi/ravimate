<%@page import="com.education.util.EducationConstant"%>
<%@page import="com.education.Session.SessionConstants" %>
<%@page import="com.education.Session.UserSessionInfo"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
          
<html> 
<body><form id="userhomePg_ID">         
<%
	UserSessionInfo objUserinfo = (UserSessionInfo)( session.getAttribute(SessionConstants.user_info) == null ?
									null : session.getAttribute(SessionConstants.user_info) );

	if( objUserinfo != null && objUserinfo.getRoleId().equals(EducationConstant.TEACHER_USER_ROLE)){%>		
		<input type="hidden" value="<html:rewrite page='/teacher.do'/>" id="dd_teacher" />
		<script type="text/javascript">
			document.getElementById("userhomePg_ID").action =document.getElementById("dd_teacher").value;
			document.getElementById("userhomePg_ID").submit();
		</script>
				
	<%}else if( objUserinfo != null && objUserinfo.getRoleId().equals(EducationConstant.AGENT_USER_ROLE)){ %>	
		<input type="hidden" value="<html:rewrite page='/agent.do'/>" id="dd_agent" />
		<script type="text/javascript">
			document.getElementById("userhomePg_ID").action =document.getElementById("dd_agent").value;
			document.getElementById("userhomePg_ID").submit();
		</script>
		
		<!-- <input type="hidden" value="<html:rewrite page='/pages/agent.jsp'/>" id="dd_agent" />		
		<script type="text/javascript">window.location=document.getElementById('dd_agent').value;</script> -->
				
	<%}else if( objUserinfo != null && objUserinfo.getRoleId().equals(EducationConstant.AFFILIATE_USER_ROLE)){ %>		
		<input type="hidden" value="<html:rewrite page='/affiliate.do'/>" id="dd_affiliate" />
		<script type="text/javascript">
			document.getElementById("userhomePg_ID").action =document.getElementById("dd_affiliate").value;
			document.getElementById("userhomePg_ID").submit();
		</script>
		
		<!-- <input type="hidden" value="<html:rewrite page='/pages/affiliates.jsp'/>" id="dd_affiliates" />
		<script type="text/javascript">window.location=document.getElementById('dd_affiliates').value;</script> -->
		
	<%}else if( objUserinfo != null && objUserinfo.getRoleId().equals(EducationConstant.GUEST_USER_ROLE)){
%>
		<input type="hidden" value="<html:rewrite page='/guest.do'/>" id="dd_guest" />
		<script type="text/javascript">
			document.getElementById("userhomePg_ID").action =document.getElementById("dd_guest").value;
			document.getElementById("userhomePg_ID").submit();
		</script>
		<!-- <input type="hidden" value="<html:rewrite page='/pages/guest.jsp'/>" id="dd_guest" />
		<script type="text/javascript">window.location=document.getElementById('dd_guest').value;</script> -->
		
	<%}else if( objUserinfo != null && objUserinfo.getRoleId().equals(EducationConstant.REVIEWER_USER_ROLE)){ %>
		<input type="hidden" value="<html:rewrite page='/reviewer.do'/>" id="dd_reviewer" />
		<script type="text/javascript">
			document.getElementById("userhomePg_ID").action =document.getElementById("dd_reviewer").value;
			document.getElementById("userhomePg_ID").submit();
		</script>
		
	<%}else if( objUserinfo != null && objUserinfo.getRoleId().equals(EducationConstant.ADMIN_USER_ROLE)){ %>
		<input type="hidden" value="<html:rewrite page='/admin.do'/>" id="dd_admin" />
			<script type="text/javascript">
				document.getElementById("userhomePg_ID").action =document.getElementById("dd_admin").value;
				document.getElementById("userhomePg_ID").submit();
			</script>
	<%}%>
</form></body>
</html>
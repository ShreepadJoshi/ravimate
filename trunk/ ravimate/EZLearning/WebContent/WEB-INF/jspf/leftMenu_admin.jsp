<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@page import="com.education.Session.UserSessionInfo"%>
<%@page import="com.education.Session.SessionConstants"%>
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
	   <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg">
        	<html:link styleClass="url" forward="adminQuestionList">Question List</html:link> </td>
      </tr>      
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>      
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg">
        	<html:link styleClass="url" forward="manageTopicsPage">Manage Topics</html:link> </td>
      </tr>      
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr> 
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg">
        	<html:link styleClass="url" forward="contentUploadPage">Content Upload</html:link> </td>
      </tr>      
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>      
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg">
        	<html:link styleClass="url" forward="newUserAgentList">AgentList</html:link></td>
      </tr>      	
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg">        	
        	<html:link styleClass="url" forward="newUserTeacherList">TeacherList</html:link></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
	  <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg">
        	<html:link styleClass="url" forward="newUserAffiliateList">AffiliateList</html:link></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
      <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg">
        	<html:link styleClass="url" forward="newUserReviewerList">ReviewerList</html:link></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
      <tr>
        <td width="192" height="22" align="left" valign="middle" class="leftnavbg">
        	<html:link styleClass="url" forward="schoolContentUploadPage">School list upload</html:link></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>
      <tr>
        <td height="2" align="left" valign="middle" ></td>
      </tr>  
      <tr>
        <td height="46" align="center" valign="middle" >&nbsp;<img src="<html:rewrite page='/images/icons.jpg'/>" alt="" width="172" height="26" border="0" usemap="#Map" /></td>
      </tr>
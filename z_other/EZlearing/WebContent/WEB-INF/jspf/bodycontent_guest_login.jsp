<%@page import="com.education.Session.SessionConstants" %>
<%@page import="com.education.Session.UserSessionInfo"%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%
UserSessionInfo objUserinfo = (UserSessionInfo)( session.getAttribute(SessionConstants.user_info) == null ?
		null : session.getAttribute(SessionConstants.user_info) ); 
if(objUserinfo != null){
	if(objUserinfo.getLoginType().equalsIgnoreCase("signup")) {%>
	<tr>
<td width="515"  height="20" align="center">
	 <font = "100%" color="red"><b>User Registered successfully.</b></font>   
</td>
</tr>
<br>
	<br>	
<tr height="120">
  <td width="530" height="115" align="center" valign="middle"
		class="servoverviewbg">
<font size="3" style="font-family:sans-serif" color="#2A466D"><b>You have not taken any test... Would you like to take test , then...</b></font>&nbsp;
 <br><br><html:link forward="takeSampleTestLink" styleClass="url" >Take a sample test</html:link>
     </td></tr>
<tr>
<%}}%>
			
		</tr>


		<tr>
                      <td class="text" ><table width="514" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td width="147" height="70" align="left" valign="top" class="servbg"><div style="padding:3px;"><span class="blue_bold">Study Material</span><br />
        Provide well Certified study material<br />
        <span class="text"><strong><a href="#" onclick="loadStudyMaterial('studyMaterial.jsp','Study Material')" class="url">more...</a></strong></span></div></td>
                                      <td width="36" height="70" align="left" valign="top">&nbsp;</td>
                                      <td width="147" height="70" align="left" valign="top" class="servbg1"><div style="padding:3px;"><span class="blue_bold"> Mind Mapping </span><br />
        <span>Provide well Certified study material<br />
        <strong><a href="#" onclick="loadStudyMaterial('studyMaterial.jsp','Study Material')" class="url">more...</a></strong></span></div></td>
                                      <td width="37" height="70" align="left" valign="top">&nbsp;</td>
                                      <td width="147" height="70" align="left" valign="top" class="servbg2"><div style="padding:3px;"><span class="blue_bold">Finding Schools</span><br />
        <span>Provide well Certified study material<br />
        <strong><a href="#" onclick="loadStudyMaterial('studyMaterial.jsp','Study Material')" class="url">more...</a></strong></span></div></td>
                                    </tr>
                        <tr>
                          <td height="10" align="left" valign="top">&nbsp;</td>
                                      <td height="10" align="left" valign="top">&nbsp;</td>
                                      <td height="10" align="left" valign="top">&nbsp;</td>
                              <td height="10" align="left" valign="top">&nbsp;</td>
                                      <td align="left" valign="top">&nbsp;</td>
                                    </tr>
                        <tr>
                          <td height="70" align="left" valign="top" class="servbg3"><div style="padding:3px;"><span class="blue_bold"> Personnal Assessment</span><br />
        <span>Provide well Certified study material<br />
        <strong><a href="#" onclick="loadStudyMaterial('studyMaterial.jsp','Study Material')" class="url">more...</a></strong></span></div></td>
                          <td height="10" align="left" valign="top">&nbsp;</td>
                          <td height="10" align="left" valign="top" class="servbg4"><div style="padding:3px;"><span class="blue_bold">Parent Section</span><br />
        <span>Provide well Certified study material<br />
        <strong><a href="#" onclick="loadStudyMaterial('studyMaterial.jsp','Study Material')" class="url">more...</a></strong></span></div></td>
                          <td height="10" align="left" valign="top">&nbsp;</td>
                          <td align="left" valign="top" class="servbg5"><div style="padding:3px;"><span class="blue_bold">Virtual Classroom</span><br />
        <span>Provide well Certified study material<br />
        <strong><a href="#" onclick="loadStudyMaterial('studyMaterial.jsp','Study Material')" class="url">more...</a></strong></span></div></td>
                        </tr>
                        </table></td>
							    </tr>

		
		
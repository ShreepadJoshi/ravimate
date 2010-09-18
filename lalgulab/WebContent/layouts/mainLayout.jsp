<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<title><tiles:getAsString name="title" /></title>
<tiles:insert attribute="includes" />
</head>
<body>

<!-- GLOBAL CONSTANTS USED IN JAVASCRIPT START HERE -->
<input type="hidden" value="" id="DOMAIN_NAME" />
<!--<input type="hidden" value="<html:rewrite page='/'/>" id="DOMAIN_NAME" /> -->

<script type="text/javascript">
	var DOMAIN_NAME = document.getElementById('DOMAIN_NAME').value;
</script>
<!-- GLOBAL CONSTANTS USED IN JAVASCRIPT ENDS HERE -->
<%
    //GLOBAL CONSTANTS USED IN APP
	application.setAttribute("DOMAIN_NAME", request.getContextPath()+"/");
%>

<table width="990" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td  width="201" height="110" align="left" valign="middle">
		<img
			src="<html:rewrite page='/images/e_logo1.jpg'/>" alt=""
			width="201" height="104" /></td>
		<td width="798" align="right" valign="bottom">
		
		<!-- TOP NAVIGATION MENU STARTS HERE -->
			<div id="nav" class="nav">
				<tiles:insert attribute="topMenu" />
			</div>
		<!-- TOP NAVIGATION MENU ENDS HERE --></td>
		
	</tr>
	<tr>
		<td width="192" height="3" align="left" valign="top" bgcolor="#22a7d3"></td>
		<td width="798" height="3" align="left" valign="top" bgcolor="#22a7d3"></td>
	</tr>
	<tr>
		<td width="192" height="5" align="left" valign="top"></td>
		<td width="798" height="5"></td>
	</tr>
	<tr>
		<td width="192" height="2" align="left" valign="top">
		
			<!-- TOP LEFT MENU STARTS HERE -->
		<table width="192" border="0" cellspacing="0" cellpadding="0">
			<tiles:insert attribute="leftMenu" />
		</table>
			<!-- TOP LEFT MENU ENDS HERE -->
		</td>
		
		<td align="left" valign="top" style="padding-bottom: 5px;">
		
		<!-- PAGE HEADER IMAGE STRATS HERE -->
			<tiles:insert attribute="header" />
		 <!-- PAGE HEADER IMAGE ENDS HERE  -->
		 
		 
		 </td>
	</tr>
	<tr>
		<td width="192" height="3" align="left" valign="top" bgcolor="#e0e0e0"></td>
		<td height="3" bgcolor="#e0e0e0"></td>
	</tr>
	<tr>
	
		<!-- LEFT NAVIGATION PANEL STARTS HERE -->
		<td height="1" align="left" valign="top">
			<tiles:insert attribute="leftNavigation" />
		</td>
		<!-- LEFT NAVIGATION PANEL ENDS HERE -->
		
		<td align="left" valign="top">
		
		<table width="794" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="536" align="left" valign="top">
				<table width="525" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="30" align="left" valign="middle" class="heading"><img
							src="<html:rewrite page='/images/arrow.jpg'/>" width="11"
							height="11" hspace="15" border="0" />
							<!-- BODY CONTENT HEADER STARTS HERE -->
								<tiles:getAsString name="bodyContentHeading" />
							<!-- BODY CONTENT HEADER ENDS HERE -->
						</td>
					</tr>
					<tr>
						<td height="2" align="left" valign="middle"
							background="<html:rewrite page='/images/dotted_line.jpg'/>"></td>
					</tr>
					
						<!-- BODY CONTENT EXTRA INFO STRATS HERE -->
							<tiles:insert attribute="bodyContentExtraInfo" />
						<!-- BODY CONTENT EXTRA INFO ENDS HERE -->
					<tr>
						<td align="center" valign="top"
							background="<html:rewrite page='/images/middle_table_bg.jpg'/>">
						
							<!--BODY CONTENT STARTS HERE -->
							<div class="text" style="padding: 6px;">
								<tiles:insert attribute="bodyContent"/>
							</div>
							<!-- BODY CONTENT ENDS HERES -->
							
						</td>					
					</tr>
				</table>
				</td>
				<td width="258" align="left" valign="top">
				
				
					<!-- RIGHT NAVIGATION PANEL STARTS HERE -->
						<tiles:insert attribute="rightNavigation" />
					<!-- RIGHT NAVIGATION PANEL ENDS HERE -->
					
					
				</td>
			</tr>
		</table>		
		</td>
	</tr>
	<tr>
		<td height="5" align="left" valign="top"></td>
		<td height="5"></td>
	</tr>
	<tr>
		<!--  FOOTER STRATS HERE -->
			<tiles:insert attribute="footer" />
		<!-- FOOTER ENDS HERE -->
	</tr>
</table>

<map name="Map" id="Map">
	<area shape="rect" coords="6,4,26,24" href="#" />
	<area shape="rect" coords="33,6,53,23" href="#" />
	<area shape="rect" coords="62,5,82,22" href="#" />
	<area shape="rect" coords="92,5,106,21" href="#" />
	<area shape="rect" coords="116,4,139,21" href="#" />
	<area shape="rect" coords="147,5,164,20" href="#" />
</map>
</body>
</html>
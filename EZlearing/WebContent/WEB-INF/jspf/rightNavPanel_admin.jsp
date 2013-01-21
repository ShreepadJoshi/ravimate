<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<table width="258" border="0" cellspacing="0" cellpadding="">
	<tr>
		<td height="30" align="left" valign="middle" class="heading"><img
			src="<html:rewrite page='/images/arrow.jpg'/>" width="11" height="11"
			hspace="15" border="0" />See our Demo</td>
	</tr>
	<tr>
		<td height="" align="left" valign="middle"
			background="<html:rewrite page='/images/dotted_line.jpg'/>"></td>
	</tr>
      <tr>
		<td>
		<object id="MediaPlayer" width="280" height="200" classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701"
		standby="Loading Windows Media Player components..." type="application/x-oleobject">
		<param name="FileName" value="images/GlobeatVaticanCity.wmv">
		<param name="ShowControls" value="true">
		<param name="ShowStatusBar" value="false">
		<param name="ShowDisplay" value="false">
		<param name="autostart" value="false">
		<param name="Volume" value="-300">
		<embed type="application/x-mplayer2" pluginspage="http://www.microsoft.com/Windows/MediaPlayer/" src="images/GlobeatVaticanCity.wmv" name="MediaPlayer"
		width="280" height="200" showcontrols="1" showstatusbar="0" showdisplay="0" autostart="0" volume="-300"> </embed>
		</object>
	</td>
	</tr>    	
    
	
	<tr>
		<td align="left" valign="top"><!-- Testimonails Starts here -->
		<table width="258" border="0" cellspacing="0" cellpadding="2"
			class="text">
			<tr>
				<td width="194" height="31" align="left" valign="middle"
					class="heading"><img
					src="<html:rewrite page='/images/arrow.jpg'/>" width="11"
					height="11" hspace="15" border="0" />Testimonial</td>
			</tr>
			<tr>
				<td width="194" height="" align="left" valign="top"
					background="<html:rewrite page='/images/dotted_line.jpg'/>"></td>
			</tr>
			<tr>
				<td align="left" valign="top" style="padding-left: 10px;"><span
					class="blue_bold">S. N. Gupta from Delhi</span><br />
				<br />
				This is the first page of your Web site that visitors will see.<br />
				<a href="#" class="url">read more</a><br />
				<br />
				</td>
			</tr>
			<tr>
				<td height="0" align="left" valign="top"
					background="<html:rewrite page='/images/dotted_line.jpg'/>"></td>
			</tr>
		</table>
		<!-- Testimonails Ends here --></td>
	</tr>
</table>
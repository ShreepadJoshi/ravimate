<%@page import="com.education.Session.UserSessionInfo"%>
<%@page import="com.education.Session.SessionConstants"%>
<%@page import="com.education.util.EducationConstant"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>    
<%@page import="java.util.List"%>
<%@page import="com.education.transferobj.TSchool"%>
<%int a =0;

%>
<display:table name="findSchool.pgSearchResults" id="row" requestURI="/findSchool.do" pagesize="10" decorator="org.displaytag.decorator.TotalTableDecorator">
<%a = ((TSchool)row).getSchoolId(); %>
	<display:column property="schoolName" decorator="com.education.displaytag.SchoolLinkWrapper" title="School Name" style="padding-left:2px;"/>
	<display:column property="schoolPostalAddr" title="Postal Addr" style="padding-left:2px;"/>
	<display:column property="schoolDistrict" title="City" style="padding-left:2px;"/>
	<display:column property="schoolPinCode" title="Pin" style="padding-left:2px;"/>
	<display:column property="schoolWebsite" title="WebSite" style="padding-left:2px;"/>
	<display:column property="schoolBoard" title="Board" style="padding-left:2px;"/>	
	<display:column property="schoolCategory" title="Category" style="padding-left:2px;"/>
	<display:column property="schoolType" title="Category" style="padding-left:2px;"/>
	<display:column property="principalName" title="Contact Person" style="padding-left:2px;"/>
	<display:column property="schoolPhoneNo" title="Contact Number" style="padding-left:2px;"/>
	<display:setProperty name="paging.banner.placement" value="bottom" />
</display:table>

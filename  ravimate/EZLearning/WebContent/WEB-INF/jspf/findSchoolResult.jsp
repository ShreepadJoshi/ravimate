<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>    
<%@page import="java.util.List"%>
<%@page import="com.education.transferobj.TSchool"%>
<%int a =0;

%>
<display:table name="findSchool.pgSearchResults" id="row" requestURI="/findSchool.do" pagesize="10" decorator="org.displaytag.decorator.TotalTableDecorator">
<%a = ((TSchool)row).getSchoolId(); %>
	<display:column property="schoolName" title="School Name"  href="javascript:display_schoolDetails(2)"/> 
	<display:column property="schoolBoard" title="Board" />
	<display:column property="schoolId" title="ID" />
	<display:column property="schoolDistrict" title="District"/>
	<display:column property="schoolState" title="State"/>
	<display:column property="schoolPostalAddr" title="Postal Addr"/>
	<display:column property="schoolPinCode" title="Pin"/>
	<display:column property="yearoffoundation" title="Year Of Foundation"/>
	<display:column property="schoolStatus" title="Status"/>
	<display:column property="mediumofInstruction" title="Medium Of Instruction"/>
	<display:setProperty name="paging.banner.placement" value="bottom"/>
</display:table>

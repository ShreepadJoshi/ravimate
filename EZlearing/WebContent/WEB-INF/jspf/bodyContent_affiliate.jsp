<%@page import="com.education.util.EducationConstant"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<bean:define id="roleType"  property="affiliateRole" value="<%= EducationConstant.AFFILIATE_USER_ROLE %>" /> 
<html:link forward="affiliatePage" paramId="registrationFor" property="affiliateRole" paramName="roleType">Register As Affiliate</html:link>
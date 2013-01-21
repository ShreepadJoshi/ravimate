
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>



<html:form method="post" action="/login.do">
<html:hidden property="username" />
<html:hidden property="password" />
<html:hidden property="password1" />
<html:hidden property="password2" />
<html:submit styleId="submit" ></html:submit>
</html:form>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table>
	<tr style="display: none;">
		<td>First column</td>
	</tr>
	<tr>
		<td>second column</td>
	</tr>
</table>
	
<%

//response.sendRedirect(getUpdatedURl());
		String uri = request.getScheme() + "://" +   // "http" + "://
        request.getServerName() +       // "myhost"
        ":" +                           // ":"
        request.getLocalPort() +       // "8080"
        request.getRequestURI() +       // "/people"
        "?" +                           // "?"
        request.getQueryString();       // "lastname=Fox&age=30"

        
        out.print(uri);
%>

<%!
    String getUpdatedURl()
    {
      return "http://localhost:8081/WebTest/index.jsp";
    }
%>	
	
	
</body>
</html>

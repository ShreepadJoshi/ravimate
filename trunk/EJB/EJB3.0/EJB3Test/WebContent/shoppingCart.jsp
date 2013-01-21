
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<h1 align="center">Statelfull Bean Exapmle EJB 3.0</h1>

<form name="shoppingForm" action="<%=request.getContextPath()%>/ShoppingCartServlet" method="post">
<h2 align="center">Catalog</h2>

<h3 align="center" font="bold" color="red">
<%
	if( (request.getAttribute("message"))!=null){
		out.println(request.getAttribute("message"));
	}
%>
</h3>
<table align=center border=1 cellspacing=0 cellpadding=10 width=100
	height=0 bgcolor=#faebd7>
	<tr>
		<td><input type="radio" name="book" value="Java" size=20></td>
		<td>Java</td>

	</tr>
	<tr>
		<td><input type="radio" name="book" value="Erlang" size=20></td>
		<td>Erlang</td>

	</tr>
	<tr>
		<td><input type="radio" name="book" value="Scala" size=20></td>
		<td>Scala</td>

	</tr>
	
	
</table>

<br><br>

<table align=center border=1 cellspacing=0 cellpadding=10 width=100
	height=0 bgcolor=#faebd7>
	<tr>
		<td>Add Book/s</td>
		<td><input type="radio" name="action" value="add"></td>
		
	</tr>
	
	<tr>
		<td>Remove Book/s</td>
		<td><input type="radio" name="action" value="remove"></td>
		
	</tr>
	<tr>
		<td>Show Me the Cart Items: </td>
		<td><input type="radio" name="action" value="show"></td>
	</tr>
	<tr>
		<td>Exit..</td>
		<td><input type="radio" name="action" value="exit"></td>
	</tr>
	<tr ><td colspan="2"><input type=submit name="Update Cart" ></td>
	</tr>
</table>

</form>

</html>
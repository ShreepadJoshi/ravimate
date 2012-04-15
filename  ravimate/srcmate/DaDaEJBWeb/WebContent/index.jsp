<html>
<form name="calculatorForm"
	action="<%=request.getContextPath()%>/CalculatorServlet" method="post">
	
<h1 align="center"> Stateless Bean Exapmle EJB 3.0 </h1>	
<table align=center border=1 cellspacing=0 cellpadding=10 width=100
	height=0 bgcolor=#faebd7>
	<tr>
		<td>First Operand</td>
		<td><input type=text name="first" size=20></td>
	</tr>
	<tr>
		<td>Second Operand</td>
		<td><input type=text name="second" size=30></td>
	</tr>
	<tr>
		<td><input type="radio" name="operation" value="add"> </td>
		<td>Addition</td>
	</tr>
	<tr>
		<td><input type="radio" name="operation" value="sub"> </td>
		<td>Substraction</td>
	</tr>
		
	
	
</table>
<br>
<br>
<center><input type=submit name=submit value=submit>
</form>
</html>
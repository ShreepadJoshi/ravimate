<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
     
 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="10" bgcolor="#F0F0FF">
  		<tr><td width="25%" class = "input_label" >You are Paying To: </td>
            <td class = "heading" >EZ-learning.com</td>
		</tr>
		<tr><td class = "input_label" >Amount (INR): </td>
			<td colspan="1" >
				<label><input name="Amount" type="text"  class = "input_field" maxlength="5" size="5" disabled="disabled"/></label>
			</td>
		</tr>
		<tr><td  class = "input_label" >Card Type:</td>
			<td>
			   	<select name="CardType">
			    	<option value="MasterCard" class="text">Master Card</option>
			    	<option value="Visa" class="text">Visa</option>
			        <option value="AmericanExpress" class="text">American Express</option>
				</select>
			</td>
			<td><img src="<html:rewrite page='/images/credit_card_logos.jpg'/>" alt="Credit Card" width="148" height="32" /></td>
		</tr>
		<tr>
			<td  class = "input_label" >Card Holder Name:</td>
            <td colspan="3" ><label><input name="CardHolder" type="text"  class = "input_field" maxlength="30" size="30" /></label></td>
		</tr>
		<tr>
			<td class = "input_label" >Card Number:</td>
            <td colspan="1" ><label><input name="CardNumber" type="text"  class = "input_field" maxlength="20" size="20" /></label></td>
		</tr>
		<tr><td class = "input_label" >CCV Number:</td>
			<td colspan="1" ><label><input name="CCV" type="number"  class = "input_field" maxlength="3" size="3" /></label></td>
			<td colspan="3">  <p>These are last 3 digits of the code printed on the reverse side of your card </p></td>
		</tr>
		<tr><td  class = "input_label" >Expiry Date:</td>
			<td>
			   <select name="ExpiryMonth" type="month">
			   		<option value="Jan" class="text">January</option>
			        <option value="Feb" class="text">February</option>
			        <option value="Mar" class="text">March</option>
			        <option value="Apr"class="text">April</option>  
			    </select>
			</td>
		    <td>
			    <select name="ExpiryYear">
			         <option value="2010" class="text">2010</option>
			         <option value="2011" class="text">2011</option>
			         <option value="2012" class="text">2012</option>
			         <option value="2013"class="text">2013</option>
			    </select>
		    </td>
		</tr>
		<tr>
			<td colspan="4" align="center"><label><input name="MakePayment" type="submit"  class="url"  value="Make Payment" />
                                                  <input name="Cancel" type="submit"  class="url"  value="Cancel" /></label>
			</td>
		 </tr>
	</table>



package com.education.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.TableDecorator;

import com.education.formbeans.AdminQuestionListActionForm;
import com.education.transferobj.QuestionBankTO;
import com.education.transferobj.RegistrationTo;

public class LinkQclassTableDecorator extends TableDecorator{

		
	public String getComplexity() {
		return "<select name='complexityList' size='1'>"+
			"<option label='Simple' value='1'/>"+
				"<option label='Medium' value='2'/>"+
				"<option label='Complex' value='3'/>"+
				"<option label='Very Complex' value='4'/>"+
				"</select>";
	}

	public String getCert() {
		return "<select name='classList' size='1'>"+ 
			"<option label='Engineering' value='1'/>"+
  			"<option label='B.S.C' value='2'/>"+
  			"</select>";

	}		
}

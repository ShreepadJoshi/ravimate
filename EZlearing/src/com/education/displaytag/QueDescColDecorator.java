package com.education.displaytag;

import javax.servlet.jsp.PageContext;


import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.education.transferobj.QuestionBankTO;
import com.education.util.EducationConstant;

public class QueDescColDecorator implements DisplaytagColumnDecorator{

	/**
	 * Modifies simple question description to hyper link
	 * 
	 */ 
	public Object decorate(Object object, PageContext cxt, MediaTypeEnum arg2)
			throws DecoratorException {
		// TODO Auto-generated method stub		
		return "<a href='/manageQuestion.do?" +
				"mode="+EducationConstant.PAGE_VIEW_MODE+
				"&"+
				"questionId="+ ((QuestionBankTO)cxt.getAttribute("row")).getQuestionId()+"'>"+
				object +
				"</a>";
	}
}

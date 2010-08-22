package com.education.displaytag;

import org.displaytag.decorator.ColumnDecorator;
import org.displaytag.exception.DecoratorException;

import com.education.util.Utilities;

public class DateColumnDecorator implements ColumnDecorator {

	public String decorate(Object dateObj) throws DecoratorException {
		
		return Utilities.getDate_displayFormat(dateObj);
	}	
}

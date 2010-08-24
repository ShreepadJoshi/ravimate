package com.education.displaytag;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

public class SchoolLinkWrapper implements DisplaytagColumnDecorator
{

	@Override
	public Object decorate(Object obj, PageContext page, MediaTypeEnum media)
			throws DecoratorException {
		
		StringBuffer buffer = new StringBuffer();		
		buffer.append("<a href='javascript:display_schoolDetails()'>");		
		buffer.append(obj + "</a>");		
		return buffer.toString();
	}

}

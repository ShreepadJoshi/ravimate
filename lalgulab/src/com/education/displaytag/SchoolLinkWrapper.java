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
		int count = -1;
		if(page.getRequest().getAttribute("schoolListCount")!=null){
			count = (Integer)page.getRequest().getAttribute("schoolListCount");			
			page.getRequest().setAttribute("schoolListCount",count);
		}
		count++;
		page.getRequest().setAttribute("schoolListCount",count);
		
		
		StringBuffer buffer = new StringBuffer();		
		buffer.append("<a href='javascript:display_schoolDetails(" + count + ")'>");		
		buffer.append(obj + "</a>");		
		return buffer.toString();
	}

}

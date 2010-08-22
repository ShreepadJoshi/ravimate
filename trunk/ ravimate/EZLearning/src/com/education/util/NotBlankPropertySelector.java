package com.education.util;

import org.hibernate.criterion.Example.PropertySelector;
import org.hibernate.type.Type;

public class NotBlankPropertySelector implements PropertySelector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean include(Object obj, String propertyName, Type type) {
		// TODO Auto-generated method stub
		
		return obj != null && !((obj instanceof String && ((String)obj).equals("")) || (obj instanceof Number && ((Number)obj).longValue() == 0));
	}

}

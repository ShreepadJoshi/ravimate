package com.education.util;

import org.springframework.context.ApplicationContext;

public class SpringContextAware {

	private static ApplicationContext  _ctx;
	public static void setApplicationContext(ApplicationContext ctx)
	{
		_ctx = ctx;
	}
	
	public static ApplicationContext getApplicationContext()
	{
		return _ctx;
	}
}

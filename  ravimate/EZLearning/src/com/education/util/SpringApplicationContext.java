package com.education.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext appCtx)
			throws BeansException {
		// TODO Auto-generated method stub
		SpringContextAware.setApplicationContext(appCtx);
	}

}

package com.shashajing.benison.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * 
 * @author Administrator
 *
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}


	public synchronized static Object getBean(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		} else if (context == null) {
			return null;
		}
		
		return context.getBean(name);
	}
	
}

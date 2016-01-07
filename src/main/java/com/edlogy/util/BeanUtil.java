package com.edlogy.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanUtil {
	private static BeanUtil instance = null;
	ApplicationContext appContext = null;

	protected BeanUtil() {
		appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	public static BeanUtil getInstance() {
		if(instance == null) {
			instance = new BeanUtil();
		}
		return instance;
	}

	public Object getBean(String beanName){
		return  appContext.getBean(beanName);
	}
}

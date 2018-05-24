package org.rone.study.spring.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/aop-xml.xml");
		UserDao userDao = context.getBean(UserDao.class);
		userDao.show(3);
		((AbstractApplicationContext)context).close();
	}
	
}

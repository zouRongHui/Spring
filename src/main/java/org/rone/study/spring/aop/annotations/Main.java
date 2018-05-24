package org.rone.study.spring.aop.annotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/aop-annotations.xml");
		UserService userService = (UserService) context.getBean("userService");
		userService.add("rone", "000000");
		((AbstractApplicationContext)context).close();
	}
	
}

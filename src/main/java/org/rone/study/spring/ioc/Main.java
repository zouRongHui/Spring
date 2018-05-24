package org.rone.study.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/ioc-xml.xml");
		System.out.println();
		//可通过类名来获取实例，但该类的实例在搞容器中有且只有一个
//		Person person = (Person) context.getBean(Person.class);
//		System.out.println(person);
		
//		Phone phone = (Phone) context.getBean("phone");
//		System.out.println(phone);
		
//		Phone iPhone = (Phone) context.getBean("iPhone");
//		System.out.println(iPhone);
		
//		Phone mix2s = (Phone) context.getBean("mix2-s");
//		System.out.println(mix2s);
		
//		Phone oppo = (Phone) context.getBean("oppo");
//		System.out.println(oppo);
		
//		Phone vivo = (Phone) context.getBean("vivo");
//		System.out.println(vivo);
		
//		Phone nokia = (Phone) context.getBean("nokia");
//		System.out.println(nokia);
//		Phone blackBerry = (Phone) context.getBean("blackBerry");
//		System.out.println(blackBerry);
		
		Phone samsung = (Phone) context.getBean("samsung");
		System.out.println(samsung);
		
		
		
		
		
		((AbstractApplicationContext)context).close();
	}

}

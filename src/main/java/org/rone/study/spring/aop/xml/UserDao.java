package org.rone.study.spring.aop.xml;

public class UserDao {
	
	public String show(Integer type) {
		System.out.println("UserDao show..");
		if (type == 1) {
			return "first";
		} else {
			return "others";
		}
	}

}

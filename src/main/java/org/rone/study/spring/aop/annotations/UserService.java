package org.rone.study.spring.aop.annotations;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public Boolean add(String name, String password) {
		System.out.println("UserService add..");
//		return true;
		throw new NullPointerException("NPE");
	}
	
}

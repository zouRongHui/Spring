package org.rone.study.spring.ioc;

public class Factory {

	public static Phone getStaticInstance(String name) {
		return new Phone(name, "phone", Math.random()*500 + 3000);
	}
	
	public Phone getInstance(String name) {
		return new Phone(name, "phone", Math.random()*500 + 10000);
	}
	
}

package org.rone.study.spring.ioc;

import org.springframework.beans.factory.FactoryBean;

public class PhoneFactoryBean implements FactoryBean<Phone> {
	
	private String name;

	@Override
	public Phone getObject() throws Exception {
		return new Phone(name, "phone", 999.00);
	}

	@Override
	public Class<?> getObjectType() {
		return Phone.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

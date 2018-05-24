package org.rone.study.spring.ioc;

import java.util.List;
import java.util.Map;

public class Person {
	
	private String name;
	private Integer age;
	private Phone phone;
	private List<String> lovers;
	private Map<String, String> experience;
	
	public Person() {
		System.out.println("Person default constructor.");
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	public void init() {
		System.out.println("Person init..");
	}
	
	public void destory() {
		System.out.println("Person destory..");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public List<String> getLovers() {
		return lovers;
	}

	public void setLovers(List<String> lovers) {
		this.lovers = lovers;
	}

	public Map<String, String> getExperience() {
		return experience;
	}

	public void setExperience(Map<String, String> experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", phone=" + phone + ", lovers=" + lovers + ", experience="
				+ experience + "]";
	}

}

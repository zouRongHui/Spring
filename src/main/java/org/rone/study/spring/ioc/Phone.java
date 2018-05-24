package org.rone.study.spring.ioc;

public class Phone {
	
	private String name;
	private String brand;
	private Double price;
	
	public Phone() {
		System.out.println("Phone default constructor.");
	}
	
	public Phone(String name, String brand, Double price) {
		super();
		System.out.println("Phone custom constructor.");
		this.name = name;
		this.brand = brand;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Phone [name=" + name + ", brand=" + brand + ", price=" + price + "]";
	}

}

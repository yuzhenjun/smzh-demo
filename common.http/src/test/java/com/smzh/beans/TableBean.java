package com.smzh.beans;

public class TableBean {
	@Override
	public String toString() {
		return "TableBean [id=" + id + ", name=" + name + ", price=" + price
				+ "]";
	}
	private int id;
	private String name;
	private String price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}

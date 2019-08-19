package com.itheima;

public class Product {
	private String name;
	private String stock;
	private String price;
	
	
	public Product() {
	}

	public Product(String name, String stock, String price) {
		this.name = name;
		this.stock = stock;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}

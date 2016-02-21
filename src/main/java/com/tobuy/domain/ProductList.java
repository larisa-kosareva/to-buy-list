package com.tobuy.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
	private long productListId;
	private String date;
	private List<Product> products = new ArrayList<>();
	private boolean done = false;
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ProductList() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public ProductList(String date) {
		this.date = date;
	}
	
	public void addProduct(Product product) {
		products.add(product);
		product.setProductList(this);
	}

	public long getProductListId() {
		return productListId;
	}

	public void setProductListId(long productListId) {
		this.productListId = productListId;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
}

package com.example.demo.entity;

public class Category {
	private Integer id;
	private String categoryName;
	
	public Category(){
		
	}
	public Category(Integer id, String categoryName) {
		this.id = id;
		this.categoryName = categoryName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}

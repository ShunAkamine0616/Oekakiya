package com.example.demo.controller.form;

import javax.validation.constraints.NotBlank;

public class EditForm{
	
	@NotBlank(message="画像のタイトルは必須です。  ")
	private String title;
	
	private Integer categoryId;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	private String comment;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
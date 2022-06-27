package com.example.demo.controller.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

public class EditForm{
	
	@Max(value=100, message="タイトルは100文字以内です。  ")
	@NotBlank(message="画像のタイトルは必須です。  ")
	private String title;
	
	private Integer categoryId;
	
	@Max(value=2000, message="コメントは2000文字以内です。  ")
	private String comment;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

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
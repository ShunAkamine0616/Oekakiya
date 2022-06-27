package com.example.demo.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class EditForm{
	@NotBlank(message="画像のタイトルは必須です。  ")
	@Length(max=100, message="タイトルは100文字以内です。  ")
	private String title;
	
	private Integer categoryId;
	@Length(max=2000, message="コメントは2000文字以内です。  ")
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
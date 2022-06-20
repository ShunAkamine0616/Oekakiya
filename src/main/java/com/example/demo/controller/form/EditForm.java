package com.example.demo.controller.form;

import javax.validation.constraints.NotBlank;

public class EditForm{
	@NotBlank(message="画像のタイトルは必須です。  ")
	private String title;
	private Integer categoryid;
	private String comment;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
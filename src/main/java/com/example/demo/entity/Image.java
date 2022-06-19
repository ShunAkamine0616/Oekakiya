package com.example.demo.entity;

import java.sql.Timestamp;

public class Image {
	private Integer id;
	private String imageTitle;
	private String imagePath;
	private String comment;
	private Integer categoryId;
	private Integer userId;
	private Timestamp created_at;
	private Timestamp updated_at;

	public Image() {

	}

	public Image(String imageTitle, String imagePath, String comment, 
			Integer categoryId, Integer userId) {
		this.imageTitle = imageTitle;
		this.imagePath = imagePath;
		this.comment = comment;
		this.categoryId = categoryId;
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}

	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
}

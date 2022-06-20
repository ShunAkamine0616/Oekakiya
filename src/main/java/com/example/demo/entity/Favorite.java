package com.example.demo.entity;

import java.sql.Timestamp;

public class Favorite{
	private Integer id;
	private Integer userId;
	private Integer imageId;
	private Image image;
	
	public Favorite() {
		
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getImageId() {
		return imageId;
	}
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	
	public Integer getImagesId() {
		return image.getId();
	}
	public void setImagesId(Integer id) {
		if(this.image == null) {
			this.image = new Image();
		}
		image.setId(id);
	}
	public String getImageTitle() {
		return image.getImageTitle();
	}
	public void setImageTitle(String title) {
		if(this.image == null) {
			this.image = new Image();
		}
		image.setImageTitle(title);
	}
	public String getImagePath() {
		return image.getImagePath();
	}
	public void setImagePath(String path) {
		if(this.image == null) {
			this.image = new Image();
		}
		image.setImagePath(path);
	}
	public Integer getImageUserId() {
		return image.getUserId();
	}
	public void setImageUserId(Integer userid) {
		if(this.image == null) {
			this.image = new Image();
		}
		image.setUserId(userid);
	}
	public Integer getCreatedAt() {
		return image.getCreatedAt;
	}
	public void setCreatedAt(Timestamp create) {
		if(this.image == null) {
			this.image = new Image();
		}
		image.setCreatedAt(create);
	}
	public Integer getUpdatedAt() {
		return image.getUpdatedAt;
	}
	public void setUpdatedAt(Timestamp update) {
		if(this.image == null) {
			this.image = new Image();
		}
		image.setUpdatedAt(update);
	}

}
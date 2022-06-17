package com.example.demo.entity;

import java.sql.Timestamp;

public class Favorite{
	private Integer id;
	private Integer userId;
	private Integer imageId;
	private Images images;
	
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
		return images.getId;
	}
	public void setImagesId(Integer id) {
		if(this.images == null) {
			this.images = new Images();
		}
		images.setId(id);
	}
	public Integer getImageTitle() {
		return images.getImageTitle;
	}
	public void setImageTitle(String title) {
		if(this.images == null) {
			this.images = new Images();
		}
		images.ImageTitle(title);
	}
	public Integer getImagePath() {
		return images.getImagePath;
	}
	public void setImagePath(String path) {
		if(this.images == null) {
			this.images = new Images();
		}
		images.setImagePath(path);
	}
	public Integer getImageUserId() {
		return images.getUserId;
	}
	public void setImageUserId(Integer userid) {
		if(this.images == null) {
			this.images = new Images();
		}
		images.setUserId(userid);
	}
	public Integer getCreatedAt() {
		return images.getCreatedAt;
	}
	public void setCreatedAt(Timestamp create) {
		if(this.images == null) {
			this.images = new Images();
		}
		images.setCreatedAt(create);
	}
	public Integer getUpdatedAt() {
		return images.getUpdatedAt;
	}
	public void setUpdatedAt(Timestamp update) {
		if(this.images == null) {
			this.images = new Images();
		}
		images.setUpdatedAt(update);
	}

}
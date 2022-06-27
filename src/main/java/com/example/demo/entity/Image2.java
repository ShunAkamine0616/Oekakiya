package com.example.demo.entity;

import java.sql.Timestamp;

public class Image2 {
	private Integer id;
	private String imageTitle;
	private String base64;
	private String extention;
	private String comment;
	private Integer categoryId;
	private Integer userId;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Integer favorite;
	private Integer download;

	private Integer favoriteFlag;

    private User user;

	

	public Image2() {

	}

	public Image2(String imageTitle, String base64, String extention, String comment, 
			Integer categoryId, Integer userId) {
		this.imageTitle = imageTitle;
		this.base64 = base64;
		this.extention = extention;
		this.comment = comment;
		this.categoryId = categoryId;
		this.userId = userId;
	}
	

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
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

	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Integer getFavorite() {
		return favorite;
	}

	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}

	public Integer getDownload() {
		return download;
	}

	public void setDownload(Integer download) {
		this.download = download;
	}

	public Integer getFavoriteFlag() {
		return favoriteFlag;
	}

	public void setFavoriteFlag(Integer favoriteFlag) {
		this.favoriteFlag = favoriteFlag;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;

	}
}

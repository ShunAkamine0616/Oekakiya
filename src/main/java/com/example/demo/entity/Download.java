package com.example.demo.entity;

public class Download {
	private Integer id;
	private Integer userId;
	private Integer imageId;



	public Download() {

	}

	public Download(Integer id, Integer userId, Integer imageId) {
		super();
		this.id = id;
		this.userId = userId;
		this.imageId = imageId;
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
}

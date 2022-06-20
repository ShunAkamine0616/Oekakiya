package com.example.demo.entity;

public class Follow {
	private Integer id;
	private Integer userId;
	private Integer followUserId;
	private Integer followCount;
	
	
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
	public Integer getFollowUserId() {
		return followUserId;
	}
	public void setFollowUserId(Integer followUserId) {
		this.followUserId = followUserId;
	}
	public Integer getFollowCount() {
		return followCount;
	}
	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}
}
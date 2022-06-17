package com.example.demo.dao;

import java.util.List;

public interface FollowDao {

	public List<User> findByUserId(Integer userId);
	
	public int countFollow(Integer userFollowId);

	public int insert(Integer userId, Integer followUserId);
	
	public int delete(Integer userId, Integer followUserId);

	public int deleteByUserId(Integer userId);
}
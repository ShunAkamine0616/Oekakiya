package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface FollowService {

	public List<User> findByUserId(Integer userId);
	
	public int countFollow(Integer userFollowId);

	public int insert(Integer userId, Integer followUserId);
	
	public int delete(Integer userId, Integer followUserId);

	public int deleteByUserId(Integer userId);
}
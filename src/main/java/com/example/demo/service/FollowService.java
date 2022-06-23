package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Follow;

public interface FollowService {

	public List<Follow> findByUserId(Integer userId);
	
	public Follow countFollow(Integer userFollowId);
	
	public Follow checkFollow(Integer userId, Integer otherId);

	public int insert(Integer userId, Integer followUserId);
	
	public int delete(Integer userId, Integer followUserId);

	public int deleteByUserId(Integer userId);
}
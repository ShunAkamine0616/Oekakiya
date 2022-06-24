package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Follow;

public interface FollowDao {

	public List<Follow> findByUserId(Integer userId);
	
	public Integer countFollow(Integer userFollowId);
	
	public Follow checkFollow(Integer userId, Integer otherId);

	public int insert(Integer userId, Integer followUserId);
	
	public int delete(Integer userId, Integer followUserId);

	public int deleteByUserId(Integer userId);
}
package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Image2;

public interface FavoriteDao {

	public int countFavorite(Integer imageId);
	public int insert(Integer userId,Integer imageId);
	public int delete(Integer userId,Integer imageId);
	public int deleteUser(Integer userId);
	public List<Image2> findByUserId(Integer userId);
	public Image2 findByUserIdAndImageId(Integer userId,Integer imageId);
}

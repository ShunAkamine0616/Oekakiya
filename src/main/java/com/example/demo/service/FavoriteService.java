package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Image;

public interface FavoriteService {
	public int countFavorite(Integer imageId);
	public int insert(Integer userId,Integer imageId);
	public int delete(Integer userId,Integer imageId);
	public int deleteUser(Integer userId);
	public List<Image> findByUserId(Integer userId);
	public Image findByUserIdAndImageId(Integer userId,Integer imageId);
}

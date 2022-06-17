package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FavoriteDao;
import com.example.demo.dao.impl.Images;
import com.example.demo.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService{
	@Autowired
	private FavoriteDao favoritedao;
	
	public int countFavorite(Integer imageId) {
		return favoritedao.countFavorite(imageId);
	}
	public int insert(Integer userId,Integer imageId) {
		return favoritedao.insert(userId, imageId);
	}
	public int delete(Integer userId,Integer imageId) {
		return favoritedao.delete(userId, imageId);
	}
	public int deleteUser(Integer userId) {
		return favoritedao.deleteUser(userId);
	}
	public List<Images> findByUserId(Integer userId){
		return favoritedao.findByUserId(userId);
	}
}
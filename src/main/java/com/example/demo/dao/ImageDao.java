package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.Image;

public interface ImageDao {
	public List<Image> findByKeyword(String keyword, String categoryId, String sort);
	public List<Image> findFollow(String keyword, String categoryId, String sort, Integer userId);
	public List<Image> findByUserId(Integer userId);
	public Image findByImageId(Integer imageId);
	public int insert(Image image);
	public int update(Image image);
	public int delete(Integer id);
	public int deleteByUserId(Integer userId);
}

package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Image2;

public interface Image2Service {
	public List<Image2> findByKeyword(String keyword, String categoryId, String sort, Integer userId);
	public List<Image2> findFollow(String keyword, String categoryId, String sort, Integer userId);
	public List<Image2> findByUserId(Integer userId);
	public Image2 findByImageId(Integer imageId);
	public Image2 findByImageBase64(String base64);
	public int insert(Image2 image);
	public int update(Image2 image);
	public int delete(Integer id);
	public int deleteByUserId(Integer userId);
	public Image2 findByIdCount(Integer imageId);
}

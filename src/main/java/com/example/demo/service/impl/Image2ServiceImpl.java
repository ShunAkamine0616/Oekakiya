package com.example.demo.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.Image2Dao;
import com.example.demo.entity.Image2;
import com.example.demo.service.Image2Service;

@Service
public class Image2ServiceImpl implements Image2Service {
	@Autowired
	private Image2Dao Image2Dao;
	
	public List<Image2> findByKeyword(String keyword, String categoryId, String sort, Integer userId) {
		return Image2Dao.findByKeyword(keyword, categoryId, sort, userId);
	}
	public List<Image2> findFollow(String keyword, String categoryId, String sort, Integer userId) {
		return Image2Dao.findFollow(keyword, categoryId, sort, userId);
	}
	public List<Image2> findByUserId(Integer userId) {
		return Image2Dao.findByUserId(userId);
	}
	public Image2 findByImageId(Integer imageId) {
		return Image2Dao.findByImageId(imageId);
	}
	public Image2 findByImageBase64(String base64) {
		return Image2Dao.findByImageBase64(base64);
	}
	public int insert(Image2 image) {
		return Image2Dao.insert(image);
	}
	public int update(Image2 image) {
		return Image2Dao.update(image);
	}
	public int delete(Integer id) {
		return Image2Dao.delete(id);
	}
	public int deleteByUserId(Integer userId) {
		return Image2Dao.deleteByUserId(userId);
	}
	public Image2 findByIdCount(Integer imageId){
		return Image2Dao.findByIdCount(imageId);
	}
}

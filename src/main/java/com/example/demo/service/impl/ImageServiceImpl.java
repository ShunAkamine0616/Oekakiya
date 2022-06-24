package com.example.demo.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ImageDao;
import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageDao imageDao;
	
	public List<Image> findByKeyword(String keyword, String categoryId, String sort, Integer userId) {
		return imageDao.findByKeyword(keyword, categoryId, sort, userId);
	}
	public List<Image> findFollow(String keyword, String categoryId, String sort, Integer userId) {
		return imageDao.findFollow(keyword, categoryId, sort, userId);
	}
	public List<Image> findByUserId(Integer userId) {
		return imageDao.findByUserId(userId);
	}
	public Image findByImageId(Integer imageId) {
		return imageDao.findByImageId(imageId);
	}
	public Image findByImagePath(String imagePath) {
		return imageDao.findByImagePath(imagePath);
	}
	public int insert(Image image) {
		return imageDao.insert(image);
	}
	public int update(Image image) {
		return imageDao.update(image);
	}
	public int delete(Integer id) {
		return imageDao.delete(id);
	}
	public int deleteByUserId(Integer userId) {
		return imageDao.deleteByUserId(userId);
	}
	public Image findByIdCount(Integer imageId){
		return imageDao.findByIdCount(imageId);
	}
}

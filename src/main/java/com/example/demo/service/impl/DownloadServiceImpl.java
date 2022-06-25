package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DownloadDao;
import com.example.demo.entity.Image2;
import com.example.demo.service.DownloadService;

@Service
public class DownloadServiceImpl implements DownloadService{
	@Autowired
	private DownloadDao downloadDao;

	public List<Image2> findByUserId(Integer userId){
		return downloadDao.findByUserId(userId);

	}
	public int countDownload(Integer imageId) {
		return downloadDao.countDownload(imageId);
	}
	public int insert(Integer userId, Integer imageId) {
		return downloadDao.insert(userId, imageId);
	}
	public List<Image2> usersJoinImages(Integer imageId) {
		return downloadDao.usersJoinImages(imageId);
	}
	@Override
	public List<Image2> findByUserIdList(Integer userId) {
		return downloadDao.findByUserIdList(userId);
	}
}

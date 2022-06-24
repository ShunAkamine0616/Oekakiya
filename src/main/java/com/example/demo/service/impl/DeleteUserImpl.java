package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.FavoriteDao;
import com.example.demo.dao.FollowDao;
import com.example.demo.dao.ImageDao;
import com.example.demo.dao.UserDao;
import com.example.demo.service.DeleteUserService;
@Service
public class DeleteUserImpl implements DeleteUserService {
	@Autowired
    private UserDao userDao;
	@Autowired
    private ImageDao imageDao;
	@Autowired
    private FollowDao followDao;
	@Autowired
    private FavoriteDao favoriteDao;
	
	@Transactional
	public void delete(Integer userId) {
		//ユーザー情報削除
		userDao.delete(userId);
		//そのユーザーの投稿した画像を削除
		imageDao.deleteByUserId(userId);
		//そのユーザーのフォローしたという情報を削除
		followDao.deleteByUserId(userId);
		//そのユーザーのいいねしたという情報を削除
		favoriteDao.deleteUser(userId);
	}
}

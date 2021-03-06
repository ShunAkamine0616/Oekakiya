package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FollowDao;
import com.example.demo.entity.Follow;
import com.example.demo.entity.User;
import com.example.demo.service.FollowService;
@Service
public class FollowServiceImpl implements FollowService {
	
	@Autowired
    private FollowDao followDao;
	
	public List<Follow> findByUserId(Integer userId){
		return followDao.findByUserId(userId);
	}
	
	public List<User> findByUserIdFollow(Integer userId){
		return followDao.findByUserIdFollow(userId);
	}
	
	public Integer countFollow(Integer userFollowId) {
		return followDao.countFollow(userFollowId);
	}

	public int insert(Integer userId, Integer followUserId) {
		return followDao.insert(userId, followUserId);
	}
	
	public int delete(Integer userId, Integer followUserId) {
		return followDao.delete(userId, followUserId);
	}

	public int deleteByUserId(Integer userId) {
		return followDao.deleteByUserId(userId);
	}

	@Override
	public Follow checkFollow(Integer userId, Integer otherId) {
		// TODO 自動生成されたメソッド・スタブ
		return followDao.checkFollow(userId, otherId);
	}
}

package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findByKeyword(String keyword) {
		return userDao.findByKeyword(keyword);
	}

	@Override
	public List<User> findFollow(String keyword, Integer userId) {
		return userDao.findFollow(keyword, userId);
	}

	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public int update(User user) {
		return userDao.update(user);
	}

	@Override
	public int roleAssignment(User user, boolean role) {
		return userDao.roleAssignment(user, role);
	}

	@Override
	public int delete(Integer id) {
		return userDao.delete(id);
	}

}

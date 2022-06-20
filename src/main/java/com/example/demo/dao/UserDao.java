package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.User;

public interface UserDao {
	public List<User> findByKeyword(String keyword);
	public List<User> findFollow(String keyword, Integer userId);
	public User findById(String keyword);
	public int insert(User user);
	public int update(User user);
	public int delete(Integer id);
}

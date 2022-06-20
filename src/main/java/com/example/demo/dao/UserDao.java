package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.User;

public interface UserDao {
	public List<User> findByKeyword(String keyword);
	public List<User> findFollow(String keyword, Integer userId);
	public User findById(Integer id);
	public int insert(User user);
	public int update(User user);
	public int roleAssignment(User user, boolean role);
	public int delete(Integer id);
}
package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {
	
	//ユーザー検索　nullで全検索
	public List<User> findByKeyword(String keyword);
	
	//フォローしているユーザーを検索
	public List<User> findFollow(String keyword, Integer userId);
	
	//ユーザーをIDで検索
	public User findById(Integer id);
	
	//ユーザーの新規登録
	public int insert(User user);
	
	//プロフィール編集　権限は変えられない
	public int update(User user);
	
	//管理者権限の付与
	public int roleAssignment(User user, boolean role);
	
	//ユーザーの削除
	public int delete(Integer id);
}
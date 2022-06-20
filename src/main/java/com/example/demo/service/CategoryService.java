package com.example.demo.service;

import java.util.List;
import java.util.Locale.Category;

public interface CategoryService {
	public int insert(String categoryName);
	public int update(Integer id,String categoryName);
	public int delete(Integer categoryId);
	public List<Category> findAll();
}

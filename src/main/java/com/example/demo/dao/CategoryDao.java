package com.example.demo.dao;
import java.util.List;

import com.example.demo.entity.Category;


public interface CategoryDao {
	public List<Category> findAll();
	public Category findByCategoryId(Integer id);
	public int insert(String categoryName);
	public Integer update(Integer id , String categoryName);
	public Integer delete(Integer categoryId);
}

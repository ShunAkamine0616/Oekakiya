package com.example.demo.dao;
import java.util.List;
import java.util.Locale.Category;

public interface CategoryDao {
	public List<Category> findAll();
	public int insert(String categoryName);
	public Integer update(Integer id , String categoryName);
	public Integer delete(Integer categoryId);
}

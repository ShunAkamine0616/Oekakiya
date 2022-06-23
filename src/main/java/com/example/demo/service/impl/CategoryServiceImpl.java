package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDao categorydao;
	
	public int insert(String categoryName) {
		return categorydao.insert(categoryName);
	}
	public int update(Integer id,String categoryName) {
		return categorydao.update(id,categoryName);
	}
	public int delete(Integer categoryId) {
		return categorydao.delete(categoryId);
	}
	public List<Category> findAll(){
		return categorydao.findAll();
	}
	public Category findByCategoryId(Integer id){
		return categorydao.findByCategoryId(id);
	}
}

package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.Category;
@Repository
public class PgCategoryDao implements CategoryDao {
	private static final String SQL_INSERT_CATEGORY = "insert into categories(category_name) values(:categoryName)";
	private static final String SQL_UPDATE_CATEGORY = "UPDATE categories SET category_name = :categoryName WHERE id = :id";
	private static final String SQL_DELETE_CATEGORY = "DELETE FROM categories WHERE id = :id";
	private static final String SQL_SELSECT_CATEGORYID ="SELECT * FROM categories WHERE id =:id";
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;
	
	public List<Category> findAll() {
        return jdbcTemplate2.query("SELECT * FROM categories",
                new BeanPropertyRowMapper<Category>(Category.class));
	}
	public Category findByCategoryId(Integer id){
		String sql = SQL_SELSECT_CATEGORYID;
		   MapSqlParameterSource param = new MapSqlParameterSource();
	        param.addValue("id", id);
	        
	       List<Category> categoryList =jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Category>(Category.class));
	       return categoryList .isEmpty()? null:categoryList.get(0);
	}
	public int insert(String categoryName){
		String sql = SQL_INSERT_CATEGORY;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("categoryName", categoryName);
        
        return jdbcTemplate.update(sql, param);
	}
	public Integer update(Integer id , String categoryName){
		String sql = SQL_UPDATE_CATEGORY;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("categoryName", categoryName);
        param.addValue("id", id);
        
        return jdbcTemplate.update(sql, param);
	}
	public Integer delete(Integer categoryId) {
		String sql = SQL_DELETE_CATEGORY;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", categoryId);
        
        return jdbcTemplate.update(sql, param);
	}
}

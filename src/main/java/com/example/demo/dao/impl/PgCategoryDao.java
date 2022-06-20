package com.example.demo.dao.impl;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.CategoryDao;
@Repository
public class PgCategoryDao implements CategoryDao {
	private static final String SQL_INSERT_CATEGORY = "insert into categories(category_name) values(:categoryName)";
	private static final String SQL_UPDATE_CATEGORY = "UPDATE categories SET category_name = :category_name WHERE id = :id";
	private static final String SQL_DELETE_CATEGORY = "DELETE FROM categories WHERE id = :id";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;
	
	public List<Category> findAll() {
        return jdbcTemplate2.query("SELECT * FROM categories",
                new BeanPropertyRowMapper<Category>(Category.class));
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
        
        return jdbcTemplate.update(sql, param);
	}
	public Integer delete(Integer categoryId) {
		String sql = SQL_DELETE_CATEGORY;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("categoryId", categoryId);
        
        return jdbcTemplate.update(sql, param);
	}
}

package com.example.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.FavoriteDao;
import com.example.demo.entity.Favorite;

@Repository
public class PgFavoriteDao implements FavoriteDao{
	
    @Autowired
    private NamedParameterJdbcTemplate  jdbcTemplate;
    
    private static final String SELECT_FAVORITE_COUNT = "select count(image_id) from favorite where image_id = :imageId group by image_id;";
    private static final String SQL_INSERT = "insert into favorite(user_id,image_id) values(:userId,:imageId);";
    private static final String SQL_DELETE = "delete from favorite where user_id = :userId and image_id = imageId;";
    private static final String SQL_DELETE_USER = "delete from favorite where user_id = :userId;";
    private static final String SQL_SELECT_IMAGES = "select i.id as images_id,image_title,image_path,i.user_id as image_user_id,created_at,updated_at from images as i inner join favorite as f on i.user_id = f.user_id where f.user_id =:userId;";
    
    public int countFavorite(Integer imageId) {
    	String sql = SELECT_FAVORITE_COUNT;
        MapSqlParameterSource param = new MapSqlParameterSource();
	    param.addValue("imageId", imageId);
	    List<Favorite> list =  jdbcTemplate.query(sql,param, new BeanPropertyRowMapper<Favorite>(Favorite.class));
	    return list.isEmpty() ? null : list.get(0).getImageId();
    }
    
	public int insert(Integer userId,Integer imageId) {
		String sql = SQL_INSERT;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId",userId);
		param.addValue("imageId",imageId);
		return jdbcTemplate.update(sql, param);
	}
	
	public int delete(Integer userId,Integer imageId) {
		String sql = SQL_DELETE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId",userId);
		param.addValue("imageId",imageId);
		return jdbcTemplate.update(sql, param);
	}
	
	public int deleteUser(Integer userId) {
		String sql = SQL_DELETE_USER;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId",userId);
		return jdbcTemplate.update(sql, param);
	}
	
	public List<Images> findByUserId(Integer userId) {
		List<Images> images = new ArrayList<>();
		String sql = SQL_SELECT_IMAGES;
		MapSqlParameterSource param = new MapSqlParameterSource();
	    param.addValue("userId", userId);
	    images =  jdbcTemplate.query(sql,param, new BeanPropertyRowMapper<Images>(Images.class));
	    return images;
	}

}
package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.ImageDao;
import com.example.demo.entity.Image;

@Repository
public class PgImageDao implements ImageDao {

	private static final String SQL_INSERT_IMAGE = "INSERT INTO images(image_title, image_path, comment, category_id, user_id, created_at, updated_at) VALUES(:image_title, :image_path, :comment, :category_id, :user_id, current_timestamp, current_timestamp)";
	private static final String SQL_DELETE_IMAGE = "DELETE FROM images WHERE id = :id";
	private static final String SQL_DELETE_IMAGE_BY_USERID = "DELETE FROM images WHERE user_id = :user_id";
	private static final String SQL_UPDATE_IMAGE = "UPDATE images SET image_title = :image_title,comment = :comment, category_id = :category_id, updated_at = current_timestamp WHERE id = :id;";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;


	public List<Image> findByKeyword(String keyword, String categoryId, String sort) {
		String SQL_SELECT_IMAGE_BY_KEYWORD = "SELECT * FROM images im JOIN categories c ON im.category_id = c.category_id) "
				+ "WHERE image_title LIKE :keyword OR comment LIKE :keyword"
				+ " ORDER BY " + sort;
		String sql = SQL_SELECT_IMAGE_BY_KEYWORD;
		List<Image> resultList = jdbcTemplate2.query(sql, new BeanPropertyRowMapper<Image>(Image.class));

		return resultList.isEmpty() ? null : resultList;
	}
	public List<Image> findFollow(String keyword, String categoryId, String sort, Integer userId) {
		String SQL_SELECT_IMAGE_BY_FOLLOW = "SELECT * FROM images im JOIN categories c ON im.category_id = c.category_id) "
				+ "WHERE image_title LIKE :keyword OR comment LIKE :keyword AND user_id IN "
				+ "(SELECT follow_user_id FROM follow WHERE user_id = "+ userId + ")"
				+ " ORDER BY " + sort;
		String sql = SQL_SELECT_IMAGE_BY_FOLLOW;
		List<Image> resultList = jdbcTemplate2.query(sql, new BeanPropertyRowMapper<Image>(Image.class));
		return resultList.isEmpty() ? null : resultList;
	}
	public List<Image> findByUserId(Integer userId) {
		String SQL_SELECT_IMAGE_BY_USERID = "SELECT * FROM images WHERE user_id = " + userId;
		String sql = SQL_SELECT_IMAGE_BY_USERID;
		List<Image> resultList = jdbcTemplate2.query(sql, new BeanPropertyRowMapper<Image>(Image.class));
		return resultList.isEmpty() ? null : resultList;
	}
	public Image findByImageId(Integer imageId) {
		String SQL_SELECT_IMAGE_BY_IMAGEID = "SELECT * FROM images WHERE user_id = " + imageId;
		String sql = SQL_SELECT_IMAGE_BY_IMAGEID;
		List<Image> resultList = jdbcTemplate2.query(sql, new BeanPropertyRowMapper<Image>(Image.class));
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	public int insert(Image image) {
		String sql = SQL_INSERT_IMAGE;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("image_title", image.getImageTitle());
        param.addValue("image_path", image.getImagePath());
        param.addValue("comment", image.getComment());
        param.addValue("category_id", image.getCategoryId());
        param.addValue("user_id", image.getUserId());
        
        return jdbcTemplate.update(sql, param);
	}
	public int update(Image image) {
		String sql = SQL_UPDATE_IMAGE;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("image_title", image.getImageTitle());
        param.addValue("comment", image.getComment());
        param.addValue("category_id", image.getCategoryId());
        param.addValue("id", image.getId());
        
        return jdbcTemplate.update(sql, param);
	}
	public int delete(Integer id) {
		String sql = SQL_DELETE_IMAGE;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        
        return jdbcTemplate.update(sql, param);
	}
	public int deleteByUserId(Integer userId) {
		String sql = SQL_DELETE_IMAGE_BY_USERID;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("user_id", userId);
        
        return jdbcTemplate.update(sql, param);
	}
}

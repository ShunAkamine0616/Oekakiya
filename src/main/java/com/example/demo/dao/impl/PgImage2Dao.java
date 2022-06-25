package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Image2Dao;
import com.example.demo.entity.Image2;

@Repository
public class PgImage2Dao implements Image2Dao{
	String SQL_SELECT_IMAGE_BY_KEYWORD = "SELECT im.id, im.image_title, im.base64, im.comment, im.category_id, im.user_id, im.created_at, im.updated_at, COALESCE(f.favorite,0) AS favorite, COALESCE(dl.download,0) AS download, CASE WHEN im.id IN (SELECT image_id FROM favorite WHERE user_id = :userId) THEN 1 ELSE 0 END favorite_flag FROM images2 im "
			+ "LEFT JOIN (SELECT image_id, count(*) favorite FROM favorite GROUP BY image_id) f "
			+ "ON im.id = f.image_id "
			+ "LEFT JOIN (SELECT image_id, count(*) download FROM downloads GROUP BY image_id) dl "
			+ "ON im.id = dl.image_id "
			+ "WHERE (im.image_title LIKE :keyword OR im.comment LIKE :keyword) ";

	private static final String SQL_INSERT_IMAGE = "INSERT INTO images2(image_title, base64, comment, category_id, user_id, created_at, updated_at) VALUES(:image_title, :base64, :comment, :category_id, :user_id, current_timestamp, current_timestamp)";
	private static final String SQL_DELETE_IMAGE = "DELETE FROM images2 WHERE id = :id";
	private static final String SQL_DELETE_IMAGE_BY_USERID = "DELETE FROM images2 WHERE user_id = :user_id";
	private static final String SQL_UPDATE_IMAGE = "UPDATE images2 SET image_title = :image_title,comment = :comment, category_id = :category_id, updated_at = current_timestamp WHERE id = :id;";
	private static final String SQL_COUNT_SELECT_ID ="SELECT COALESCE(f.favorite,0) AS favorite, COALESCE(dl.download,0) AS download FROM images2 im LEFT JOIN (SELECT image_id, count(*) favorite FROM favorite GROUP BY image_id) f ON im.id = f.image_id LEFT JOIN (SELECT image_id, count(*) download FROM downloads GROUP BY image_id) dl ON im.id = dl.image_id WHERE (im.id = :id);";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate2;


	public List<Image2> findByKeyword(String keyword, String categoryId, String sort, Integer userId) {
		String sql = SQL_SELECT_IMAGE_BY_KEYWORD;
		MapSqlParameterSource param = new MapSqlParameterSource();
		if(!" ".equals(categoryId)) {
			sql += "AND im.category_id IN ("+categoryId+") ORDER BY "+ sort;
		}else {
			sql += "ORDER BY "+ sort;
		}
		param.addValue("keyword", "%"+keyword+"%");
		param.addValue("userId", userId);
		List<Image2> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Image2>(Image2.class));

		return resultList.isEmpty() ? null : resultList;
	}
	public Image2 findByIdCount(Integer imageId){
		String sql = SQL_COUNT_SELECT_ID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("id", imageId);
		List<Image2> resultList = jdbcTemplate.query(sql,param, new BeanPropertyRowMapper<Image2>(Image2.class));
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	public List<Image2> findFollow(String keyword, String categoryId, String sort, Integer userId) {
		String sql = SQL_SELECT_IMAGE_BY_KEYWORD;
		MapSqlParameterSource param = new MapSqlParameterSource();
		if(!" ".equals(categoryId)) {
			sql += "AND im.category_id IN ("+categoryId+") AND im.user_id IN (select follow_user_id from follow where user_id = :userId) ORDER BY "+ sort;
		}else {
			sql += "AND im.user_id IN (select follow_user_id from follow where user_id = :userId) ORDER BY "+ sort;
		}
		param.addValue("keyword", "%"+keyword+"%");
		param.addValue("userId", userId);
		List<Image2> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Image2>(Image2.class));
		return resultList.isEmpty() ? null : resultList;
	}
	public List<Image2> findByUserId(Integer userId) {
		String SQL_SELECT_IMAGE_BY_USERID = "SELECT * FROM images2 WHERE user_id = " + userId;
		String sql = SQL_SELECT_IMAGE_BY_USERID;
		List<Image2> resultList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Image2>(Image2.class));
		return resultList.isEmpty() ? null : resultList;
	}
	public Image2 findByImageId(Integer imageId) {
		String SQL_SELECT_IMAGE_BY_IMAGEID = "SELECT * FROM images2 WHERE id = " + imageId;
		String sql = SQL_SELECT_IMAGE_BY_IMAGEID;
		List<Image2> resultList = jdbcTemplate2.query(sql, new BeanPropertyRowMapper<Image2>(Image2.class));
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	public Image2 findByImageBase64(String base64) {
		System.out.println("base64 = " + base64);
		String SQL_SELECT_IMAGE_BY_IMAGEPATH = "SELECT * FROM images2 WHERE base64 =:base64";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("base64", base64);
		String sql = SQL_SELECT_IMAGE_BY_IMAGEPATH;
		List<Image2> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Image2>(Image2.class));
		return resultList.isEmpty() ? null : resultList.get(0);
	}
	public int insert(Image2 image2) {
		String sql = SQL_INSERT_IMAGE;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("image_title", image2.getImageTitle());
        param.addValue("base64", image2.getBase64());
        param.addValue("comment", image2.getComment());
        param.addValue("category_id", image2.getCategoryId());
        param.addValue("user_id", image2.getUserId());
        
        return jdbcTemplate.update(sql, param);
	}
	public int update(Image2 image2) {
		String sql = SQL_UPDATE_IMAGE;
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("image_title", image2.getImageTitle());
        param.addValue("comment", image2.getComment());
        param.addValue("category_id", image2.getCategoryId());
        param.addValue("id", image2.getId());
        
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

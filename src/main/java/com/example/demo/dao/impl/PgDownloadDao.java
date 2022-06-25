package com.example.demo.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.DownloadDao;
import com.example.demo.entity.Image2;


@Repository
public class PgDownloadDao implements DownloadDao {
	private static final String SQL_SELECT_DOWNLOADS_WHERE_USERID_LIST ="SELECT * FROM images2 WHERE id IN (SELECT image_id FROM downloads WHERE user_id = :userId)";
	private static final String SQL_SELECT_DOWNLOADS_WHERE_USERID = "SELECT image_id FROM downloads WHERE user_id =:userId";
	private static final String SQL_SELECT_DOWNLOADS_COUNT_IMAGEID ="SELECT COUNT(*) FROM downloads WHERE image_id = :imageId";
	private static final String SQL_INSERT ="INSERT INTO downloads(user_id,image_id) VALUES(:userId,:imageId)";
	private static final String SQL_SELECT_USERS_JOIN_IMAGES_WHERE_IMAGEID ="SELECT * FROM users u JOIN images2 i ON u.id = i.user_id WHERE i.user_id = :imageId";
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;


	//userID指定
	@Override
	public List<Image2> findByUserId(Integer userId) {
		String sql = SQL_SELECT_DOWNLOADS_WHERE_USERID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId",userId);

		List<Image2> imageList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Image2>(Image2.class));
		return imageList.isEmpty() ? null : imageList;

	}
	
	@Override
	public List<Image2> findByUserIdList(Integer userId) {
		String sql = SQL_SELECT_DOWNLOADS_WHERE_USERID_LIST;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId",userId);

		List<Image2> imageList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Image2>(Image2.class));
		return imageList.isEmpty() ? null : imageList;

	}
	//imageID指定
	@Override
	public Integer countDownload(Integer imageId) {
		String sql = SQL_SELECT_DOWNLOADS_COUNT_IMAGEID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("imageId",imageId);
		Integer count = jdbcTemplate.queryForObject(sql, param, Integer.class);
		return count;

	}
	//ユーザーテーブルとイメージテーブルからimageID指定
	@Override
	public List<Image2> usersJoinImages(Integer imageId) {
		String sql = SQL_SELECT_USERS_JOIN_IMAGES_WHERE_IMAGEID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("imageId",imageId);
		List<Image2> UserImageList =jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Image2>(Image2.class));
		return UserImageList.isEmpty()? null : UserImageList;

	}
	public int insert(Integer userId,Integer imageId) {
		String sql = SQL_INSERT;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		param.addValue("imageId", imageId);


		return jdbcTemplate.update(sql, param);

	}

}

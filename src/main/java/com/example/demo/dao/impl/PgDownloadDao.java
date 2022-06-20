package com.example.demo.dao.impl;

<<<<<<< HEAD
public class PgDownloadDao {

}
=======

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.DownloadDao;
import com.example.demo.entity.Image;


@Repository
public class PgDownloadDao implements DownloadDao {
	private static final String SQL_SELECT_DOWNLOADS_WHERE_USERID ="SELECT * FROM downloads WHERE user_id =:userId";
	private static final String SQL_SELECT_DOWNLOADS_COUNT_IMAGEID ="SELECT COUNT(*) FROM downloads WHERE image_id = :imageId";
	private static final String SQL_INSERT ="INSERT INTO downloads(user_id,image_id) VALUES(:userId,:imageId)";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;


	//userID指定
	@Override
	public List<Image> findByUserId(Integer userId) {
		String sql = SQL_SELECT_DOWNLOADS_WHERE_USERID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId",userId);

		List<Image> imageList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Image>(Image.class));
		return imageList.isEmpty() ? null : imageList;

	}
	//userID指定
	@Override
	public int countDownload(Integer imageId) {
		String sql = SQL_SELECT_DOWNLOADS_COUNT_IMAGEID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("imageId",imageId);

		return jdbcTemplate.update(sql, param);

	}
	public int insert(Integer userId,Integer imageId) {
		String sql = SQL_INSERT;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		param.addValue("imageId", imageId);


		return jdbcTemplate.update(sql, param);

	}
}


//**SQLの定数の名前** 
//
//大文字のスネークケースみたいなやつ　例：　SQL_SELECT_ALL
//
//WHEREまではなくてもいい
//
//**共通**
//
//SQL_INSERT
//
//SQL_UPDATE
//
//SQL_SELECT
//
//SQL_DELETE
//
//全部取得するとき
//
//例：SELECT_USERS_ALL
//
//　USERSはテーブル名
//
//ログインは一個しかないから
//
//例　：　ログインするとき→　SQL_LOGIN
>>>>>>> origin/higa

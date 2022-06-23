package com.example.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.FollowDao;
import com.example.demo.entity.Follow;

@Repository
public class PgFollowDao implements FollowDao {
	
	private static final String SQL_SELECT = "SELECT * FROM follow WHERE user_id = :userId";
	private static final String SQL_FOLLOW_COUNT = "SELECT COALESCE(count(*)) AS follow_count FROM follow WHERE follow_user_id = :userId";
	private static final String SQL_CHECK_FOLLOW = "SELECT * FROM follow WHERE user_id = :userId AND follow_user_id = :otherId";
	private static final String SQL_INSERT = "INSERT INTO follow(user_id, follow_user_id) VALUES(:userId, :followUserId)";
	private static final String SQL_DELETE = "DELETE FROM follow WHERE user_id = :userId AND follow_user_id = :followUserId";
	private static final String SQL_DELETE_USERID = "DELETE FROM follow WHERE user_id = :userId";
	
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Follow> findByUserId(Integer userId){
    	String sql = SQL_SELECT;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	ArrayList<Follow> followList = (ArrayList<Follow>) jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Follow>(Follow.class));
    	return followList.isEmpty() ? null : followList;
    }
	
    public Integer countFollow(Integer userFollowId) {
    	String sql = SQL_FOLLOW_COUNT;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userFollowId);
    	Integer count = jdbcTemplate.queryForObject(sql, param, Integer.class);
    	return count;
    };

    public Follow checkFollow(Integer userId, Integer otherId) {
    	String sql = SQL_CHECK_FOLLOW;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("otherId", otherId);
    	List<Follow> followCheck = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Follow>(Follow.class));
    	return followCheck.isEmpty() ? null : followCheck.get(0);
    }
    
	public int insert(Integer userId, Integer followUserId) {
		String sql = SQL_INSERT;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("followUserId", followUserId);
    	int result = 0;
    	try {
    		result = jdbcTemplate.update(sql, param);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return result;
	};
	
	public int delete(Integer userId, Integer followUserId) {
		String sql = SQL_DELETE;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("followUserId", followUserId);
    	int result = 0;
    	try {
    		result = jdbcTemplate.update(sql, param);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return result;
	};

	public int deleteByUserId(Integer userId) {
		String sql = SQL_DELETE_USERID;
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	int result = 0;
    	try {
    		result = jdbcTemplate.update(sql, param);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return result;
	};
	
}

package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

@Repository
public class PgUserDao implements UserDao {
	
	private static final String SQL_SELECT_USER_BY_KEYWORD = "SELECT * FROM users WHERE account_id LIKE :keyword OR name LIKE :keyword";
	private static final String SQL_SELECT_FOLLOW_USER = "SELECT * FROM users WHERE (account_id LIKE :keyword OR name LIKE :keyword) AND id IN (select follow_user_id from follow where user_id = :userId)";
	private static final String SQL_SELECT_USER = "SELECT * FROM users WHERE id = :id";
	private static final String SQL_LOGIN_USER = "SELECT * FROM users WHERE account_id = :account_id AND password = :password";
	private static final String SQL_INSERT_USER = "INSERT INTO users(account_id, password, name, role, icon_path) VALUES(:accountId, :password, :name, 2, :icon_path)";
	private static final String SQL_UPDATE_USER = "UPDATE users SET account_id = :accountId, password = :password, name = :name, "
			+ "icon_path = :iconPath, mail = :mail, introduction = :introduction WHERE id = :id";
	private static final String SQL_ROLE_UPDATE = "UPDATE users SET role = :role WHERE id = :id";
	private static final String SQL_USER_DELETE = "DELETE FROM users WHERE id = :id";
	private static final String SQL_MATCH_ACCOUNTID = "SELECT * FROM users WHERE account_id = :accountId";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	//ユーザー検索
	@Override
	public List<User> findByKeyword(String keyword) {
		String sql = SQL_SELECT_USER_BY_KEYWORD;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("keyword", "%"+keyword+"%");
		
		List<User> userList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return userList.isEmpty() ? null : userList;
	}

	//フォローしているユーザー検索
	@Override
	public List<User> findFollow(String keyword, Integer userId) {
		String sql = SQL_SELECT_FOLLOW_USER;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		param.addValue("keyword", "%"+keyword+"%");
		
		List<User> userList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return userList.isEmpty() ? null : userList;
	}
	
	//1人のユーザー検索
	@Override
	public User findById(Integer id) {
		String sql = SQL_SELECT_USER;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		
		List<User> userList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return userList.isEmpty() ? null : userList.get(0);
	}
	
	//ログイン
	@Override
	public User login(String accountid, String password) {
		String sql = SQL_LOGIN_USER;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("account_id", accountid);
		param.addValue("password", password);
		List<User> userList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return userList.isEmpty() ? null : userList.get(0);
	}
	
	//ユーザー登録
	@Override
	public int insert(User user) {
		String sql = SQL_INSERT_USER;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("accountId", user.getAccountId());
		param.addValue("password", user.getPassword());
		param.addValue("name", user.getName());
		param.addValue("icon_path", user.getIconPath());
		return jdbcTemplate.update(sql, param);
	}
	
	//プロフィール編集　権限は変えられない
	@Override
	public int update(User user) {
		String sql = SQL_UPDATE_USER;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("accountId", user.getAccountId());
		param.addValue("password", user.getPassword());
		param.addValue("name", user.getName());
		//画像パス
		param.addValue("iconPath", user.getIconPath());
		
		param.addValue("mail", user.getMail());
		param.addValue("introduction", user.getIntroduction());
		param.addValue("id", user.getId());
		return jdbcTemplate.update(sql, param);
	}
	
	//管理者権限の編集 trueなら付与、falseなら剥奪
	@Override
	public int roleAssignment(User user, boolean role) {
		String sql = SQL_ROLE_UPDATE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		if(role) {
			param.addValue("role", 1);
		}else{
			param.addValue("role", 2);
		}
		param.addValue("id", user.getId());
		return jdbcTemplate.update(sql, param);
	}
	
	//ユーザーの削除
	@Override
	public int delete(Integer id) {
		String sql = SQL_USER_DELETE;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		
		return jdbcTemplate.update(sql, param);
	}

	@Override
	public User findByAccountId(String accountId) {
		String sql = SQL_MATCH_ACCOUNTID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("accountId", accountId);
		
		List<User> userList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
		return userList.isEmpty() ? null : userList.get(0);
	}

}

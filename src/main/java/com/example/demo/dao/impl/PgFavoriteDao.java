package com.example.demo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.FavoriteDao;

@Repository
public class PgFavoriteDao implements FavoriteDao{
	
    @Autowired
    private NamedParameterJdbcTemplate  jdbcTemplat;
    
    private static final String SELECT_FAVORITE_COUNT = "a";
    
}
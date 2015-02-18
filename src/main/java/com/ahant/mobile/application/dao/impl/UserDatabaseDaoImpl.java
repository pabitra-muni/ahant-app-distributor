package com.ahant.mobile.application.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ahant.mobile.application.dao.ConnectionDao;
import com.ahant.mobile.application.dao.UserDatabaseDao;
import com.ahant.mobile.application.response.bean.Application;

public class UserDatabaseDaoImpl implements ConnectionDao, UserDatabaseDao {

	private JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	public List<Application> getReleaseList(int isIcon) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}

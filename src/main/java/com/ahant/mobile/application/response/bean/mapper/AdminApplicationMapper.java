package com.ahant.mobile.application.response.bean.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ahant.mobile.application.response.bean.Application;

public class AdminApplicationMapper implements RowMapper<Application> {

	public Application mapRow(ResultSet rs, int rowNum) throws SQLException {

		return new Application(rs.getString("app_name"), rs.getInt("id"),
				rs.getInt("platform"),
				rs.getString("description"),null);
	}

}

package com.ahant.mobile.application.response.bean.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ahant.mobile.application.response.bean.Platform;

public class PlatformMapper implements RowMapper<Platform> {

	public Platform mapRow(ResultSet rs, int rowNum) throws SQLException {

		Platform platform = new Platform();
		platform.setId(rs.getInt("id"));
		platform.setPlatformName(rs.getString("platform_name"));
		platform.setExecutableType(rs.getString("executable_type"));

		return platform;
	}

}

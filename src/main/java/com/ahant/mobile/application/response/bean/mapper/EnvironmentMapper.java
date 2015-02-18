package com.ahant.mobile.application.response.bean.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ahant.mobile.application.response.bean.ReleaseEnvironment;

public class EnvironmentMapper implements RowMapper<ReleaseEnvironment> {

	public ReleaseEnvironment mapRow(ResultSet rs, int rowNum)
			throws SQLException {

		ReleaseEnvironment env = new ReleaseEnvironment();
		env.setId(rs.getInt("id"));
		env.setEnvName(rs.getString("env_name"));
		env.setDescription(rs.getString("description"));

		return env;
	}

}

package com.ahant.mobile.application.response.bean.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ahant.mobile.application.response.bean.Application;

public class ApplicationMapper implements RowMapper<Application> {

	public Application mapRow(ResultSet rs, int rowNum) throws SQLException {

		String name = rs.getString("app_name");
		int appId = rs.getInt("id");
		int platformId = rs.getInt("platform");
		String description = rs.getString("description");

		String base64Icon = null;

		try {
			base64Icon = getBase64Image(rs.getBinaryStream("app_icon"));
		} catch (SQLException e) {
		}

		return new Application(name, appId, platformId, description, base64Icon);
	}

	private String getBase64Image(InputStream in) {
		try {
			return new String(Base64.encodeBase64(IOUtils.toByteArray(in)));
		} catch (IOException e) {
			Logger logger = LoggerFactory.getLogger(getClass());
			logger.error("Couldn't convert database image to Base64, returning empty string.");
			logger.error(e.getMessage());
		}

		return "";
	}
}

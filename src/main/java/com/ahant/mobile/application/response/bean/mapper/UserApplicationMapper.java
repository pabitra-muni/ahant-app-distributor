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

public class UserApplicationMapper implements RowMapper<Application> {

	public Application mapRow(ResultSet rs, int rowNum) throws SQLException {

		return null;
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

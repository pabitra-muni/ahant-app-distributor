package com.ahant.mobile.application.cache;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ahant.mobile.application.dao.ConnectionDao;
import com.ahant.mobile.application.response.bean.Application;
import com.ahant.mobile.application.response.bean.Platform;
import com.ahant.mobile.application.response.bean.ReleaseEnvironment;
import com.ahant.mobile.application.response.bean.mapper.ApplicationMapper;
import com.ahant.mobile.application.response.bean.mapper.EnvironmentMapper;
import com.ahant.mobile.application.response.bean.mapper.PlatformMapper;

public class ApplicationCacheManagerImpl implements ApplicationCacheManager,
		ConnectionDao {

	@Autowired
	private ApplicationCache applicationCache;

	@Autowired
	Environment env;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);

	}

	public void updateCache() throws DataAccessException {
		applicationCache.saveApplication(getApplicationsFromDB());
		applicationCache.savePlatform(getPlatformsFromDB());
		applicationCache.saveEnvironment(getEnvironmentsFromDB());
	}

	public void updateCache(CacheType type) throws DataAccessException {
		switch (type) {
		case PLATFORM:
			applicationCache.savePlatform(getPlatformsFromDB());
			break;
		case ENVIRONMENT:
			applicationCache.saveEnvironment(getEnvironmentsFromDB());
			break;
		case APPLICATION:
			applicationCache.saveApplication(getApplicationsFromDB());
			break;
		default:
			break;
		}
	}

	/**
	 * Application list without icon.
	 * 
	 * @return
	 * @throws DataAccessException
	 */
	private List<Application> getApplicationsFromDB()
			throws DataAccessException {
		try {
			return jdbcTemplate.query(env.getProperty("query.app.select")
					.replace("#icon", ""), new ApplicationMapper());
		} catch (DataAccessException e) {
			LoggerFactory.getLogger(getClass()).error(
					env.getProperty("cache.init.error"));
			throw e;
		}

	}

	private List<Platform> getPlatformsFromDB() throws DataAccessException {
		try {
			return jdbcTemplate.query(env.getProperty("query.platform.select"),
					new PlatformMapper());
		} catch (DataAccessException e) {
			LoggerFactory.getLogger(getClass()).error(
					env.getProperty("cache.init.error"));
			throw e;
		}

	}

	private List<ReleaseEnvironment> getEnvironmentsFromDB()
			throws DataAccessException {
		try {
			return jdbcTemplate.query(
					env.getProperty("query.environment.select"),
					new EnvironmentMapper());
		} catch (DataAccessException e) {
			LoggerFactory.getLogger(getClass()).error(
					env.getProperty("cache.init.error"));
			throw e;
		}
	}

}

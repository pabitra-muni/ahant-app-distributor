package com.ahant.mobile.application.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.ahant.mobile.application.cache.ApplicationCacheManager;

@Component
public class ApplicationConfig implements
		ApplicationListener<ContextRefreshedEvent> {

	private final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);

	@Autowired
	private Environment env;

	@Autowired
	private ApplicationCacheManager applicationCacheManager;

	public void onApplicationEvent(ContextRefreshedEvent event) {

		initializeLogger();
		initializeDatabase();
		try {
			LOG.info("Initializing cache");
			applicationCacheManager.updateCache();
			LOG.info("Cache initialized successfully");
		} catch (DataAccessException e) {
			LOG.error(env.getProperty("cache.init.error"));
		}
	}

	private void initializeLogger() {
		PropertyConfigurator.configure(Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("properties/log4j.properties"));

	}

	private void initializeDatabase() {

		StringBuilder conUrl = new StringBuilder();
		String host = env.getProperty("db.host");
		String port = env.getProperty("db.port");
		String user = env.getProperty("db.user");
		String password = env.getProperty("db.password");
		String schemaName = env.getProperty("db.schema");
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conUrl.append("jdbc:mysql://").append(host).append(":")
					.append(port);
			LOG.info(
					"Trying to connect to {} with username={} and password={}",
					conUrl.toString(), user, password);

			con = DriverManager
					.getConnection(conUrl.toString(), user, password);
			if (con != null) {
				LOG.debug(
						"Successfully connected to {} with username={} and password={}",
						conUrl.toString(), user, password);
				if (!isDatabaseExists(con, schemaName)) {
					LOG.debug("Database with name {} not found.", schemaName);
					createDatabase(con, schemaName);
				} else {
					LOG.info("Database with name {} already exists.",
							schemaName);
				}

			} else {
				LOG.error("Could not connect to {}", conUrl.toString());
			}
		} catch (Exception e) {
			LOG.error(
					"Exception occured while doing operations on {} with username={} and password={}",
					conUrl.toString(), user, password);
			LOG.error(e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage());
				}
			}
		}
	}

	private boolean isDatabaseExists(final Connection con, final String dbName)
			throws SQLException {
		Statement statement = null;
		boolean isExists = Boolean.FALSE;
		try {
			statement = con.createStatement();

			ResultSet resultSet = statement.executeQuery(String.format(
					"SHOW DATABASES LIKE '%s';", dbName));
			isExists = resultSet.first();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}

		return isExists;
	}

	private void createDatabase(final Connection con, final String dbName)
			throws SQLException {
		Statement statement = null;

		try {
			LOG.info(
					"Database {} not found. Creating database using statements from classpath:sql/db_schema.sql",
					dbName);
			statement = con.createStatement();
			String[] batches = getCreateDatabaseBatchQuery(dbName);
			for (String batch : batches) {
				statement.addBatch(batch.trim());
				LOG.info("batch: {}", batch);
			}
			statement.executeBatch();

		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	private String[] getCreateDatabaseBatchQuery(String dbName) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(Thread
					.currentThread().getContextClassLoader()
					.getResourceAsStream("sql/db_schema.sql")));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString().replaceAll("#db_name", dbName).split("#b");

	}

}

package com.ahant.mobile.application.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ahant.mobile.application.dao.AdminDatabaseDao;
import com.ahant.mobile.application.dao.ConnectionDao;
import com.ahant.mobile.application.response.bean.Application;
import com.ahant.mobile.application.response.bean.Platform;
import com.ahant.mobile.application.response.bean.ReleaseData;
import com.ahant.mobile.application.response.bean.ReleaseData.ReleaseFile;
import com.ahant.mobile.application.response.bean.ReleaseEnvironment;
import com.ahant.mobile.application.response.bean.mapper.ApplicationMapper;

public class AdminDatabaseDaoImpl implements ConnectionDao, AdminDatabaseDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(AdminDatabaseDaoImpl.class);

	private JdbcTemplate jdbcTemplate;
	@Autowired
	private Environment env;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	public int addApplication(Application app) throws DataAccessException {

		return jdbcTemplate.update(
				env.getProperty("query.app.add"),
				new Object[] { app.getAppName(), app.getPlatformId(),
						app.getAppDesc(), app.getIcon() });

	}

	public int updateApplication(Application app) throws DataAccessException {
		return jdbcTemplate.update(
				env.getProperty("query.app.update"),
				new Object[] { app.getAppName(), app.getPlatformId(),
						app.getAppDesc(), app.getIcon(), app.getAppId() });
	}

	public List<Application> getApplicationList() throws DataAccessException {

		return jdbcTemplate.query(
				env.getProperty("query.app.select").replace("#icon",
						", app_icon"), new ApplicationMapper());
	}

	public int addPlatform(Platform platform) throws DataAccessException {

		return jdbcTemplate.update(
				env.getProperty("query.platform.add"),
				new Object[] { platform.getPlatformName(),
						platform.getExecutableType() });

	}

	public int updatePlatform(Platform platform) throws DataAccessException {

		return jdbcTemplate.update(
				env.getProperty("query.platform.update"),
				new Object[] { platform.getPlatformName(),
						platform.getExecutableType(), platform.getId() });

	}

	public int addEnvironment(ReleaseEnvironment releaseEnv)
			throws DataAccessException {
		return jdbcTemplate.update(
				env.getProperty("query.environment.add"),
				new Object[] { releaseEnv.getEnvName(),
						releaseEnv.getDescription() });
	}

	public int updateEnvironment(ReleaseEnvironment releaseEnv)
			throws DataAccessException {
		return jdbcTemplate.update(
				env.getProperty("query.environment.update"),
				new Object[] { releaseEnv.getEnvName(),
						releaseEnv.getDescription(), releaseEnv.getId() });
	}

	public int saveReleaseData(final ReleaseData data)
			throws DataAccessException, java.text.ParseException {

		int release_id = saveReleaseInfo(data);

		if (release_id > 0) {
			// TODO: save the applications in hard drive before returning
			// return saveApplicationFiles(release_id, data);
			if (saveApplicationFiles(release_id, data) > 0) {
				return saveExecutablesInHardDrive(data);
			}
		}
		return 0;
	}

	private int saveApplicationFiles(final int release_id,
			final ReleaseData data) throws DataAccessException {
		Set<String> envSet = data.getApplicationMap().keySet();
		String appId = data.getAppId();
		String releaseNo = data.getReleaseNo();
		List<Object[]> batch = new ArrayList<Object[]>();

		Iterator<String> envIterator = envSet.iterator();
		while (envIterator.hasNext()) {
			final String envId = envIterator.next();
			final ReleaseFile file = data.getApplicationMap().get(envId);
			
				// size in Bytes
				final int fileSize = file.getData().length;
				/*System.out.println("********* 2" + file.getFileName() + ":"
						+ fileSize);*/
				/**
				 * file path convention is: APP_UPLOAD_DIR (environment
				 * variable)/app_id/release_no/env_id/file_name
				 */
				Object[] values = {
						release_id,
						envId,
						new StringBuilder().append(appId).append("/")
								.append(releaseNo).append("/").append(envId)
								.append("/").append(file.getFileName())
								.toString(), fileSize };
				batch.add(values);
			
		}
		int[] updateCounts = jdbcTemplate.batchUpdate(
				env.getProperty("query.app.release.add"), batch);

		return (updateCounts.length == data.getApplicationMap().size()) ? 1 : 0;
	}

	private int saveReleaseInfo(final ReleaseData data)
			throws DataAccessException, java.text.ParseException {

		int release_id = 0;

		if (jdbcTemplate.update(
				env.getProperty("query.release.add"),
				new Object[] { data.getAppId(), data.getReleaseNo(),
						data.getReleaseDate(), data.getReleaseNote() }) >= 1) {
			release_id = jdbcTemplate.queryForInt(
					env.getProperty("query.release.id.select"), new Object[] {
							data.getAppId(), data.getReleaseNo() });
		}

		return release_id;
	}

	private int saveExecutablesInHardDrive(final ReleaseData data) {
		Set<String> envSet = data.getApplicationMap().keySet();
		String appId = data.getAppId();
		String releaseNo = data.getReleaseNo();

		Iterator<String> envIterator = envSet.iterator();
		while (envIterator.hasNext()) {
			final String envId = envIterator.next();
			ReleaseFile file = data.getApplicationMap().get(envId);
			try {

				/*System.out.println("********* 1" + file.getFileName() + ":"
						+ file.getData().length);*/
				/**
				 * file path convention is: APP_UPLOAD_DIR (environment
				 * variable)/app_id/release_no/env_id/file_name
				 */
				String filePath = new StringBuilder()
						.append(System.getenv().get("APP_UPLOAD_DIR"))
						.append("/").append(appId).append("/")
						.append(releaseNo).append("/").append(envId)
						.append("/").append(file.getFileName()).toString();
				writeFile(filePath, file.getData());
			} catch (IOException e) {

				LOG.error(e.getMessage());
				return 0;
			}
		}
		return 1;
	}

	private void writeFile(String filePath, final byte[] data)
			throws IOException {

		OutputStream outputStream = null;

		try {

			File file = new File(filePath);
			File dir = file.getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if (dir.exists()) {
				outputStream = new FileOutputStream(file);
				/*System.out.println("********* 3" + file.getName() + ":"
						+ data.length);*/
				outputStream.write(data);
			} else {
				throw new IOException(
						"Could not create directories to save applications.");
			}

		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}
}

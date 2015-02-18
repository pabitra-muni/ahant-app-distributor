package com.ahant.mobile.application.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;

import com.ahant.mobile.application.cache.ApplicationCache;
import com.ahant.mobile.application.cache.ApplicationCacheManager;
import com.ahant.mobile.application.dao.AdminDatabaseDao;
import com.ahant.mobile.application.dao.AdminServiceDao;
import com.ahant.mobile.application.response.bean.Application;
import com.ahant.mobile.application.response.bean.ApplicationList;
import com.ahant.mobile.application.response.bean.ErrorEntity;
import com.ahant.mobile.application.response.bean.Platform;
import com.ahant.mobile.application.response.bean.ReleaseData;
import com.ahant.mobile.application.response.bean.ReleaseData.ReleaseFile;
import com.ahant.mobile.application.response.bean.ReleaseEnvironment;
import com.ahant.mobile.application.response.bean.ServiceResponse;
import com.ahant.mobile.application.response.bean.SuccessEntity;

public class AdminServiceDaoImpl implements AdminServiceDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(AdminServiceDaoImpl.class);
	@Autowired
	private AdminDatabaseDao adminDatabaseManager;
	@Autowired
	private ServiceResponse response;
	@Autowired
	private Environment env;

	@Autowired
	private ApplicationCacheManager applicationCacheManager;

	@Autowired
	private ApplicationCache applicationCache;

	public Response addApplication(Application app) {

		if (app != null && StringUtils.hasText(app.getAppName())
				&& StringUtils.hasText(app.getAppDesc())
				&& app.getPlatformId() != null && app.getIcon() != null) {

			if (!StringUtils.containsWhitespace(app.getAppName().trim())) {
				try {
					int updateCount = adminDatabaseManager.addApplication(app);

					LOG.info("Updating application cache");
					applicationCacheManager
							.updateCache(ApplicationCacheManager.CacheType.APPLICATION);

					response.setEntity(new SuccessEntity(env.getProperty(
							"update.record.success").replace("#count",
							String.valueOf(updateCount))));
					response.setStatus(Status.OK);
				} catch (DataAccessException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("database.error")));
					response.setStatus(Status.SERVICE_UNAVAILABLE);
					LOG.error(e.getMessage());
				} catch (RuntimeException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("internal.server.error")));
					response.setStatus(Status.INTERNAL_SERVER_ERROR);
					LOG.error(e.getMessage());
				}
			} else {

				response.setEntity(new ErrorEntity(env
						.getProperty("invalid.app.detail")));
				response.setStatus(Status.PRECONDITION_FAILED);
			}

		} else {

			response.setEntity(new ErrorEntity(env
					.getProperty("required.field.missing")));
			response.setStatus(Status.PRECONDITION_FAILED);
		}

		return Response.status(response.getStatus())
				.entity(response.getEntity()).build();
	}

	public Response updateApplication(Application app) {

		if (app != null && StringUtils.hasText(app.getAppName())
				&& StringUtils.hasText(app.getAppDesc())
				&& app.getPlatformId() != null && app.getIcon() != null
				&& app.getAppId() != null) {

			if (!StringUtils.containsWhitespace(app.getAppName().trim())) {
				try {
					int updateCount = adminDatabaseManager
							.updateApplication(app);

					LOG.info("Updating application cache");
					applicationCacheManager
							.updateCache(ApplicationCacheManager.CacheType.APPLICATION);

					response.setEntity(new SuccessEntity(env.getProperty(
							"update.record.success").replace("#count",
							String.valueOf(updateCount))));
					response.setStatus(Status.OK);
				} catch (DataAccessException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("database.error")));
					response.setStatus(Status.SERVICE_UNAVAILABLE);
					LOG.error(e.getMessage());
				} catch (RuntimeException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("internal.server.error")));
					response.setStatus(Status.INTERNAL_SERVER_ERROR);
					LOG.error(e.getMessage());
				}
			} else {

				response.setEntity(new ErrorEntity(env
						.getProperty("invalid.app.detail")));
				response.setStatus(Status.PRECONDITION_FAILED);
			}

		} else {

			response.setEntity(new ErrorEntity(env
					.getProperty("required.field.missing")));
			response.setStatus(Status.PRECONDITION_FAILED);
		}

		return Response.status(response.getStatus())
				.entity(response.getEntity()).build();
	}

	public Response addPlatform(Platform platform) {

		if (platform != null && StringUtils.hasText(platform.getPlatformName())
				&& StringUtils.hasText(platform.getExecutableType())) {

			if (!StringUtils.containsWhitespace(platform.getPlatformName()
					.trim())
					&& !StringUtils.containsWhitespace(platform
							.getExecutableType().trim())) {
				try {
					int updateCount = adminDatabaseManager
							.addPlatform(platform);

					LOG.info("Updating platform cache");
					applicationCacheManager
							.updateCache(ApplicationCacheManager.CacheType.PLATFORM);

					response.setEntity(new SuccessEntity(env.getProperty(
							"update.record.success").replace("#count",
							String.valueOf(updateCount))));

					response.setStatus(Status.OK);
				} catch (DataAccessException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("database.error")));
					response.setStatus(Status.SERVICE_UNAVAILABLE);
					LOG.error(e.getMessage());
				} catch (RuntimeException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("internal.server.error")));
					response.setStatus(Status.INTERNAL_SERVER_ERROR);
					LOG.error(e.getMessage());
				}
			} else {

				response.setEntity(new ErrorEntity(env
						.getProperty("invalid.platform.detail")));
				response.setStatus(Status.PRECONDITION_FAILED);
			}

		} else {

			response.setEntity(new ErrorEntity(env
					.getProperty("required.field.missing")));
			response.setStatus(Status.PRECONDITION_FAILED);
		}

		return Response.status(response.getStatus())
				.entity(response.getEntity()).build();

	}

	public Response updatePlatform(Platform platform) {

		if (platform != null && StringUtils.hasText(platform.getPlatformName())
				&& StringUtils.hasText(platform.getExecutableType())
				&& platform.getId() != null) {

			if (!StringUtils.containsWhitespace(platform.getPlatformName()
					.trim())
					&& !StringUtils.containsWhitespace(platform
							.getExecutableType().trim())) {
				try {
					int updateCount = adminDatabaseManager
							.updatePlatform(platform);

					LOG.info("Updating platform cache");
					applicationCacheManager
							.updateCache(ApplicationCacheManager.CacheType.PLATFORM);

					response.setEntity(new SuccessEntity(env.getProperty(
							"update.record.success").replace("#count",
							String.valueOf(updateCount))));

					response.setStatus(Status.OK);
				} catch (DataAccessException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("database.error")));
					response.setStatus(Status.SERVICE_UNAVAILABLE);
					LOG.error(e.getMessage());
				} catch (RuntimeException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("internal.server.error")));
					response.setStatus(Status.INTERNAL_SERVER_ERROR);
					LOG.error(e.getMessage());
				}
			} else {

				response.setEntity(new ErrorEntity(env
						.getProperty("invalid.platform.detail")));
				response.setStatus(Status.PRECONDITION_FAILED);
			}

		} else {

			response.setEntity(new ErrorEntity(env
					.getProperty("required.field.missing")));
			response.setStatus(Status.PRECONDITION_FAILED);
		}

		return Response.status(response.getStatus())
				.entity(response.getEntity()).build();

	}

	public Response addEnvironment(ReleaseEnvironment releaseEnv) {

		System.out.println(">>>>>>releaseEnv:" + releaseEnv);
		System.out.println(">>>>>releaseEnv.getEnvName():"
				+ releaseEnv.getEnvName());
		System.out.println(">>>>>>getDescription:"
				+ releaseEnv.getDescription());
		if (releaseEnv != null && StringUtils.hasText(releaseEnv.getEnvName())
				&& StringUtils.hasText(releaseEnv.getDescription())) {

			if (!StringUtils.containsWhitespace(releaseEnv.getEnvName().trim())) {
				try {
					int updateCount = adminDatabaseManager
							.addEnvironment(releaseEnv);

					LOG.info("Updating environment cache");
					applicationCacheManager
							.updateCache(ApplicationCacheManager.CacheType.ENVIRONMENT);

					response.setEntity(new SuccessEntity(env.getProperty(
							"update.record.success").replace("#count",
							String.valueOf(updateCount))));

					response.setStatus(Status.OK);
				} catch (DataAccessException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("database.error")));
					response.setStatus(Status.SERVICE_UNAVAILABLE);
					LOG.error(e.getMessage());
				} catch (RuntimeException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("internal.server.error")));
					response.setStatus(Status.INTERNAL_SERVER_ERROR);
					LOG.error(e.getMessage());
				}
			} else {

				response.setEntity(new ErrorEntity(env
						.getProperty("invalid.environment.detail")));
				response.setStatus(Status.PRECONDITION_FAILED);
			}

		} else {

			response.setEntity(new ErrorEntity(env
					.getProperty("required.field.missing")));
			response.setStatus(Status.PRECONDITION_FAILED);
		}

		return Response.status(response.getStatus())
				.entity(response.getEntity()).build();

	}

	public Response updateEnvironment(ReleaseEnvironment releaseEnv) {

		if (releaseEnv != null && StringUtils.hasText(releaseEnv.getEnvName())
				&& StringUtils.hasText(releaseEnv.getDescription())
				&& releaseEnv.getId() != null) {

			if (!StringUtils.containsWhitespace(releaseEnv.getEnvName().trim())) {
				try {
					int updateCount = adminDatabaseManager
							.updateEnvironment(releaseEnv);

					LOG.info("Updating environment cache");
					applicationCacheManager
							.updateCache(ApplicationCacheManager.CacheType.ENVIRONMENT);

					response.setEntity(new SuccessEntity(env.getProperty(
							"update.record.success").replace("#count",
							String.valueOf(updateCount))));

					response.setStatus(Status.OK);
				} catch (DataAccessException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("database.error")));
					response.setStatus(Status.SERVICE_UNAVAILABLE);
					LOG.error(e.getMessage());
				} catch (RuntimeException e) {
					response.setEntity(new ErrorEntity(env
							.getProperty("internal.server.error")));
					response.setStatus(Status.INTERNAL_SERVER_ERROR);
					LOG.error(e.getMessage());
				}
			} else {

				response.setEntity(new ErrorEntity(env
						.getProperty("invalid.environment.detail")));
				response.setStatus(Status.PRECONDITION_FAILED);
			}

		} else {

			response.setEntity(new ErrorEntity(env
					.getProperty("required.field.missing")));
			response.setStatus(Status.PRECONDITION_FAILED);
		}

		return Response.status(response.getStatus())
				.entity(response.getEntity()).build();

	}

	public Response getApplicationList(int isIcon) {

		try {
			ApplicationList appList = new ApplicationList();
			if (isIcon == 1) {
				// with icon
				appList.setAppList(adminDatabaseManager.getApplicationList());
			} else {
				// without icon, get from cache
				appList.setAppList(applicationCache.getApplications());
			}

			response.setEntity(appList);
			response.setStatus(Status.OK);
		} catch (DataAccessException e) {
			response.setEntity(new ErrorEntity(env
					.getProperty("database.error")));
			response.setStatus(Status.SERVICE_UNAVAILABLE);
			LOG.error(e.getMessage());
		} catch (RuntimeException e) {
			response.setEntity(new ErrorEntity(env
					.getProperty("internal.server.error")));
			response.setStatus(Status.INTERNAL_SERVER_ERROR);
			LOG.error(e.getMessage());
		}

		return Response.status(response.getStatus())
				.entity(response.getEntity()).build();
	}

	public Response uploadRelease(final FormDataMultiPart multipart) {

		if (multipart != null) {
			String uploadPath = System.getenv().get("APP_UPLOAD_DIR");
			if (uploadPath != null && uploadPath.length() > 0) {
				final ReleaseData releaseData = extractReleaseData(multipart);
				if (isReleaseDataValid(releaseData)) {
					try {
						// will override if already exists
						int status = adminDatabaseManager
								.saveReleaseData(releaseData);
						if (status == 1) {
							response.setEntity(new SuccessEntity(env
									.getProperty("upload.success")));
							response.setStatus(Status.CREATED);

							// TODO: SEND notification to users.
						} else {
							response.setEntity(new ErrorEntity(env
									.getProperty("upload.failure")));
							response.setStatus(Status.SERVICE_UNAVAILABLE);
						}
					} catch (DataAccessException e) {
						response.setEntity(new ErrorEntity(env
								.getProperty("upload.failure")));
						response.setStatus(Status.SERVICE_UNAVAILABLE);
						LOG.error(e.getMessage());
					} catch (ParseException e) {
						response.setEntity(new ErrorEntity(env
								.getProperty("upload.failure")));
						response.setStatus(Status.SERVICE_UNAVAILABLE);
						LOG.error(e.getMessage());
					} catch (RuntimeException e) {
						response.setEntity(new ErrorEntity(env
								.getProperty("internal.server.error")));
						response.setStatus(Status.INTERNAL_SERVER_ERROR);
						LOG.error(e.getMessage());
					}
				}
			} else {
				response.setEntity(new ErrorEntity(env
						.getProperty("internal.server.error")));
				response.setStatus(Status.INTERNAL_SERVER_ERROR);
				LOG.error(env.getProperty("file.upload.dir.missing"));
			}
		} else {
			response.setEntity(new ErrorEntity(env
					.getProperty("required.field.missing")));
			response.setStatus(Status.PRECONDITION_FAILED);
		}

		return Response.status(response.getStatus())
				.entity(response.getEntity()).build();
	}

	private boolean isReleaseDataValid(ReleaseData releaseData) {

		String strAppId = releaseData.getAppId();
		String strReleaseNo = releaseData.getReleaseNo();
		String releaseDate = releaseData.getReleaseDate();
		String releaseNote = releaseData.getReleaseNote();
		Map<String, ReleaseFile> applicationMap = releaseData
				.getApplicationMap();

		if (strAppId == null || (strAppId.trim().length() == 0)
				|| strReleaseNo == null || (strReleaseNo.trim().length() == 0)
				|| releaseDate == null || (releaseDate.trim().length() == 0)
				|| releaseNote == null || (releaseNote.trim().length() == 0)
				|| applicationMap == null || applicationMap.size() == 0) {

			response.setEntity(new ErrorEntity(env
					.getProperty("required.field.missing")));
			response.setStatus(Status.PRECONDITION_FAILED);
			return false;
		}

		// verify application id
		try {
			int appId = Integer.valueOf(strAppId.trim());
			if (!applicationCache.isApplicationExists(appId)) {
				response.setEntity(new ErrorEntity(env
						.getProperty("invalid.app.id")));
				response.setStatus(Status.PRECONDITION_FAILED);
				return false;
			}
		} catch (NumberFormatException e) {
			response.setEntity(new ErrorEntity(env
					.getProperty("invalid.app.id")));
			response.setStatus(Status.PRECONDITION_FAILED);
			return false;
		}

		// verify release no
		try {
			Double.valueOf(strReleaseNo);
		} catch (NumberFormatException e) {
			response.setEntity(new ErrorEntity(env
					.getProperty("invalid.release.no")));
			response.setStatus(Status.PRECONDITION_FAILED);
			return false;
		}

		// date must be in 'yyyy-mm-dd' format.
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			format.parse(releaseDate);
		} catch (ParseException e) {
			response.setEntity(new ErrorEntity(env
					.getProperty("invalid.release.date")));
			response.setStatus(Status.PRECONDITION_FAILED);
			return false;
		}

		// verify environment id(s)
		Iterator<String> envIdIterator = applicationMap.keySet().iterator();
		while (envIdIterator.hasNext()) {
			try {
				final String envId = envIdIterator.next();

				if (!applicationCache.isEnvironmentExists(Integer
						.valueOf(envId))) {
					response.setEntity(new ErrorEntity(env
							.getProperty("invalid.environment.id")));
					response.setStatus(Status.PRECONDITION_FAILED);
					return false;
				}

			} catch (NumberFormatException e) {
				response.setEntity(new ErrorEntity(env
						.getProperty("invalid.environment.id")));
				response.setStatus(Status.PRECONDITION_FAILED);
				return false;
			}

		}

		return true;
	}

	/**
	 * Extracts uploaded form data. Followed convention is: binary file name =
	 * "file_envId".
	 * 
	 * @param multipart
	 * @return ({@link ReleaseData}
	 */
	private ReleaseData extractReleaseData(FormDataMultiPart multipart) {
		ReleaseData releaseData = new ReleaseData();

		Map<String, List<FormDataBodyPart>> formFieldMap = multipart
				.getFields();

		for (String key : formFieldMap.keySet()) {
			key = key.trim();
			FormDataBodyPart bodyPart = formFieldMap.get(key).get(0);
			// All the executables must have key as 'file_envId'.
			if (key.startsWith("file_")) {
				String[] envArray = key.split("_");
				// Avoid ArrayIndexOutOfBoundException.
				if (envArray.length == 2) {
					try {
						releaseData.addApplication(
								envArray[1].trim(),
								new ReleaseData.ReleaseFile(bodyPart
										.getFormDataContentDisposition()
										.getFileName(), IOUtils.toByteArray(bodyPart
										.getValueAs(InputStream.class))));
					} catch (IOException e) {
					}

				}

			} else {
				switch (getReleaseKey(key)) {
				case APP_ID:
					releaseData.setAppId(bodyPart.getValue().trim());
					break;
				case RELEASE_NO:
					releaseData.setReleaseNo(bodyPart.getValue().trim());
					break;
				case RELEASE_DATE:
					releaseData.setReleaseDate(bodyPart.getValue().trim());
					break;
				case RELEASE_NOTE:
					releaseData.setReleaseNote(bodyPart.getValue().trim());
					break;
				case NOTIFY_MSG:
					releaseData.setNotificationMsg(bodyPart.getValue().trim());
					break;
				}
			}
		}

		return releaseData;
	}

	private enum ReleaseKey {
		APP_ID, RELEASE_NO, RELEASE_DATE, RELEASE_NOTE, NOTIFY_MSG
	}

	/**
	 * The client must upload the form with field names same as the keys used in
	 * <code>releaseKeyMap</code> object. Only <code>notify_msg</code> is
	 * optional.
	 * 
	 * @param key
	 * @return
	 */
	private Map<String, ReleaseKey> releaseKeyMap;

	private ReleaseKey getReleaseKey(String key) {
		if (releaseKeyMap == null) {
			releaseKeyMap = new HashMap<String, ReleaseKey>();

			releaseKeyMap.put("app_id", ReleaseKey.APP_ID);
			releaseKeyMap.put("release_no", ReleaseKey.RELEASE_NO);
			releaseKeyMap.put("release_date", ReleaseKey.RELEASE_DATE);
			releaseKeyMap.put("release_note", ReleaseKey.RELEASE_NOTE);
			releaseKeyMap.put("notify_msg", ReleaseKey.NOTIFY_MSG);
		}
		return releaseKeyMap.get(key);
	}
}

package com.ahant.mobile.application.dao.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.ahant.mobile.application.cache.ApplicationCache;
import com.ahant.mobile.application.dao.UserServiceDao;
import com.ahant.mobile.application.response.bean.EnvironmentList;
import com.ahant.mobile.application.response.bean.PlatformList;
import com.ahant.mobile.application.response.bean.ServiceResponse;

public class UserServiceDaoImpl implements UserServiceDao {

	@Autowired
	private ServiceResponse response;
	@Autowired
	private Environment env;
	@Autowired
	private ApplicationCache applicationCache;

	public Response getReleaseList(int isIcon) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get platforms from cache.
	 */
	public Response getPlatformList() {
		PlatformList platformList = new PlatformList();
		platformList.setPlatformList(applicationCache.getPlatforms());

		response.setEntity(platformList);
		response.setStatus(Status.OK);

		return Response.status(response.getStatus())
				.entity(response.getEntity()).build();

	}

	/**
	 * Get environments from cache
	 */
	public Response getEnvironmentList() {
		EnvironmentList envList = new EnvironmentList();
		envList.setEnvList(applicationCache.getEnvironments());

		response.setEntity(envList);
		response.setStatus(Status.OK);

		return Response.status(response.getStatus())
				.entity(response.getEntity()).build();
	}

}

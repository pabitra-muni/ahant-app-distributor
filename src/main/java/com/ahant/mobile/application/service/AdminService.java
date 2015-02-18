package com.ahant.mobile.application.service;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahant.mobile.application.dao.AdminServiceDao;
import com.ahant.mobile.application.response.bean.Application;
import com.ahant.mobile.application.response.bean.Platform;
import com.ahant.mobile.application.response.bean.ReleaseEnvironment;

@Service
@Path("/admin")
public class AdminService {

	@Autowired
	private AdminServiceDao adminServiceDao;

	@POST
	@Path("/add-app")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response addApplication(@FormDataParam("app_name") String appName,
			@FormDataParam("platform") Integer platform,
			@FormDataParam("description") String description,
			@FormDataParam("icon") InputStream icon) {

		return adminServiceDao.addApplication(new Application(appName,
				platform, icon, description));

	}

	@POST
	@Path("/update-app")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateApplication(
			@FormDataParam("app_name") String appName,
			@FormDataParam("app_id") Integer appId,
			@FormDataParam("platform") Integer platformId,
			@FormDataParam("icon") InputStream icon,
			@FormDataParam("description") String description) {

		return adminServiceDao.updateApplication(new Application(appName,
				appId, platformId, icon, description));
	}

	@GET
	@Path("/get-apps")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApplicationList(@QueryParam("icon") int isIcon) {

		return adminServiceDao.getApplicationList(isIcon);
	}

	@POST
	@Path("/add-platform")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPlatform(Platform platform) {

		return adminServiceDao.addPlatform(platform);
	}

	@POST
	@Path("/update-platform")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePlatform(Platform platform) {

		return adminServiceDao.updatePlatform(platform);
	}

	@POST
	@Path("/add-environment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEnvironment(ReleaseEnvironment env) {

		return adminServiceDao.addEnvironment(env);
	}

	@POST
	@Path("/update-environment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEnvironment(ReleaseEnvironment env) {

		return adminServiceDao.updateEnvironment(env);
	}

	@POST
	@Path("/upload_release")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadRelease(FormDataMultiPart multipart) {

		return adminServiceDao.uploadRelease(multipart);

	}

}

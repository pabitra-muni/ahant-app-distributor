package com.ahant.mobile.application.dao;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.ahant.mobile.application.response.bean.Application;
import com.ahant.mobile.application.response.bean.Platform;
import com.ahant.mobile.application.response.bean.ReleaseEnvironment;

public interface AdminServiceDao {

	Response addApplication(Application app);

	Response updateApplication(Application app);

	Response addPlatform(Platform platform);

	Response updatePlatform(Platform platform);

	Response addEnvironment(ReleaseEnvironment releaseEnv);

	Response updateEnvironment(ReleaseEnvironment releaseEnv);

	Response getApplicationList(int isIcon);
	
	Response uploadRelease(FormDataMultiPart multipart);
	
	//Response updateRelease(FormDataMultiPart multipart);
}

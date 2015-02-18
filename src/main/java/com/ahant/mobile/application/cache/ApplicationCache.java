package com.ahant.mobile.application.cache;

import java.util.List;

import com.ahant.mobile.application.response.bean.Application;
import com.ahant.mobile.application.response.bean.Platform;
import com.ahant.mobile.application.response.bean.ReleaseEnvironment;

public interface ApplicationCache {

	void saveApplication(List<Application> appList);

	List<Application> getApplications();

	boolean isApplicationExists(Integer appId);

	void savePlatform(List<Platform> platformList);

	List<Platform> getPlatforms();

	boolean isPlatformExists(Integer platformId);

	void saveEnvironment(List<ReleaseEnvironment> envList);

	List<ReleaseEnvironment> getEnvironments();

	boolean isEnvironmentExists(Integer envId);
	
	boolean isiOSApp(Integer appId);
}

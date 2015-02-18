package com.ahant.mobile.application.cache;

import java.util.ArrayList;
import java.util.List;

import com.ahant.mobile.application.response.bean.Application;
import com.ahant.mobile.application.response.bean.Platform;
import com.ahant.mobile.application.response.bean.ReleaseEnvironment;

public class ApplicationCacheImpl implements ApplicationCache {

	public void saveApplication(List<Application> appList) {
		if (appList != null && appList.size() > 0) {
			applications.clear();
			applications.addAll(appList);
		}
	}

	public List<Application> getApplications() {
		return applications;
	}

	public boolean isApplicationExists(Integer appId) {
		if (appId == null) {
			return false;
		}

		return applications.contains(new Application(appId));
	}

	public void savePlatform(List<Platform> platformList) {
		if (platformList != null && platformList.size() > 0) {
			platforms.clear();
			platforms.addAll(platformList);
		}
	}

	public List<Platform> getPlatforms() {
		return platforms;
	}

	public boolean isPlatformExists(Integer platformId) {
		if (platformId == null) {
			return false;
		}
		Platform platform = new Platform();
		platform.setId(platformId);
		return platforms.contains(platform);
	}

	public void saveEnvironment(List<ReleaseEnvironment> envList) {
		if (envList != null && envList.size() > 0) {
			environments.clear();
			environments.addAll(envList);
		}

	}

	public List<ReleaseEnvironment> getEnvironments() {
		return environments;
	}

	public boolean isEnvironmentExists(Integer envId) {
		if (envId == null) {
			return false;
		}

		ReleaseEnvironment env = new ReleaseEnvironment();
		env.setId(envId);
		return environments.contains(env);
	}

	/**
	 * Returns if the application is an iOS application (iPad, iPhone, iPod) or
	 * not. Convention followed is: if the application platform starts with "i",
	 * then the application is considered as an iOS application.
	 */
	public boolean isiOSApp(Integer appId) {
		if (appId == null) {
			return false;
		}
		Application searchedApp = new Application(appId);
		int appIndex = applications.indexOf(searchedApp);
		if (appIndex != -1) {

			Platform searchedPlatform = new Platform();
			searchedPlatform.setId(applications.get(appIndex).getPlatformId());
			int platformIndex = platforms.indexOf(searchedPlatform);
			if (platformIndex != -1) {
				return platforms.get(platformIndex).getPlatformName()
						.startsWith("i") ? true : false;
			} else {
				return false;
			}
		}
		return false;
	}

	List<Application> applications = new ArrayList<Application>();
	List<Platform> platforms = new ArrayList<Platform>();
	List<ReleaseEnvironment> environments = new ArrayList<ReleaseEnvironment>();

}

package com.ahant.mobile.application.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ahant.mobile.application.response.bean.Application;
import com.ahant.mobile.application.response.bean.Platform;
import com.ahant.mobile.application.response.bean.ReleaseData;
import com.ahant.mobile.application.response.bean.ReleaseEnvironment;

public interface AdminDatabaseDao {

	int addApplication(Application app);

	int updateApplication(Application app);

	int addPlatform(Platform platform);

	int updatePlatform(Platform platform);

	int addEnvironment(ReleaseEnvironment releaseEnv);

	int updateEnvironment(ReleaseEnvironment releaseEnv);

	List<Application> getApplicationList();

	int saveReleaseData(ReleaseData data) throws DataAccessException, ParseException;
}

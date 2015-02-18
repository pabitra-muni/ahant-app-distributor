package com.ahant.mobile.application.dao;

import java.util.List;

import com.ahant.mobile.application.response.bean.Application;
import com.ahant.mobile.application.response.bean.Platform;
import com.ahant.mobile.application.response.bean.ReleaseEnvironment;

public interface UserDatabaseDao {

	List<Application> getReleaseList(int isIcon);

}

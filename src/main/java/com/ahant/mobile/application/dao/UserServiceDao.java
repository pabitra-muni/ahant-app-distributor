package com.ahant.mobile.application.dao;

import javax.ws.rs.core.Response;

public interface UserServiceDao {

	Response getReleaseList(int isIcon);

	Response getPlatformList();

	Response getEnvironmentList();
}

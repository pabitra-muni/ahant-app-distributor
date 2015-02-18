package com.ahant.mobile.application.cache;

import org.springframework.dao.DataAccessException;

public interface ApplicationCacheManager {

	void updateCache() throws DataAccessException;

	void updateCache(CacheType type) throws DataAccessException;

	enum CacheType {
		APPLICATION, PLATFORM, ENVIRONMENT
	}
}

package com.ahant.mobile.application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class CustomApplication extends ResourceConfig {

	public CustomApplication() {
		super(MultiPartFeature.class, JacksonFeature.class,
				CustomJsonMapperProvider.class);
		packages("com.ahant.mobile.application.service");
		
	}

}

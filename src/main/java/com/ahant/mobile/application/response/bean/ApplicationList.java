package com.ahant.mobile.application.response.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("apps")
public class ApplicationList{

	@JsonProperty("application_list")
	private List<Application> appList;

	public List<Application> getAppList() {
		return appList;
	}

	public void setAppList(List<Application> appList) {
		this.appList = appList;
	}

}

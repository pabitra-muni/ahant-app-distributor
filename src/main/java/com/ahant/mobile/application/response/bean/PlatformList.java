package com.ahant.mobile.application.response.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("platforms")
public class PlatformList{

	@JsonProperty("platform_list")
	private List<Platform> platformList;

	public List<Platform> getPlatformList() {
		return platformList;
	}

	public void setPlatformList(List<Platform> platformList) {
		this.platformList = platformList;
	}

}

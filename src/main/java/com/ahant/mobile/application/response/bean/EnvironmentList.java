package com.ahant.mobile.application.response.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("envs")
public class EnvironmentList{

	@JsonProperty("env_list")
	private List<ReleaseEnvironment> envList;

	public List<ReleaseEnvironment> getEnvList() {
		return envList;
	}

	public void setEnvList(List<ReleaseEnvironment> envList) {
		this.envList = envList;
	}

}

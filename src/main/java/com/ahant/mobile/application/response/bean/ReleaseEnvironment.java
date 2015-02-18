package com.ahant.mobile.application.response.bean;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("environment")
public class ReleaseEnvironment {

	private Integer id;
	@JsonProperty("name")
	private String envName;
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		ReleaseEnvironment other = (ReleaseEnvironment) obj;
		if (this.getId() == other.getId()) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int hash = 3 * (this.getId() != null ? this.getId().hashCode() : 0);
		return hash;
	}
}

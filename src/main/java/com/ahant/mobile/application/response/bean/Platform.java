package com.ahant.mobile.application.response.bean;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("platform")
public class Platform {

	private Integer id;
	@JsonProperty("name")
	private String platformName;
	@JsonProperty("executable_type")
	private String executableType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getExecutableType() {
		return executableType;
	}

	public void setExecutableType(String executableType) {
		this.executableType = executableType;
	}

	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Platform other = (Platform) obj;
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

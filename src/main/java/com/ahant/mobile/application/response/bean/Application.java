package com.ahant.mobile.application.response.bean;

import java.io.InputStream;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("application")
@JsonPropertyOrder({ "id", "name", "platform_id", "description" })
public class Application {

	@JsonProperty("name")
	private String appName;
	@JsonProperty("id")
	private Integer appId;
	@JsonProperty("platform_id")
	private Integer platformId;
	private InputStream icon;
	@JsonProperty("description")
	private String appDesc;
	@JsonProperty("base64_icon")
	private String base64Icon;

	// Required for Jackson.
	public Application(String s) {
	}
	
	/**
	 * Constructor for adding a new application
	 * 
	 * @param appName
	 * @param platformId
	 * @param icon
	 * @param appDesc
	 */
	public Application(String appName, Integer platformId, InputStream icon,
			String appDesc) {
		this.appName = appName;
		this.platformId = platformId;
		this.icon = icon;
		this.appDesc = appDesc;

	}

	/**
	 * Constructor for updating existing application
	 * 
	 * @param appName
	 * @param appId
	 * @param platformId
	 * @param icon
	 * @param appDesc
	 */
	public Application(String appName, Integer appId, Integer platformId,
			InputStream icon, String appDesc) {
		this.appName = appName;
		this.appId = appId;
		this.platformId = platformId;
		this.icon = icon;
		this.appDesc = appDesc;

	}

	/**
	 * Constructor for getting application list
	 * 
	 * @param appName
	 * @param appId
	 * @param platformId
	 * @param appDesc
	 * @param base64Icon
	 */
	public Application(String appName, Integer appId, Integer platformId,
			String appDesc, String base64Icon) {
		this.appName = appName;
		this.appId = appId;
		this.platformId = platformId;
		this.appDesc = appDesc;
		this.base64Icon = base64Icon;

	}

	/**
	 * This constructor is only provided to facilitate the easy creation of an
	 * object containing only id for being used in equals comparison.
	 * 
	 * @param appId
	 */
	public Application(Integer appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	public InputStream getIcon() {
		return icon;
	}

	public void setIcon(InputStream icon) {
		this.icon = icon;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public String getBase64Icon() {
		return base64Icon;
	}

	public void setBase64Icon(String base64Icon) {
		this.base64Icon = base64Icon;
	}

	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Application other = (Application) obj;
		if (this.getAppId() == other.getAppId()) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int hash = 3 * (this.getAppId() != null ? this.getAppId().hashCode()
				: 0);
		return hash;
	}
}

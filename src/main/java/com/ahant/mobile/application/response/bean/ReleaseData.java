package com.ahant.mobile.application.response.bean;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ReleaseData {

	private String appId;
	private String releaseNo;
	private String releaseDate;
	private String releaseNote;
	private String notificationMsg;
	private Map<String, ReleaseFile> applicationMap;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getReleaseNo() {
		return releaseNo;
	}

	public void setReleaseNo(String releaseNo) {
		this.releaseNo = releaseNo;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseNote() {
		return releaseNote;
	}

	public void setReleaseNote(String releaseNote) {
		this.releaseNote = releaseNote;
	}

	public String getNotificationMsg() {
		return notificationMsg;
	}

	public void setNotificationMsg(String notificationMsg) {
		this.notificationMsg = notificationMsg;
	}

	public Map<String, ReleaseFile> getApplicationMap() {
		return applicationMap;
	}

	public void addApplication(String envId, ReleaseFile file) {
		if (applicationMap == null) {
			applicationMap = new HashMap<String, ReleaseFile>();
		}
		applicationMap.put(envId, file);
	}

	public static class ReleaseFile {

		public ReleaseFile(String fileName, byte[] data) {
			this.fileName = fileName;
			this.data = data;
		}

		private String fileName;
		private byte[] data;

		public String getFileName() {
			return fileName;
		}

		public byte[] getData() {
			return data;
		}

	}
}

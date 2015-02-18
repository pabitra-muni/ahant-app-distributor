package com.ahant.mobile.application.response.bean;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("error")
public class ErrorEntity {

	public ErrorEntity(String message) {
		setMessage(message);
	}

	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
}

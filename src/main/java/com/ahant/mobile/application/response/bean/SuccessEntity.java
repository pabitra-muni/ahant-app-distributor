package com.ahant.mobile.application.response.bean;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("success")
public class SuccessEntity {
	
	public SuccessEntity(String message){
		setMessage(message);
	}
	
	private String message;

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

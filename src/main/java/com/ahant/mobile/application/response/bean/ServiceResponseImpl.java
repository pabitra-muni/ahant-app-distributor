package com.ahant.mobile.application.response.bean;

import java.util.List;

import javax.ws.rs.core.Response.Status;


public class ServiceResponseImpl implements ServiceResponse {

	private Object entity;
	private Status status;

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setEntity(List<Object> entity) {
		this.entity = entity;
		
	}

}

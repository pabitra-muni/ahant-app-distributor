package com.ahant.mobile.application.response.bean;

import java.util.List;

import javax.ws.rs.core.Response.Status;

public interface ServiceResponse {

	Object getEntity();

	void setEntity(Object entity);

	void setEntity(List<Object> entityList);

	Status getStatus();

	void setStatus(Status status);
}

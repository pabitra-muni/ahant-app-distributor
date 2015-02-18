package com.ahant.mobile.application.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahant.mobile.application.dao.UserServiceDao;

@Service
@Path("/user")
public class UserService {

	@Autowired
	private UserServiceDao userServiceDao;

	@GET
	@Path("/get-platforms")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlatformList() {
		return userServiceDao.getPlatformList();
	}

	@GET
	@Path("/get-environments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEnvironmentList() {
		return userServiceDao.getEnvironmentList();
	}
	
}

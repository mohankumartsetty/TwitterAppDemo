package com.tjx.resources;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.tjx.dao.UserDAO;
import com.tjx.dto.User;
import com.tjx.service.UserService;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List getUsers()
	{
		List<User> users = new ArrayList();
		try
		{
			UserDAO userDaoObj = new UserDAO(); 
			return userDaoObj.getAllUsers();
		}
		catch(IOException ioExp)
		{
			ioExp.printStackTrace();
		}
		
		return users;
	}
	@POST
	@Path("/create")
	public Response createUser(
		@FormParam("username") String username,
		@FormParam("password") String password) {

		User userObj=new User();
		userObj.setUsername(username);
		userObj.setPassword(password);
		
		UserDAO userDaoObj = new UserDAO();
		String responseMessage = "";
		
		try {
			if(!UserService.isExistingUser(userObj)) {
				userDaoObj.createUser(userObj);
				return Response.status(200).entity("User has been created : " + username ).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(200)
			.entity("User : " + username + " already exists, please use login ").build();

	}	
	
	@POST
	@Path("/login")
	public Response loginUser(
		@FormParam("username") String username,
		@FormParam("password") String password,
		@Context HttpServletRequest  request) throws URISyntaxException {

		User userObj=new User();
		userObj.setUsername(username);
		userObj.setPassword(password);
		
		UserDAO userDaoObj = new UserDAO();
		String responseMessage = "";
		
		try {
			if(!UserService.isExistingUser(userObj)) {
				return Response.status(200).entity("Invalid User Name or Password").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpSession session= request.getSession(true);
		session.setAttribute("username", username);
		
	    java.net.URI location = new java.net.URI("../jsp/userpage.jsp");
	    return Response.temporaryRedirect(location).build();
	    		
		

	}
}

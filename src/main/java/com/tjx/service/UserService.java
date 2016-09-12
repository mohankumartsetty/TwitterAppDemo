package com.tjx.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.tjx.dao.UserDAO;
import com.tjx.dto.User;

public class UserService {

	/**
	 * check if user is valid
	 */ 
	public static boolean isExistingUser(User currentUserObj) throws IOException
	{
		UserDAO userDao = new UserDAO();
		List<User> currentUsers = userDao.getAllUsers();
		Iterator<User> usersItr = currentUsers.iterator();
		while(usersItr.hasNext())
		{
			User userObj = usersItr.next();
			
			if(userObj.equals(currentUserObj)) 
				return true;
		}
		
		return false;
	}
}

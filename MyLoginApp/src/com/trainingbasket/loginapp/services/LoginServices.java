package com.trainingbasket.loginapp.services;


import java.util.Map;

import com.trainingbasket.loginapp.beans.User;
import com.trainingbasket.loginapp.dao.DataAccess;

public class LoginServices {
	
	DataAccess access=null;
	public LoginServices() {
		access = new DataAccess();
		
	}

	public boolean authenticateUser(String userName, String password) {
		Map<String,User> users =access.getAllUsers();
		User user= users.get(userName);
		if(user.getName().equals(userName)&&user.getPassword().equals(password)) {
		return true;	
		}
		return false;
	}

}

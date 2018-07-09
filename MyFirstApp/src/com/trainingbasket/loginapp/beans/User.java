package com.trainingbasket.loginapp.beans;

public class User {
	         int UserId;
		     String Name;
		     String Password;
		     String SecurityGroup;
			public int getUserId() {
				return UserId;
			}
			public void setUserId(int userId) {
				UserId = userId;
			}
			public String getName() {
				return Name;
			}
			public void setName(String name) {
				Name = name;
			}
			public String getPassword() {
				return Password;
			}
			public void setPassword(String password) {
				Password = password;
			}
			public String getSecurityGroup() {
				return SecurityGroup;
			}
			public void setSecurityGroup(String securityGroup) {
				SecurityGroup = securityGroup;
			}

}

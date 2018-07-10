package com.trainingbasket.loginapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trainingbasket.loginapp.beans.User;
import com.trainingbasket.changepassword.ChangePassword;

public class DataAccess {

	java.util.Map<String, User> userMap = null;

	String connectionUrl = "jdbc:sqlserver://localhost:1900;" + "databaseName=SummerTraining;user=sa;password=123;";
	Connection con = null;// to build a connection
	Statement stmt = null;// to issue SQL statement
	ResultSet rs = null;// after issuance and execution of SQL statement we will get the result set
	// in read only and forward only form
	List<User> users = null;

	public DataAccess() {

		try {
			userMap = new HashMap<>();
			users = new ArrayList<User>();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = ObjectFactory.getDefaultConnection();

			System.out.println();
			System.out.println("Connection established successfully.");

			// Create and execute an SQL statement that returns some data.
			String SQL = "SELECT * FROM [Users]";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, User> getAllUsers() {
		try {
			String SQL = "SELECT * FROM [Users]";
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL);

			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("UserId"));
				user.setName(rs.getString("Name"));
				user.setPassword(rs.getString("Password"));
				user.setSecurityGroup(rs.getString("SecurityGroup"));
				// users.add(user);
				userMap.put(user.getName(), user);

			}
		}

		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * finally { try { //con.close(); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 */
		return userMap;
	}

	public int saveUser(User user) {
		int status = 0;
		try {
			/*String SQLInsert = "INSERT INTO [dbo].[Users]([Name],[Password],[SecurityGroup]) VALUES ('" + user.getName()
					+ "','" + user.getPassword() + "','" + user.getSecurityGroup() + "')";
			stmt = con.createStatement();
			status = stmt.executeUpdate(SQLInsert);
		*/
                        PreparedStatement stmt = con.prepareStatement("insert into [dbo].[Users] values(?,?,?)");
                stmt.setString(1,"Mukesh");
                stmt.setString(2,"125");
                stmt.setString(3,"user");
               status = stmt.executeUpdate();
                

                } catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}
        public int deleteUser(User user) {
		int status = 0;
		try {
			/*String SQLDelete = "DELETE FROM [dbo].[Users]([Name],[Password],[SecurityGroup]) VALUES ('" + user.getName()
					+ "','" + user.getPassword() + "','" + user.getSecurityGroup() + "')";
			stmt = con.createStatement();
			status = stmt.executeUpdate(SQLDelete);
		*/
                PreparedStatement stmt = con.prepareStatement("delete from [dbo].[Users] where UserId=17");
               status = stmt.executeUpdate();
                
                } catch (Exception e) {
			e.printStackTrace();
		}
		return status;
        }
                public int UpdateUser(User user) {
                    /*
                    String new_password=fldnewpassword.getText();
                    String Name=fldusername.getText();
                    String old_password=fldoldpassword.getText();*/
		int status = 0;
		try {
			/*String SQLDelete = "DELETE FROM [dbo].[Users]([Name],[Password],[SecurityGroup]) VALUES ('" + user.getName()
					+ "','" + user.getPassword() + "','" + user.getSecurityGroup() + "')";
			stmt = con.createStatement();
			status = stmt.executeUpdate(SQLDelete);
		*/
                /*PreparedStatement stmt = con.prepareStatement("update [dbo].[Users] set Password=? WHERE Name=? and Password =?");
                stmt.setString(1,new_password);
                stmt.setString(2,Name);
                stmt.setString(3,old_password);
               status = stmt.executeUpdate();
               if(status>0){
                    
		    	  System.out.println("password successfully changed");
		      }
		      else
		      {
		       System.out.println("unable to change password, provide right credential");
		      }*/
                } catch (Exception e) {
			e.printStackTrace();
		}
		return status;
        }


        
	public static void main(String []args) {
		User se = new User();
		System.err.println(new DataAccess().deleteUser(se));

	}
}

package com.trainingbasket.loginapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ObjectFactory {
	public ObjectFactory() {
		
	}
	static String connectionUrl="jdbc:sqlserver://localhost:1900;"+
		       "databaseName=SummerTraining;user=sa;password=123;";
	static ObjectFactory objectFactory=null;
	static Connection connection =null;
	public static ObjectFactory getInstance() {
		if(objectFactory==null) {
			objectFactory = new ObjectFactory();
			
		}
		return objectFactory;
	}
	public static Connection getDefaultConnection() {
		try{
			if(connection==null) {
				connection=DriverManager.getConnection(connectionUrl);
			}
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return connection;
		}
		
	}




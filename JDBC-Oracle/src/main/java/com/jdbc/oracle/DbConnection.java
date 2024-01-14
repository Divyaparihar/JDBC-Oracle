package com.jdbc.oracle;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	private static Connection con;
	
	public static Connection establishOracleConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "db6534", "db6534");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static void main(String[] args) {
		
		Connection connection = DbConnection.establishOracleConnection();
		if(connection != null) {
			System.out.println("Connected.....");
		}else {
			System.out.println("Not Connected......");
		}
	}
	

}

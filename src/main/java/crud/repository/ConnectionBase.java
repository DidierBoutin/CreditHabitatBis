package crud.repository;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class ConnectionBase  {

	private final static String DRIVERJDBC = "org.h2.Driver";
	private final static String URL = "jdbc:h2:file:~/test;AUTO_SERVER=TRUE";
	private final static String USER  = "sa";
	private final static String PIN  = "";
	private static Connection conn;
	
 	
	public static Connection getInstance()  {
		
		// j'arrive pas à faire de with ressource
		try  {
			System.out.println("class : JDBC");
			Class.forName(DRIVERJDBC);
		}		
		catch(ClassNotFoundException e) {
				System.out.println("DriverJDBC not Found : " + e);
				return null;
			}
		
		conn = null;
		
		try {
			System.out.println("getConnection");

 			conn = DriverManager.getConnection(URL, USER, PIN);
		}
		catch (SQLException e) {
			System.out.println("Connection KO : " + e);

		}
		
		if (conn == null) {
 
			System.out.println("Failed to make connection");
			return null;
		}
		else {			
				System.out.println("return conn");
				return conn;}
	}

}


package com.healtcare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * used for database connection
 * @author Pavankunj
 *
 */
public class clsConnection {

	static final String DB_URL = "jdbc:mysql://localhost:3306/healthcare";
	static final String USER_NAME = "root";
	static final String PASSWORD = "jumanji@2";

	
	public Connection setConnection(Connection conn)

	{
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

		} catch (SQLException e) {
			System.err.println("SQl Exception");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("\nAnother Error");
			e.printStackTrace();
		}
		return conn;
	}

}

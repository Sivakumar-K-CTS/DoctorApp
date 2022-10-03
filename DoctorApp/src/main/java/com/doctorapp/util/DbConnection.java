package com.doctorapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	static Connection connection;

	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/doctordb";
		String username = "root";
		String password = "root";

		try {
			// Class.forName("com.mysql.cj.jdbc.Driver");<since we added the driver to the
			// project directly>
			connection = DriverManager.getConnection(url, username, password);

			/*
			 * statement = connection.createStatement(); statement.
			 * execute("Create table doctor(Name varchar(30), Id int primary key auto_increment, Speciality varchar(30), Fees double, Experience int, startTime timestamp, endTime timestamp)"
			 * );
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;

	}

	public static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


}

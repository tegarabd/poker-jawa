package com.example.pokerjawa.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static Connection conn = null;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (conn == null) {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/poker_jawa",
					"root", "");

		}

		return conn;
	}

	public static Connection closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			conn = null;
		}
		return null;
	}

}

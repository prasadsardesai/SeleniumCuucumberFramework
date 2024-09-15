package com.qa.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * DatabaseUtility class to manage database connections and operations.
 */
public class DatabaseUtil {

	private Connection connection; // JDBC Connection
	private String databaseConfigPath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\config\\database.properties";
	Properties prop;

	/**
	 * Constructor to initialize the database connection.
	 * 
	 * @throws SQLException if a database access error occurs.
	 */
	public DatabaseUtil() throws SQLException {

		ConfigReader configReader = new ConfigReader();
		prop = configReader.init_prop(databaseConfigPath);

		String url = prop.getProperty("db.url");
		String username = prop.getProperty("db.username");
		String password = prop.getProperty("db.password");
		this.connection = DriverManager.getConnection(url, username, password);
	}

	/**
	 * Get a single value from the database.
	 * 
	 * @param query The SQL query to execute.
	 * @return The value from the first row and first column.
	 * @throws SQLException if a database access error occurs.
	 */
	public Object getSingleValue(String query) {
		Object value = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				value = resultSet.getObject(1); // Get the first column value
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to retrieve single value: " + e.getMessage());
		}
		return value;
	}

	/**
	 * Get a single string value from the database.
	 * 
	 * @param query The SQL query to execute.
	 * @return The string value from the first row and first column.
	 * @throws SQLException if a database access error occurs.
	 */
	public String getSingleString(String query) {
		String value = null;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				value = resultSet.getString(1); // Get the first column as string
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to retrieve single string: " + e.getMessage());
		}
		return value;
	}

	/**
	 * Get a single row from the database as a Map.
	 * 
	 * @param query The SQL query to execute.
	 * @return A Map representing the first row with column names as keys.
	 * @throws SQLException if a database access error occurs.
	 */
	public Map<String, Object> getSingleRow(String query) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			if (resultSet.next()) { // Get the first row
				for (int i = 1; i <= columnCount; i++) {
					result.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to retrieve single row: " + e.getMessage());
		}
		return result;
	}

	/**
	 * Get a list of values for a specific column.
	 * 
	 * @param query      The SQL query to execute.
	 * @param columnName The name of the column to retrieve values from.
	 * @return List of values from the specified column.
	 * @throws SQLException if a database access error occurs.
	 */
	public List<Object> getColumnValues(String query, String columnName) {
		List<Object> values = new ArrayList<Object>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				values.add(resultSet.getObject(columnName)); // Get values from the specified column
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to retrieve column values: " + e.getMessage());
		}
		return values;
	}

	/**
	 * Execute a query and return results as a List of Maps.
	 * 
	 * @param query The SQL query to execute.
	 * @return List of maps, where each map represents a row with column names as
	 *         keys.
	 * @throws SQLException if a database access error occurs.
	 */
	public List<Map<String, Object>> executeQuery(String query) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (resultSet.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					row.put(metaData.getColumnName(i), resultSet.getObject(i));
				}
				results.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to execute query: " + e.getMessage());
		}
		return results;
	}

	/**
	 * Close the database connection.
	 * 
	 * @throws SQLException if a database access error occurs.
	 */
	public void close() {
		if (connection != null) {
			try {
				if (!connection.isClosed()) {
					connection.close();
					System.out.println("Database connection closed.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to close the database connection: " + e.getMessage());
			}
		}
	}
}

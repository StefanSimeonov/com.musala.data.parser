package com.musala.database.web.parser.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

/**
 * This class is responsible for handle all the exceptions occurred during the
 * working process and rethrow them with a proper exceptions message
 *
 */
public class ObjectValidator {

	private static final String SCHOOLCLASS_AS_STRING = "schoolclasses";

	/**
	 * Check the ResultSet cursor's proper next action
	 * 
	 * @param result
	 * @param message
	 * @return ResultSet
	 * @throws SQLException
	 */
	public static ResultSet checkAndMoveCursorToNextPosition(ResultSet result, String message) throws SQLException {
		try {
			result.next();
		} catch (SQLException e) {
			throw new SQLException(message, e);
		}

		return result;
	}

	/**
	 * Check for database driver existence
	 * 
	 * @param driverName
	 * @throws ClassNotFoundException
	 */
	public static void checkForDatabaseDrivers(String driverName) throws ClassNotFoundException {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Wrong database drivers", e);
		}
	}

	/**
	 * Check for InputMissmatch exception
	 * 
	 * @param matchedObj
	 * @param matchingObj
	 * @param message
	 * @return Object
	 * @throws InputMismatchException
	 */
	public static Object checkForInputMissmatch(Object matchedObj, Object matchingObj, String message)
			throws InputMismatchException {
		try {
			matchedObj = matchingObj;
		} catch (InputMismatchException ex) {
			throw new InputMismatchException(message);
		}

		return matchedObj;
	}

	/**
	 * Check whether getting a result from a database table with given name is
	 * feasible
	 * 
	 * @param result
	 * @param propertyName
	 * @param message
	 * @return String
	 * @throws SQLException
	 */
	public static String checkForPropertyNameValidaty(ResultSet result, String propertyName, String message)
			throws SQLException {
		String queryResult = null;
		try {
			queryResult = result.getString(propertyName);
		} catch (SQLException e) {
			throw new SQLException(message + " " + e.getMessage(), e);
		}

		return queryResult;
	}

	/**
	 * Check whether object is equal to schoolclass
	 * 
	 * @param object
	 * @param message
	 * @throws SchoolClassException
	 */
	public static void checkForSchoolClassException(Object object, String message) throws SchoolClassException {
		if (object.equals(SCHOOLCLASS_AS_STRING))
			throw new SchoolClassException(message);
	}

	/**
	 * Execute "get" query from a database and retrieve the result as an
	 * ResultSet object
	 * 
	 * @param query
	 * @param statement
	 * @return ResultSet
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public static ResultSet checkForSQLQuery(String query, Statement statement)
			throws SQLException, NullPointerException {
		ResultSet result = null;
		try {
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			throw new SQLException("Invalid Query . " + e.getMessage(), e);
		}
		checkIfObjectIsNull(result, "Empty resultSet");

		return result;
	}

	/**
	 * Realize a connection to DB server and return the connection itself or
	 * null if it'snt finded
	 * 
	 * @param server
	 * @param message
	 * @param userName
	 * @param password
	 * @return Connection
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public static Connection checkForSQLConnectionException(String server, String message, String userName,
			String password) throws SQLException, NullPointerException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(server, userName, password);
		} catch (SQLException e) {
			throw new SQLException(message, e);
		}
		checkIfObjectIsNull(connection, "Empty connection");

		return connection;
	}

	/**
	 * Check for statement validity from a brought connection
	 * 
	 * @param connection
	 * @param message
	 * @return Statement
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public static Statement checkForSQLStatementException(Connection connection, String message)
			throws SQLException, NullPointerException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			throw new SQLException(message, e);
		}
		checkIfObjectIsNull(statement, "Empty statement");

		return statement;
	}

	/**
	 * Check if the object is null and retrieve Nullpointer Exception with
	 * givven message
	 * 
	 * @param object
	 * @param message
	 * @return Object
	 */
	public static Object checkIfObjectIsNull(Object object, String message) {
		if (object == null) {
			throw new NullPointerException(message);
		}

		return object;
	}
}

package com.musala.database.web.parser.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

import com.musala.database.web.parser.helper.SchoolClassException;

/**
 * This class is responsible for handle all the exceptions occurred during the
 * working process and rethrow them with a proper exceptions message
 *
 */
public class ObjectValidator {

	private static final String SCHOOLCLASS_AS_STRING = "schoolclasses";

	public static Object checkIfObjectIsNull(Object object, String message) {
		if (object == null) {
			throw new NullPointerException(message);
		}

		return object;
	}

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

	public static void checkForDatabaseDrivers(String driverName) throws ClassNotFoundException {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Wrong database drivers", e);
		}
	}

	public static ResultSet checkAndMoveCursorToNextPosition(ResultSet result, String message) throws SQLException {
		try {
			result.next();
		} catch (SQLException e) {
			throw new SQLException(message, e);
		}

		return result;
	}

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

	public static Object checkForInputMissmatch(Object matchedObj, Object matchingObj, String message)
			throws InputMismatchException {
		try {
			matchedObj = matchingObj;
		} catch (InputMismatchException ex) {
			throw new InputMismatchException(message);
		}

		return matchedObj;
	}

	public static void checkForSchoolClassException(Object object, String message) throws SchoolClassException {
		if (object.equals(SCHOOLCLASS_AS_STRING))
			throw new SchoolClassException(message);
	}
}

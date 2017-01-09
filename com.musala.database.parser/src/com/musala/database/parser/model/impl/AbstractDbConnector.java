package com.musala.database.parser.model.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.musala.database.parser.helper.ObjectValidator;
import com.musala.database.parser.model.IDbConnector;

/**
 * Doesn't implement the actual connection string. Lets the extending classes do
 * this job.
 *
 */
public abstract class AbstractDbConnector implements IDbConnector {
	protected String url;
	protected String userName;
	protected String database;
	protected String password;
	protected Statement statement;
	protected Connection connection;

	@Override
	public String getURL() {
		return url;
	}

	@Override
	public String getDatabase() {
		return database;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public String getPassWord() {
		return password;
	}

	@Override
	public Statement getStatement() {

		return statement;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	protected void createStatement() throws SQLException {
		statement = ObjectValidator.checkForSQLStatementException(connection, "Invalid statement creation");
	}

	/**
	 * Create a proper connection string for XAMPP delivered server
	 * 
	 * @param url,
	 *            database, userName, password
	 */
	protected abstract void createConnectionString(String url, String database, String userName, String password)
			throws SQLException;

	/**
	 * Connection building method itself
	 * 
	 * @return MySQLDatabaseConnector
	 */
	protected abstract AbstractDbConnector build(String url, String database) throws SQLException;

	/**
	 * Connection building method itself with credentials
	 * 
	 * @return MySQLDatabaseConnector
	 */
	protected abstract AbstractDbConnector buildWithAdditionalCredentials(String url, String database, String userName,
			String password) throws SQLException;

}

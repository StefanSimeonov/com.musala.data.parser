package com.musala.database.web.asynchronous.parser.model.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.musala.database.web.asynchronous.parser.helper.ObjectValidator;
import com.musala.database.web.asynchronous.parser.model.IDbConnector;

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
	public Connection getConnection() {
		return connection;
	}

	@Override
	public String getDatabase() {
		return database;
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
	public String getURL() {
		return url;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	/**
	 * Accomplish a statement with a database
	 * 
	 * @throws SQLException
	 */
	protected void createStatement() throws SQLException {
		statement = ObjectValidator.checkForSQLStatementException(connection, "Invalid statement creation");
	}
}

package com.musala.database.parser.model.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.musala.database.parser.helper.ObjectValidator;
import com.musala.database.parser.model.IDbConnector;

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


	protected abstract void createConnectionString(String url, String database, String userName, String password)
			throws SQLException;
	
	protected abstract AbstractDbConnector build(String url, String database) throws SQLException;
	protected abstract AbstractDbConnector buildWithAdditionalCredentials(String url, String database, String userName, String password) throws SQLException;

}

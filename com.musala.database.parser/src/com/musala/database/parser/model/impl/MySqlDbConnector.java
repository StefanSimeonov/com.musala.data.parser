package com.musala.database.parser.model.impl;

import java.sql.SQLException;

import com.musala.database.parser.helper.ObjectValidator;

/**
 * Class that hold in itself the connection and statement for executing a query
 * in some upper level of program implementation
 *
 */
public class MySqlDbConnector extends AbstractDbConnector {

	private static final String MYSQL_CONNECTION_STRING_PREFIX = "jdbc:mysql://";

	
	@Override
	protected void createConnectionString(String url, String database, String userName, String password)
			throws SQLException {
		String connectionAsString = MYSQL_CONNECTION_STRING_PREFIX + url + "/" + database;
		connection = ObjectValidator.checkForSQLConnectionException(connectionAsString, "Invalid SQL operation",
				userName, password);
	}

	
	@Override
	public String getConServerName() {
		return MYSQL_CONNECTION_STRING_PREFIX + url + "/" + database;
	}

	
	@Override
	public MySqlDbConnector build(String url, String database) throws SQLException {
		this.url = url;
		this.database = database;
		createConnectionString(url, database, userName, password);
		createStatement();

		return this;
	}


	@Override
	public MySqlDbConnector buildWithAdditionalCredentials(String url, String database, String userName,
			String password) throws SQLException {
		this.url = url;
		this.database = database;
		this.userName = userName;
		this.password = password;
		createConnectionString(url, database, userName, password);
		createStatement();

		return this;
	}

}

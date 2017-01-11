package com.musala.database.web.parser.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.musala.database.web.parser.model.impl.AbstractDbConnector;

/**
 * Requiring the connection and statement in every implementing classes
 *
 */
public interface IDbConnector {

	Connection getConnection();

	Statement getStatement();

	String getURL();

	String getDatabase();

	String getUserName();

	String getPassWord();

	String getConServerName();

	/**
	 * Connection building method itself
	 * 
	 * @return MySQLDatabaseConnector
	 */
	AbstractDbConnector build(String url, String database) throws SQLException;

	/**
	 * Connection building method itself with credentials
	 * 
	 * @return MySQLDatabaseConnector
	 */
	AbstractDbConnector buildWithAdditionalCredentials(String url, String database, String userName, String password)
			throws SQLException;
}

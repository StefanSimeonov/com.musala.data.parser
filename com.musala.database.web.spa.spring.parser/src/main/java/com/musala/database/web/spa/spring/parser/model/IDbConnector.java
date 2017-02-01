package com.musala.database.web.spa.spring.parser.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.musala.database.web.spa.spring.parser.model.impl.AbstractDbConnector;

/**
 * Requiring the connection and statement in every implementing classes
 *
 */
public interface IDbConnector {
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

	/**
	 * Get the all connection string name
	 * 
	 * @return
	 */
	String getConServerName();

	/**
	 * Retrieve the IDbConnector object
	 * 
	 * @return IDbConnector
	 */
	Connection getConnection();

	/**
	 * Return the server's specific database name
	 * 
	 * @return String
	 */
	String getDatabase();

	/**
	 * Return the password for connection to the server as String
	 * 
	 * @return String
	 */
	String getPassWord();

	/**
	 * Retrieve the Statement object
	 * 
	 * @return Statement
	 */
	Statement getStatement();

	/**
	 * Return the server's name
	 * 
	 * @return String
	 */
	String getURL();

	/**
	 * Return the username for connection to the server as String
	 * 
	 * @return String
	 */
	String getUserName();
}

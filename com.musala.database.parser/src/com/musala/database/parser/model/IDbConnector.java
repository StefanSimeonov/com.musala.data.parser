package com.musala.database.parser.model;

import java.sql.Connection;
import java.sql.Statement;

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

}

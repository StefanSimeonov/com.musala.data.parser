package com.musala.database.web.spa.parser.model;

import com.musala.database.web.spa.parser.model.impl.QueryType;

/**
 * For providing the input and communicating to the user
 *
 */
public interface IDbInputProvider {

	/**
	 * close the accomplished connection
	 */
	void close();

	/**
	 * Get the server's connection name and return it as string representation
	 * 
	 * @return String
	 */
	String getConnection();

	/**
	 * Get the server's database and return it as string representation
	 * 
	 * @return String
	 */
	String getDatabase();

	/**
	 * Send a greeting message to the user
	 */
	void getGreeting();

	/**
	 * Get the id from the user and return it as string representation
	 * 
	 * @return String
	 */
	String getNeedableId();

	/**
	 * Get the name from database records in some table and return it as string
	 * representation
	 * 
	 * @return String
	 */
	String getNeedableName();

	/**
	 * Get the server's password and return it as string representation
	 * 
	 * @return String
	 */
	String getPassword();

	/**
	 * Transfer the given int type of query to enum QueryType type return
	 * QueryType
	 */
	QueryType getQueryType();

	/**
	 * Get the table name from some server's database and return it as string
	 * representation
	 * 
	 * @return String
	 */
	String getQueryTableName();

	/**
	 * Get the columns name from table in server's database and return it as
	 * string representation
	 * 
	 * @return String
	 */
	String[] getRecordPropertiesName();

	/**
	 * Get the server's username and return it as string representation
	 * 
	 * @return String
	 */

	String getUserName();

	/**
	 * Visualize the main menu to the user
	 * 
	 */
	void visualizeMainMenu();
}

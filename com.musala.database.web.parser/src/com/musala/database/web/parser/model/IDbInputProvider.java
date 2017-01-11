package com.musala.database.web.parser.model;

import com.musala.database.web.parser.model.impl.QueryType;

/**
 * For providing the input and communicating to the user
 *
 */
public interface IDbInputProvider {

	void visualizeMainMenu();

	String getConnection();

	String getDatabase();

	/**
	 * Transfer the given int type of query to enum QueryType type return
	 * QueryType
	 */
	QueryType getQueryType();

	String getUserName();

	String getPassword();

	String getQueryTableName();

	String[] getRecordPropertiesName();

	String getNeedableId();

	String getNeedableName();

	void getGreeting();

	void close();
	
}

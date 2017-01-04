package com.musala.database.parser.model;

import com.musala.database.parser.model.impl.QueryType;

public interface IDbInputProvider {

	void visualizeMainMenu();

	String getConnection();

	String getDatabase();

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

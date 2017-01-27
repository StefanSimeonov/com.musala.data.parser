package com.musala.database.web.spa.parser.model.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.musala.database.web.spa.parser.helper.StringConstants;
import com.musala.database.web.spa.parser.model.IDbInputProvider;

public class MyWebInputDbProvider implements IDbInputProvider {

	private static final String CLOSE_THE_QUERY = "closeTheQuery";
	private static final String GET_RECORD_BY_NAME = "getRecordByName";
	private static final String GET_RECORD_BY_ID = "getRecordById";
	private static final String GET_ALL_RECORDS = "getAllRecords";
	private JsonObject request;

	public MyWebInputDbProvider(JsonObject request) {
		this.request = request;
	}

	@Override
	public void close() {
	}

	@Override
	public String getConnection() {
		String serverName = request.get(StringConstants.WEB_PARAMS_SERVER_NAME).getAsString();
		return serverName;
	}

	@Override
	public String getDatabase() {
		String databaseName = request.get(StringConstants.WEB_PARAMS_DATABASE_NAME).getAsString();
		return databaseName;
	}

	@Override
	public void getGreeting() {
	}

	@Override
	public String getNeedableId() {
		return request.get(StringConstants.WEB_PARAMS_ID).getAsString();
	}

	@Override
	public String getNeedableName() {
		return request.get(StringConstants.WEB_PARAMS_NAME).getAsString();
	}

	@Override
	public String getPassword() {
		String password = request.get(StringConstants.WEB_PARAMS_PASSWORD).getAsString();
		return password;
	}

	@Override
	public String getQueryTableName() {
		String tableName = request.get(StringConstants.WEB_PARAMS_TABLE_NAME).getAsString();
		return tableName;
	}

	@Override
	public QueryType getQueryType() {
		JsonElement el = request.get(StringConstants.WEB_PARAMS_QUERIESTYPE);
		String query = el.getAsString();
		switch (query) {
		case GET_ALL_RECORDS:
			return QueryType.first;
		case GET_RECORD_BY_ID:
			return QueryType.second;
		case GET_RECORD_BY_NAME:
			return QueryType.third;
		case CLOSE_THE_QUERY:
			return QueryType.fourth;
		default:
			return null;
		}
	}

	@Override
	public String[] getRecordPropertiesName() {
		return request.get(StringConstants.WEB_PARAMS_PROPERTIES).getAsString().split(" ");
	}

	@Override
	public String getUserName() {
		String username = request.get(StringConstants.WEB_PARAMS_USERNAME).getAsString();
		return username;
	}

	@Override
	public void visualizeMainMenu() {
	}
}

package com.musala.database.web.parser.model.impl;

import javax.servlet.http.HttpServletRequest;

import com.musala.database.web.parser.helper.StringConstants;
import com.musala.database.web.parser.model.IDbInputProvider;
import com.sun.javafx.binding.StringConstant;

public class MyWebInputDbProvider implements IDbInputProvider {
	private HttpServletRequest request;
	public MyWebInputDbProvider(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void close() {
	}

	@Override
	public String getConnection() {
		String serverName = request.getParameter(StringConstants.WEB_PARAMS_SERVER_NAME);
		return serverName;
	}

	@Override
	public String getDatabase() {
		String databaseName = request.getParameter(StringConstants.WEB_PARAMS_DATABASE_NAME);
		return databaseName;
	}

	@Override
	public void getGreeting() {
	}

	@Override
	public String getNeedableId() {
		return request.getParameter(StringConstants.WEB_PARAMS_ID);
	}

	@Override
	public String getNeedableName() {
		return request.getParameter(StringConstants.WEB_PARAMS_NAME);
	}

	@Override
	public String getPassword() {
		String password = request.getParameter(StringConstants.WEB_PARAMS_PASSWORD);
		return password;
	}

	@Override
	public String getQueryTableName() {
		String tableName = request.getParameter(StringConstants.WEB_PARAMS_TABLE_NAME);
		return tableName;
	}

	@Override
	public QueryType getQueryType() {
		String query = request.getParameter(StringConstants.WEB_PARAMS_QUERIESTYPE);
		switch (query) {
		case "getAllRecords":
			return QueryType.first;
		case "getRecordById":
			return QueryType.second;
		case "getRecordByName":
			return QueryType.third;
		case "closeTheQuery":
			return QueryType.fourth;
		default:
			return null;
		}
	}

	@Override
	public String[] getRecordPropertiesName() {
		return request.getParameter(StringConstants.WEB_PARAMS_PROPERTIES).split(" ");
	}

	@Override
	public String getUserName() {
		String username = request.getParameter(StringConstants.WEB_PARAMS_USERNAME);
		return username;
	}

	@Override
	public void visualizeMainMenu() {
	}
}

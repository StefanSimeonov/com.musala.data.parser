package com.musala.database.web.parser.model.impl;

import javax.servlet.http.HttpServletRequest;

import com.musala.database.web.parser.model.IDbInputProvider;

public class MyWebInputDbProvider implements IDbInputProvider {
	private HttpServletRequest request;

	public MyWebInputDbProvider(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void visualizeMainMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getConnection() {
		String serverName = request.getParameter("Server name");
		return serverName;
	}

	@Override
	public String getDatabase() {
		String databaseName = request.getParameter("Database name");
		return databaseName;
	}

	@Override
	public QueryType getQueryType() {
		String query = request.getParameter("queriesType");
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
	public String getUserName() {
		String username = request.getParameter("Username");
		return username;
	}

	@Override
	public String getPassword() {
		String password = request.getParameter("Password");
		return password;
	}

	@Override
	public String getQueryTableName() {
		String tableName = request.getParameter("tableName");
		return tableName;
	}

	@Override
	public String[] getRecordPropertiesName() {
		return request.getParameter("properties").split(" ");
	}

	@Override
	public String getNeedableId() {
		return request.getParameter("id");
	}

	@Override
	public String getNeedableName() {
		return request.getParameter("name");
	}

	@Override
	public void getGreeting() {
		// TODO Auto-generated method stub
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}

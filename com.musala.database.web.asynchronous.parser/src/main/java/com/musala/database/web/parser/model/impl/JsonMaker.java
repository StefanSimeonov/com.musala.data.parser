package com.musala.database.web.parser.model.impl;

public class JsonMaker {
	public String convertConnection(String serverName, String databaseName, String username, String password,
			boolean status) {
		String srvName = "\"serverName\":" + "\"" + serverName + "\"";
		String dbName = "\"databaseName\":" + "\"" + databaseName + "\"";
		String userName = "\"username\":" + "\"" + username + "\"";
		String pass = "\"password\":" + "\"" + password + "\"";
		String stats = "\"status\":" + status;
		String jsonFormat = "{" + srvName + "," + dbName + "," + userName + "," + pass + "," + stats + "}";
		
		return jsonFormat;
	}

	public String convertAnswear(String message, boolean status) {
		String msg = "\"message\":" + "\"" + message + "\"";
		String stats = "\"status\":" + status;
		String jsonFormat = "{" + msg + "," + stats + "}";
		
		return jsonFormat;
	}

	public String convertThirdLocationAnswear(String currentTable, String[] currentProperties, QueryType typeOfQuery,
			boolean status) {
		String table = "currentTable=" + currentTable;
		String properties = "currentProperties=" + currentProperties;
		String queryType = "typeOfQuery=" + typeOfQuery;
		String stats = "status=" + status;

		String jsonFormat = table + "&" + properties + "&" + queryType + "&" + stats;
		
		return jsonFormat;
	}
}

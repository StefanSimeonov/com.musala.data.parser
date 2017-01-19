package com.musala.database.web.parser.model.ui;

import java.util.HashMap;

import com.google.gson.Gson;

public class JsonMaker {

	private static final String JSON_OBJ_FIELD_MESSAGE = "message";
	private static final String JSON_OBJ_FIELD_STATUS = "status";
	private static final String JSON_OBJ_FIELD_PASSWORD = "password";
	private static final String JSON_OBJ_FIELD_USERNAME = "username";
	private static final String JSON_OBJ_FIELD_DATABASE_NAME = "databaseName";
	private static final String JSON_OBJ_FIELD_SERVER_NAME = "serverName";
	private static final String JSON_OBJ_TYPE_ANSWER = "answear";
	private static final String JSON_OBJ_TYPE_CONNECTION = "connection";

	public static String build(String typeOfJsonObj, HashMap<String, String> map) {
		Gson gson = new Gson();
		String jsonObj = null;
		
		switch (typeOfJsonObj) {
			case JSON_OBJ_TYPE_CONNECTION: {
				ConnectionStringRepo repo = new ConnectionStringRepo();
				repo.setServerName(map.get(JSON_OBJ_FIELD_SERVER_NAME));
				repo.setDatabaseName(map.get(JSON_OBJ_FIELD_DATABASE_NAME));
				repo.setUsername(map.get(JSON_OBJ_FIELD_USERNAME));
				repo.setPassword(map.get(JSON_OBJ_FIELD_PASSWORD));
				repo.setStatus(Boolean.valueOf(map.get(JSON_OBJ_FIELD_STATUS)));
				jsonObj = gson.toJson(repo);
				
				break;
			}
			case JSON_OBJ_TYPE_ANSWER: {
				AnswearStringRepo repo = new AnswearStringRepo();
				repo.setMessage(map.get(JSON_OBJ_FIELD_MESSAGE));
				repo.setStatus(Boolean.valueOf(map.get(JSON_OBJ_FIELD_STATUS)));
				jsonObj = gson.toJson(repo);
				
				break;
			}
		}
		
		return jsonObj;
	}
}

package com.musala.database.web.spa.spring.parser.model.impl;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.musala.database.web.spa.spring.parser.helper.ObjectValidator;
import com.musala.database.web.spa.spring.parser.helper.SchoolClassException;
import com.musala.database.web.spa.spring.parser.model.ui.JsonMaker;
import com.musala.database.web.spa.spring.parser.requests.RequestConnectionStringRepo;
import com.musala.database.web.spa.spring.parser.requests.RequestQueryStringRepo;

@Component
public class MySqlWebDbEngine extends AbstractDbEngine {
	private static final String QUERY_TYPE_CLOSE_THE_QUERY = "closeTheQuery";
	private static final String QUERY_TYPE_GET_RECORD_BY_NAME = "getRecordByName";
	private static final String QUERY_TYPE_GET_RECORD_BY_ID = "getRecordById";
	private static final String QUERY_TYPE_GET_ALL_RECORDS = "getAllRecords";
	private static final String CONNECTION_DRIVERS_AS_STRING = "com.mysql.jdbc.Driver";
	private HashMap<String, String> repoForJsonCreation = new HashMap<>();
	private static MySqlWebDbEngine instance;
	private RequestConnectionStringRepo connetionRequest;
	private RequestQueryStringRepo queryRequest;
	private final String SQL_ERROR_MESSAGE = "Invalid query";
	public static boolean secondQueryInvoking = false;
	private QueryHolder queryHolder = new QueryHolder();

	private MySqlWebDbEngine(RequestConnectionStringRepo connetionRequest, RequestQueryStringRepo queryRequest) {
		this.connetionRequest = connetionRequest;
		this.queryRequest = queryRequest;
	}

	public static MySqlWebDbEngine getInstanceForConnection(RequestConnectionStringRepo connetionRequest) {
		if (instance == null) {
			instance = new MySqlWebDbEngine(connetionRequest, null);
		} else {
			instance.connetionRequest = connetionRequest;
		}
		return instance;
	}

	public static MySqlWebDbEngine getInstanceForQuering(RequestQueryStringRepo queryRequest) {
		instance.queryRequest = queryRequest;
		return instance;
	}

	public String initialize() {
		try {
			ObjectValidator.checkForDatabaseDrivers(CONNECTION_DRIVERS_AS_STRING);
		} catch (ClassNotFoundException ex) {
			repoForJsonCreation.put("status", "false");
			repoForJsonCreation.put("message", ex.getMessage());
			String json = JsonMaker.build("connection", repoForJsonCreation);
			return json;
		}

		try {
			String serverName = connetionRequest.getServerName();
			String databaseName = connetionRequest.getDatabaseName();
			String userName = connetionRequest.getUsername();
			String password = connetionRequest.getPassword();
			repoForJsonCreation.put("serverName", serverName);
			repoForJsonCreation.put("databaseName", databaseName);
			repoForJsonCreation.put("username", userName);
			repoForJsonCreation.put("password", password);
			connection = new MySqlDbConnector().buildWithAdditionalCredentials(serverName, databaseName, userName,
					password);
			query = new MySqlQueryBuilder();
			writer = new MyDbWebQueryRenderer(connection, query);
			repoForJsonCreation.put("status", "true");
			String json = JsonMaker.build("connection", repoForJsonCreation);
			return json;
		} catch (SQLException sq) {
			repoForJsonCreation.put("status", "false");
			String json = JsonMaker.build("connection", repoForJsonCreation);
			return json;
		}

	}

	public String startQuering() {
		String typeOfQuery;
		String json;
		try {
			if (!secondQueryInvoking) {
				typeOfQuery = queryRequest.getQueriesType();
			} else {
				typeOfQuery = queryHolder.getQueryType();
			}
			if (typeOfQuery.equals(QUERY_TYPE_CLOSE_THE_QUERY)) {
				repoForJsonCreation.put("status", "false");
				repoForJsonCreation.put("message", "Thank you");
				json = JsonMaker.build("answer", repoForJsonCreation);
				return json;
			} else {
				json = executeQuery(typeOfQuery);
			}
		} catch (NullPointerException e) {
			repoForJsonCreation.put("status", "false");
			repoForJsonCreation.put("message", "Enter query type");
			json = JsonMaker.build("answer", repoForJsonCreation);
			return json;
		}
		return json;
	}

	/**
	 * Executes query depending on a given type
	 * 
	 * @param QueryType
	 *            typeOfQuery
	 */
	private String executeQuery(String typeOfQuery) {

		try {
			String currentTable = null;
			String[] currentProperties = null;
			if (!secondQueryInvoking) {
				currentTable = queryRequest.getTableName();
				currentProperties = queryRequest.getProperties().split(" ");
			}
			switch (typeOfQuery) {
			case QUERY_TYPE_GET_ALL_RECORDS: {
				return writer.printAllRecordsInTable(currentTable, currentProperties);
			}
			case QUERY_TYPE_GET_RECORD_BY_ID: {
				if (!secondQueryInvoking) {
					queryHolder.setCurrentProperties(currentProperties);
					queryHolder.setCurrentTable(currentTable);
					queryHolder.setQueryType(typeOfQuery);
					repoForJsonCreation.put("status", "true");
					repoForJsonCreation.put("message", "Succesfull");
					String json = JsonMaker.build("answer", repoForJsonCreation);
					secondQueryInvoking = true;
					return json;
				} else {
					String needablePropertyName = queryRequest.getId();
					String json = writer.printRecordsById(queryHolder.getCurrentTable(), needablePropertyName,
							queryHolder.getCurrentProperties());
					json = json.replaceAll("false", "true");
					secondQueryInvoking = false;
					return json;
				}
			}
			case QUERY_TYPE_GET_RECORD_BY_NAME: {
				if (!secondQueryInvoking) {
					queryHolder.setCurrentProperties(currentProperties);
					queryHolder.setCurrentTable(currentTable);
					queryHolder.setQueryType(typeOfQuery);
					repoForJsonCreation.put("status", "true");
					repoForJsonCreation.put("message", "Succesfull");
					String json = JsonMaker.build("answer", repoForJsonCreation);
					secondQueryInvoking = true;
					return json;
				} else {
					String needablePropertyName = queryRequest.getName();
					String json = writer.printRecordsByName(queryHolder.getCurrentTable(), needablePropertyName,
							queryHolder.getCurrentProperties());
					json = json.replaceAll("false", "true");
					secondQueryInvoking = false;
					return json;
				}

			}
			}
		} catch (SQLException sqlex) {
			repoForJsonCreation.put("status", "false");
			repoForJsonCreation.put("message", SQL_ERROR_MESSAGE);
			String json = JsonMaker.build("answer", repoForJsonCreation);
			secondQueryInvoking = false;
			return json;
		} catch (SchoolClassException sc) {
			repoForJsonCreation.put("status", "false");
			repoForJsonCreation.put("message", sc.getMessage());
			String json = JsonMaker.build("answer", repoForJsonCreation);
			secondQueryInvoking = false;
			return json;
		}
		return null;

	}

}

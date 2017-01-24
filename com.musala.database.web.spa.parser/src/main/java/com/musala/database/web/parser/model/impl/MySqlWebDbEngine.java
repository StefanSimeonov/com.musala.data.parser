package com.musala.database.web.parser.model.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.musala.database.web.parser.helper.ObjectValidator;
import com.musala.database.web.parser.helper.SchoolClassException;
import com.musala.database.web.parser.model.ui.JsonMaker;

/**
 * The engine, where the initial required classes is stored and instanced for
 * the purpose of the program. The class where all cycling process like reading
 * queries from the input starts. SINGLETON PATTERN USAGE
 * 
 *
 */
public class MySqlWebDbEngine extends AbstractDbEngine {

	private static final String CONNECTION_DRIVERS_AS_STRING = "com.mysql.jdbc.Driver";
	private HttpServletResponse response;
	private JsonObject request;
	public static boolean secondQueryInvoking = false;
	private static MySqlWebDbEngine instance;
	private QueryHolder queryHolder = new QueryHolder();
	private HashMap<String, String> repoForJsonCreation = new HashMap<>();

	/**
	 * The constructor for the singleton pattern usage
	 * 
	 * @param response
	 * @param request
	 */
	private MySqlWebDbEngine(HttpServletResponse response, JsonObject request) {
		this.response = response;
		this.request = request;
	}

	public static MySqlWebDbEngine getInstance(HttpServletResponse response, JsonObject request) {
		if (instance == null) {
			instance = new MySqlWebDbEngine(response, request);
			return instance;
		
		} else {
			instance.response = response;
			instance.request = request;
			instance.input = new MyWebInputDbProvider(request);
			return instance;

		}
	}

	@Override
	public void initialize() {
		try {
			ObjectValidator.checkForDatabaseDrivers(CONNECTION_DRIVERS_AS_STRING);
		} catch (ClassNotFoundException ex) {
			try {
				repoForJsonCreation.put("status", "false");
				repoForJsonCreation.put("message", ex.getMessage());
				String json = JsonMaker.build("connection", repoForJsonCreation);
				response.getWriter().print(json);
			} catch (IOException e) {
				// fictive try-catch block for the needs of sendredirect method
				// exception requirement
			}
		}

		input = new MyWebInputDbProvider(request);
		try {
			input.visualizeMainMenu();
			String serverName = input.getConnection();
			String databaseName = input.getDatabase();
			String userName = input.getUserName();
			String password = input.getPassword();
			repoForJsonCreation.put("serverName", serverName);
			repoForJsonCreation.put("databaseName", databaseName);
			repoForJsonCreation.put("username", userName);
			repoForJsonCreation.put("password", password);
			connection = new MySqlDbConnector().buildWithAdditionalCredentials(serverName, databaseName, userName,
					password);
			query = new MySqlQueryBuilder();
			writer = new MyDbWebQueryRenderer(connection, query, response);

			try {
				repoForJsonCreation.put("status", "true");
				String json = JsonMaker.build("connection", repoForJsonCreation);
				response.getWriter().print(json);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException sq) {
			try {
				repoForJsonCreation.put("status", "false");
				String json = JsonMaker.build("connection", repoForJsonCreation);
				response.getWriter().print(json);
			} catch (IOException e) {
				// fictive try-catch block for the needs of sendredirect
				// method
				// exception requirement
			}
		}
	}

	@Override
	public void startQuering() {
		QueryType typeOfQuery;
		try {
			if (!secondQueryInvoking) {
				typeOfQuery = input.getQueryType();
			} else {
				typeOfQuery = queryHolder.getQueryType();
			}
			if (typeOfQuery.getValue() == QueryType.fourth.getValue()) {
				try {
					repoForJsonCreation.put("status", "false");
					repoForJsonCreation.put("message", "Thank you");
					String json = JsonMaker.build("answear", repoForJsonCreation);
					response.getWriter().println(json);
				} catch (IOException e) {
					// fictive
				}
			} else {
				executeQuery(typeOfQuery);
			}
		} catch (NullPointerException e) {
			repoForJsonCreation.put("status", "false");
			repoForJsonCreation.put("message", "Enter query type");
			String json = JsonMaker.build("answear", repoForJsonCreation);
			try {
				response.getWriter().print(json);
			} catch (IOException e1) {
				// fictive
			}
		}
	}

	/**
	 * Executes query depending on a given type
	 * 
	 * @param QueryType
	 *            typeOfQuery
	 */
	private void executeQuery(QueryType typeOfQuery) {

		try {
			String currentTable = null;
			String[] currentProperties = null;
			if (!secondQueryInvoking) {
				currentTable = input.getQueryTableName();
				currentProperties = input.getRecordPropertiesName();
			}
			switch (typeOfQuery.getValue()) {
			case 1: {
				writer.printAllRecordsInTable(currentTable, currentProperties);
				break;
			}
			case 2: {
				if (!secondQueryInvoking) {
					queryHolder.setCurrentProperties(currentProperties);
					queryHolder.setCurrentTable(currentTable);
					queryHolder.setQueryType(typeOfQuery);
					repoForJsonCreation.put("status", "true");
					repoForJsonCreation.put("message", "Succesfull");
					String json = JsonMaker.build("answear", repoForJsonCreation);
					secondQueryInvoking = true;
					try {
						response.getWriter().println(json);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					String needablePropertyName = input.getNeedableId();
					writer.printRecordsById(queryHolder.getCurrentTable(), needablePropertyName,
							queryHolder.getCurrentProperties());
					secondQueryInvoking = false;
				}
				break;
			}
			case 3: {
				if (!secondQueryInvoking) {
					queryHolder.setCurrentProperties(currentProperties);
					queryHolder.setCurrentTable(currentTable);
					queryHolder.setQueryType(typeOfQuery);
					repoForJsonCreation.put("status", "true");
					repoForJsonCreation.put("message", "Succesfull");
					String json = JsonMaker.build("answear", repoForJsonCreation);
					secondQueryInvoking = true;
					try {
						response.getWriter().println(json);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					String needablePropertyName = input.getNeedableName();
					writer.printRecordsByName(queryHolder.getCurrentTable(), needablePropertyName,
							queryHolder.getCurrentProperties());
					secondQueryInvoking = false;
				}
				break;
			}
			}

		} catch (SQLException sqlex) {
			try {
				repoForJsonCreation.put("status", "false");
				repoForJsonCreation.put("message", sqlex.getMessage());
				String json = JsonMaker.build("answear", repoForJsonCreation);
				response.getWriter().println(json);
				secondQueryInvoking = false;
			} catch (IOException e) {
				// fictive
			}
		} catch (SchoolClassException sc) {
			try {
				repoForJsonCreation.put("status", "false");
				repoForJsonCreation.put("message", sc.getMessage());
				String json = JsonMaker.build("answear", repoForJsonCreation);
				response.getWriter().println(json);
				secondQueryInvoking = false;
			} catch (IOException e) {
				// fictive
			}
		}

	}

}

package com.musala.database.web.parser.model.impl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.musala.database.web.parser.helper.ObjectValidator;
import com.musala.database.web.parser.helper.SchoolClassException;

/**
 * The engine, where the initial required classes is stored and instanced for
 * the purpose of the program. The class where all cycling process like reading
 * queries from the input starts. SINGLETON PATTERN USAGE
 * 
 *
 */
public class MySqlWebDbEngine extends AbstractDbEngine {

	private static final String CONNECTION_DRIVERS_AS_STRING = "com.mysql.jdbc.Driver";
	private JsonMaker jsonMaker = new JsonMaker();
	private HttpServletResponse response;
	private HttpServletRequest request;
	public static boolean secondQueryInvoking = false;
	private static MySqlWebDbEngine instance;
	private QueryHolder queryHolder = new QueryHolder();

	/**
	 * The constructor for the singleton pattern usage
	 * 
	 * @param response
	 * @param request
	 */
	private MySqlWebDbEngine(HttpServletResponse response, HttpServletRequest request) {
		this.response = response;
		this.request = request;
	}

	public static MySqlWebDbEngine getInstance(HttpServletResponse response, HttpServletRequest request) {
		if (instance == null) {
			instance = new MySqlWebDbEngine(response, request);
			return instance;

		} else {
			instance.response = response;
			instance.request = request;
			return instance;

		}
	}

	@Override
	public void initialize() {
		try {
			ObjectValidator.checkForDatabaseDrivers(CONNECTION_DRIVERS_AS_STRING);
		} catch (ClassNotFoundException ex) {
			try {
				String j = jsonMaker.convertConnection(null, null, null, null, false);
				response.getWriter().print(j);
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
			connection = new MySqlDbConnector().buildWithAdditionalCredentials(serverName, databaseName, userName,
					password);
			query = new MySqlQueryBuilder();
			this.writer = new MyDbWebQueryRenderer(connection, query, response);

			try {
				String respMessage = jsonMaker.convertConnection(serverName, databaseName, userName, password, true);
				response.getWriter().print(respMessage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException sq) {
			try {
				String respMessage = jsonMaker.convertConnection(null, null, null, null, false);
				response.getWriter().print(respMessage);
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
		if (! secondQueryInvoking) {
			typeOfQuery = input.getQueryType();
		} else {
			typeOfQuery = queryHolder.getQueryType();
		}
		if (typeOfQuery.getValue() == QueryType.fourth.getValue()) {
			try {
				String respMessage = jsonMaker.convertAnswear("Thank you", false);
				response.getWriter().println(respMessage);
			} catch (IOException e) {
				// fictive
			}
		} else {
			executeQuery(typeOfQuery);
		}
	}

	/**
	 * Executes query depending on
	 * a given type
	 * 
	 * @param QueryType typeOfQuery
	 */
	private void executeQuery(QueryType typeOfQuery) {

		try {
			String currentTable = null;
			String[] currentProperties = null;
			if (! secondQueryInvoking) {
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
						String respMessage = jsonMaker.convertAnswear("succesfull", true);
						secondQueryInvoking = true;
						try {
							response.getWriter().println(respMessage);
						} catch (IOException e) {
							// fictive
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
						String respMessage = jsonMaker.convertAnswear("succesfull", true);
						secondQueryInvoking = true;
						try {
							response.getWriter().println(respMessage);
						} catch (IOException e) {
							// fictive
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
				String respMessage = jsonMaker.convertAnswear(sqlex.getMessage(), false);
				response.getWriter().println(respMessage);
				secondQueryInvoking = false;
			} catch (IOException e) {
				// fictive
			}
		} catch (SchoolClassException sc) {
			try {
				String respMessage = jsonMaker.convertAnswear(sc.getMessage(), false);
				response.getWriter().println(respMessage);
				secondQueryInvoking = false;
			} catch (IOException e) {
				// fictive
			}
		}

	}

}

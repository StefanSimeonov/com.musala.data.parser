package com.musala.database.web.parser.model.impl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.musala.database.web.parser.helper.ObjectValidator;
import com.musala.database.web.parser.helper.RedirectURLBuilder;
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
	private static final String REDIRECT_WEB_PAGE = RedirectURLBuilder.getPath();

	private HttpServletResponse response;
	private HttpServletRequest request;
	public static boolean secondQueryInvoking = false;
	private static MySqlWebDbEngine instance;

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
				response.sendRedirect(REDIRECT_WEB_PAGE + ex.getMessage());
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
		} catch (SQLException sq) {
			try {
				response.sendRedirect(REDIRECT_WEB_PAGE + sq.getMessage());
			} catch (IOException e) {
				// fictive try-catch block for the needs of sendredirect
				// method
				// exception requirement
			}
		}
	}

	@Override
	public void startQuering() {
		QueryType typeOfQuery = input.getQueryType();
		if (typeOfQuery.getValue() == QueryType.fourth.getValue()) {
			try {
				response.getWriter().println("<p>Thank you. Have a nice day.</p>");
			} catch (IOException e) {
				// fictive
			}
		} else {
			executeQuery(typeOfQuery);
		}
	}

	private void executeQuery(QueryType typeOfQuery) {

		try {
			String currentTable = input.getQueryTableName();
			String[] currentProperties = input.getRecordPropertiesName();
			switch (typeOfQuery.getValue()) {
			case 1: {
				writer.printAllRecordsInTable(currentTable, currentProperties);
				break;
			}
			case 2: {
				if (secondQueryInvoking == false) {
					fillTheResponse(currentTable, currentProperties, typeOfQuery, response);
					secondQueryInvoking = true;
				} else {
					String needablePropertyName = input.getNeedableId();
					writer.printRecordsById(currentTable, needablePropertyName, currentProperties);
				}
				break;
			}
			case 3: {
				if (secondQueryInvoking == false) {
					fillTheResponse(currentTable, currentProperties, typeOfQuery, response);
					secondQueryInvoking = true;

				} else {
					String needablePropertyName = input.getNeedableName();
					writer.printRecordsByName(currentTable, needablePropertyName, currentProperties);
				}
				break;
			}
			}

		} catch (SQLException sqlex) {
			try {
				response.sendRedirect(REDIRECT_WEB_PAGE + sqlex.getMessage());
				secondQueryInvoking = false;
			} catch (IOException e) {
				// fictive
			}
		} catch (SchoolClassException sc) {
			try {
				response.sendRedirect(REDIRECT_WEB_PAGE + sc.getMessage());
			} catch (IOException e) {
				// fictive
			}
		}

	}

	/**
	 * Switch between every query type and execute the exact query commands.
	 * Handle all the occurred exceptions occurred meanwhile and don't pass
	 * while proper input isn't given
	 * 
	 * @param typeOfQuery-there
	 *            are currently 4 types of queries
	 */

	private void fillTheResponse(String currentTable, String[] currentProperties, QueryType typeOfQuery,
			HttpServletResponse response2) {
		String temp = "";
		for (int i = 0; i < currentProperties.length; i++) {
			temp += currentProperties[i] + " ";
		}
		String currentTableName = searchForTableName(currentTable);
		String typeOfQueryName = searchForQueryTypeName(typeOfQuery);
		try {
			response.getWriter().println(
					"<form action='ThirdGetter'><p>Enter the id you want:<br><input type='text' name='id'><p>Enter the name you want:</p><input type='text' name='name'>"
							+ typeOfQueryName + currentTableName
							+ " </p><input type='text' name='properties' style='display:none' value='" + temp
							+ "'><br><br><input type='submit'></form>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/** Build static html depending on query type
	 * @param typeOfQuery
	 * @return String
	 */
	private String searchForQueryTypeName(QueryType typeOfQuery) {
		switch (typeOfQuery.getValue()) {
		case 2:
			return "</p><select name='queriesType' style='display:none'><option value='getAllRecords'>getAllRecords</option><option value='getRecordById' selected>getRecordById</option><option value='getRecordByName' >getRecordByName</option><option value='closeTheQuery'>close</option></select><p>";
		case 3:
			return "</p><select name='queriesType' style='display:none'><option value='getAllRecords'>getAllRecords</option><option value='getRecordById'>getRecordById</option><option value='getRecordByName' selected>getRecordByName</option><option value='closeTheQuery'>close</option></select><p>";
		}
		return null;
	}

	/** Build static html depending on table name
	 * @param currentTable
	 * @return String
	 */
	private String searchForTableName(String currentTable) {
		switch (currentTable) {
		case "schools":
			return "</p><select name='tableName' style='display:none'><option value='schools' selected >schools</option><option value='schoolclasses'>school classes</option><option value='students' >students</option><option value='teachers' >teachers</option></select>";
		case "schoolclasses":
			return "</p><select name='tableName' style='display:none'><option value='schools'>schools</option><option value='schoolclasses' selected >school classes</option><option value='students' >students</option><option value='teachers' >teachers</option></select>";
		case "students":
			return "</p><select name='tableName' style='display:none'><option value='schools' >schools</option><option value='schoolclasses'>school classes</option><option value='students' selected >students</option><option value='teachers' >teachers</option></select>";
		case "teachers":
			return "</p><select name='tableName' style='display:none'><option value='schools' >schools</option><option value='schoolclasses'>school classes</option><option value='students' >students</option><option value='teachers' selected >teachers</option></select>";
		}
		return null;
	}
}

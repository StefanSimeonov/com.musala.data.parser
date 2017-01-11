package com.musala.database.web.parser.model.impl;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.InputMismatchException;

import com.musala.database.web.parser.model.impl.AbstractDbEngine;
import com.musala.database.web.parser.model.impl.MyConsoleInputDbProvider;
import com.musala.database.web.parser.model.impl.MyDbConsoleRenderer;
import com.musala.database.web.parser.model.impl.MySqlDbConnector;
import com.musala.database.web.parser.model.impl.MySqlQueryBuilder;
import com.musala.database.web.parser.model.impl.QueryType;
import com.musala.database.web.parser.helper.ObjectValidator;
import com.musala.database.web.parser.helper.SchoolClassException;

/**
 * The engine, where the initial required classes is stored and instanced for
 * the purpose of the program. The class where all cycling process like reading
 * queries from the input starts.
 * 
 *
 */
public class MySqlDbEngine extends AbstractDbEngine {

	private boolean hasError = false;
	private final String connectionDriversAsString = "com.mysql.jdbc.Driver";

	@Override
	public void initialize() {
		errorWriter = new PrintWriter(System.out);

		try {
			ObjectValidator.checkForDatabaseDrivers(connectionDriversAsString);
		} catch (ClassNotFoundException ex) {
			errorWriter.println(ex.getMessage());
			errorWriter.flush();
		}

		input = new MyConsoleInputDbProvider();
		hasError = true;
		while (hasError) {
			try {
				input.visualizeMainMenu();
				String serverName = input.getConnection();
				String databaseName = input.getDatabase();
				String userName = input.getUserName();
				String password = input.getPassword();
				connection = new MySqlDbConnector().buildWithAdditionalCredentials(serverName, databaseName, userName,
						password);
				query = new MySqlQueryBuilder();
				this.writer = new MyDbConsoleRenderer(connection, query);
				hasError = false;
			} catch (SQLException sq) {
				errorWriter.println(sq.getMessage());
				errorWriter.flush();
			}
		}

	}

	@Override
	public void startQuering() {
		hasError = true;
		boolean stillReading = true;
		while (stillReading) { // cycling while typeOfQuery==4 - close statement
			QueryType typeOfQuery = typeOfQueryChoosing();
			if (typeOfQuery.getValue() == QueryType.fourth.getValue()) {
				input.getGreeting();
				stillReading = false;
				break;
			}
			executeQuery(typeOfQuery);
		}
		try {
			CloseAllConnections();
		} catch (SQLException e) {
			errorWriter.println(e);
			errorWriter.flush();
		}
	}

	private void CloseAllConnections() throws SQLException {
		connection.getConnection().close();
		connection.getStatement().close();
		input.close();
		errorWriter.close();

	}

	/**
	 * Switch between every query type and execute the exact query commands.
	 * Handle all the occurred exceptions occurred meanwhile and don't pass
	 * while proper input isn't given
	 * 
	 * @param typeOfQuery-there
	 *            are currently 4 types of queries
	 */
	private void executeQuery(QueryType typeOfQuery) {
		hasError = true;
		while (hasError) {
			try {
				String currentTable = input.getQueryTableName();
				String[] currentProperties = input.getRecordPropertiesName();
				switch (typeOfQuery.getValue()) {
				case 1: {
					writer.printAllRecordsInTable(currentTable, currentProperties);
					hasError = false;
					break;
				}
				case 2: {
					String needablePropertyName = input.getNeedableId();
					writer.printRecordsById(currentTable, needablePropertyName, currentProperties);
					hasError = false;
					break;
				}
				case 3: {
					String needablePropertyName = input.getNeedableName();
					writer.printRecordsByName(currentTable, needablePropertyName, currentProperties);
					hasError = false;
					break;
				}
				}
			} catch (SQLException sqlex) {
				errorWriter.println(sqlex.getMessage());
				errorWriter.flush();
			} catch (SchoolClassException sc) {
				errorWriter.println(sc.getMessage());
				errorWriter.flush();
			}
		}
	}

	/**
	 * Get the type of each one query while the type is correct. Handle all the
	 * occurred exceptions occurred meanwhile and don't pass while proper input
	 * isn't given
	 * 
	 * @return QueryType
	 */
	private QueryType typeOfQueryChoosing() {
		hasError = true;
		QueryType typeOfQuery = null;
		while (hasError) {
			try {
				typeOfQuery = (QueryType) ObjectValidator.checkForInputMissmatch(typeOfQuery, input.getQueryType(),
						"Please enter a number");
				ObjectValidator.checkIfObjectIsNull(typeOfQuery, "Wrong type of Query.");
				hasError = false;
			} catch (NullPointerException e) {
				errorWriter.println(e.getMessage());
				errorWriter.flush();
			} catch (InputMismatchException e) {
				errorWriter.println(e.getMessage());
				errorWriter.flush();
				
			}

		}
		return typeOfQuery;
	}
}

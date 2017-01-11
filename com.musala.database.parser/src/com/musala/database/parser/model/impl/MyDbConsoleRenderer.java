package com.musala.database.parser.model.impl;

import java.sql.SQLException;

import com.musala.database.parser.helper.ObjectValidator;
import com.musala.database.parser.helper.SchoolClassException;
import com.musala.database.parser.model.IDbConnector;
import com.musala.database.parser.model.IQueryable;

/**
 * This class takes responsibility to print all the queries getting from
 * IQueryable object
 *
 */
public class MyDbConsoleRenderer extends AbstractDbQueryWriter {

	public MyDbConsoleRenderer(IDbConnector dbconnector, IQueryable query) {
		super(dbconnector, query);
	}

	@Override
	public void printAllRecordsInTable(String dbTable, String... columnNames)
			throws NullPointerException, SQLException {
		result = query.getAllRecords(dbTable, statement);
		resultSetPrinting(columnNames);
	}

	@Override
	public void printRecordsById(String dbTable, String id, String... columnNames)
			throws NullPointerException, SQLException {
		result = query.getRecordById(dbTable, statement, id);
		resultSetPrinting(columnNames);
	}

	@Override
	public void printRecordsByName(String dbTable, String name, String... columnNames)
			throws NullPointerException, SQLException, SchoolClassException {
		ObjectValidator.checkForSchoolClassException(dbTable, "School classes dont have name property");
		result = query.getRecordByName(dbTable, statement, name);
		resultSetPrinting(columnNames);
	}

	/**
	 * Iterate trough all table's rows
	 * 
	 * @param columnNames
	 * @throws NullPointerException
	 * @throws SQLException
	 */
	protected void resultSetPrinting(String... columnNames) throws NullPointerException, SQLException {
		ObjectValidator.checkIfObjectIsNull(result, "Wrong property name or database table");
		result = ObjectValidator.checkAndMoveCursorToNextPosition(result, "Wrong resultset cursor translation");
		printTableRow(columnNames);
		while (result.next()) {
			printTableRow(columnNames);
		}
	}

	/**
	 * Getting the all results from a current table row
	 * 
	 * @param columnNames
	 * @throws SQLException
	 */
	protected void printTableRow(String[] columnNames) throws SQLException {
		for (String currentColumnName : columnNames) {
			String queryResult = ObjectValidator.checkForPropertyNameValidaty(result, currentColumnName,
					"Wrong table's column name");
			System.out.println(queryResult);
		}
	}

}

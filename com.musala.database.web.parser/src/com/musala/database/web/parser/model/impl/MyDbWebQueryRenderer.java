package com.musala.database.web.parser.model.impl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.musala.database.web.parser.helper.ObjectValidator;
import com.musala.database.web.parser.helper.SchoolClassException;
import com.musala.database.web.parser.model.IDbConnector;
import com.musala.database.web.parser.model.IQueryable;

public class MyDbWebQueryRenderer extends AbstractDbQueryWriter {

	HttpServletResponse response;

	public MyDbWebQueryRenderer(IDbConnector dbconnector, IQueryable query, HttpServletResponse response) {
		super(dbconnector, query);
		this.response = response;
	}

	@Override
	public void printAllRecordsInTable(String dbTable, String... columnNames)
			throws SQLException, NullPointerException {
		result = query.getAllRecords(dbTable, statement);
		resultSetPrinting(columnNames);
	}

	@Override
	public void printRecordsById(String dbTable, String id, String... columnNames)
			throws SQLException, NullPointerException {
		result = query.getRecordById(dbTable, statement, id);
		resultSetPrinting(columnNames);

	}

	@Override
	public void printRecordsByName(String dbTable, String name, String... columnNames)
			throws SQLException, NullPointerException, SchoolClassException {
		ObjectValidator.checkForSchoolClassException(dbTable, "School classes dont have name property");
		result = query.getRecordByName(dbTable, statement, name);
		resultSetPrinting(columnNames);

	}

	@Override
	protected void resultSetPrinting(String... columnNames) throws NullPointerException, SQLException {
		ObjectValidator.checkIfObjectIsNull(result, "Wrong property name or database table");
		result = ObjectValidator.checkAndMoveCursorToNextPosition(result, "Wrong resultset cursor translation");
		printTableRow(columnNames);
		while (result.next()) {
			printTableRow(columnNames);
		}

	}

	@Override
	protected void printTableRow(String[] columnNames) throws SQLException {
		for (String currentColumnName : columnNames) {
			String queryResult = ObjectValidator.checkForPropertyNameValidaty(result, currentColumnName,
					"Wrong table's column name");
			try {
				response.getWriter().println("<p>" + queryResult + "</p>");
			} catch (IOException e) {
				// fictive
			}
		}

	}

}

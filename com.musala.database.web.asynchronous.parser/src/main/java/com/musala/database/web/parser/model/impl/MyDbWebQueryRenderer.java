package com.musala.database.web.parser.model.impl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.musala.database.web.parser.helper.ObjectValidator;
import com.musala.database.web.parser.helper.SchoolClassException;
import com.musala.database.web.parser.model.IDbConnector;
import com.musala.database.web.parser.model.IQueryable;

public class MyDbWebQueryRenderer extends AbstractDbQueryWriter {

	private HttpServletResponse response;
	private String returnAnswear = "";

	public MyDbWebQueryRenderer(IDbConnector dbconnector, IQueryable query, HttpServletResponse response) {
		super(dbconnector, query);
		this.response = response;
	}

	@Override
	public void printAllRecordsInTable(String dbTable, String... columnNames)
			throws SQLException, NullPointerException {
		result = query.getAllRecords(dbTable, statement);
		printResultSet(columnNames);
	}

	@Override
	public void printRecordsById(String dbTable, String id, String... columnNames)
			throws SQLException, NullPointerException {
		result = query.getRecordById(dbTable, statement, id);
		printResultSet(columnNames);

	}

	@Override
	public void printRecordsByName(String dbTable, String name, String... columnNames)
			throws SQLException, NullPointerException, SchoolClassException {
		ObjectValidator.checkForSchoolClassException(dbTable, "School classes dont have name property");
		result = query.getRecordByName(dbTable, statement, name);
		printResultSet(columnNames);

	}

	@Override
	protected void printResultSet(String... columnNames) throws NullPointerException, SQLException {
		ObjectValidator.checkIfObjectIsNull(result, "Wrong property name or database table");
		result = ObjectValidator.checkAndMoveCursorToNextPosition(result, "Wrong resultset cursor translation");

		printTableRow(columnNames);
		returnAnswear+=",";
		while (result.next()) {
			printTableRow(columnNames);
			returnAnswear+=",";
		}
		String respMessage = new JsonMaker().convertAnswear(returnAnswear.substring(0, returnAnswear.length()-1), false);
		try {
			response.getWriter().println(respMessage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void printTableRow(String[] columnNames) throws SQLException {
		for (String currentColumnName : columnNames) {
			String queryResult = ObjectValidator.checkForPropertyNameValidaty(result, currentColumnName,
					"Wrong table's column name");
			returnAnswear += queryResult + " ";
		}

	}
}

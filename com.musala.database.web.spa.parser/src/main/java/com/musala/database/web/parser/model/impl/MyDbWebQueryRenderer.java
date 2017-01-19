package com.musala.database.web.parser.model.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import com.musala.database.web.parser.helper.ObjectValidator;
import com.musala.database.web.parser.helper.SchoolClassException;
import com.musala.database.web.parser.model.IDbConnector;
import com.musala.database.web.parser.model.IQueryable;
import com.musala.database.web.parser.model.ui.JsonMaker;

public class MyDbWebQueryRenderer extends AbstractDbQueryWriter {

	private HttpServletResponse response;
	private String returnAnswear = "";
	private HashMap<String, String> repoForJsonCreation = new HashMap<>();

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
		returnAnswear += ",";
		while (result.next()) {
			printTableRow(columnNames);
			returnAnswear += ",";
		}
		repoForJsonCreation.put("status", "false");
		repoForJsonCreation.put("message", returnAnswear.substring(0, returnAnswear.length() - 1));
		String json =JsonMaker.build("answear", repoForJsonCreation);
		try {
			response.getWriter().println(json);
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

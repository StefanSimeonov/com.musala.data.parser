package com.musala.database.web.spa.spring.parser.model.impl;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.musala.database.web.spa.parser.model.ui.JsonMaker;
import com.musala.database.web.spa.spring.parser.helper.ObjectValidator;
import com.musala.database.web.spa.spring.parser.helper.SchoolClassException;
import com.musala.database.web.spa.spring.parser.model.IDbConnector;
import com.musala.database.web.spa.spring.parser.model.IQueryable;

@Component
public class MyDbWebQueryRenderer extends AbstractDbQueryWriter {

	private String returnAnswer = "";
	private HashMap<String, String> repoForJsonCreation = new HashMap<>();

	public MyDbWebQueryRenderer(IDbConnector dbconnector, IQueryable query) {
		super(dbconnector, query);
		
	}

	@Override
	public String printAllRecordsInTable(String dbTable, String... columnNames)
			throws SQLException, NullPointerException {
		result = query.getAllRecords(dbTable, statement);
	return	printResultSet(columnNames);
	}

	@Override
	public String printRecordsById(String dbTable, String id, String... columnNames)
			throws SQLException, NullPointerException {
		result = query.getRecordById(dbTable, statement, id);
	return	printResultSet(columnNames);

	}

	@Override
	public String printRecordsByName(String dbTable, String name, String... columnNames)
			throws SQLException, NullPointerException, SchoolClassException {
		ObjectValidator.checkForSchoolClassException(dbTable, "School classes dont have name property");
		result = query.getRecordByName(dbTable, statement, name);
	return	printResultSet(columnNames);

	}

	@Override
	protected String printResultSet(String... columnNames) throws NullPointerException, SQLException {
		ObjectValidator.checkIfObjectIsNull(result, "Wrong property name or database table");
		result = ObjectValidator.checkAndMoveCursorToNextPosition(result, "Wrong resultset cursor translation");

		printTableRow(columnNames);
		returnAnswer += ",";
		while (result.next()) {
			printTableRow(columnNames);
			returnAnswer += ",";
		}
		repoForJsonCreation.put("status", "false");
		repoForJsonCreation.put("message", returnAnswer.substring(0, returnAnswer.length() - 1));
		String json = JsonMaker.build("answer", repoForJsonCreation);
		return json;

	}

	@Override
	protected void printTableRow(String[] columnNames) throws SQLException {
		for (String currentColumnName : columnNames) {
			String queryResult = ObjectValidator.checkForPropertyNameValidaty(result, currentColumnName,
					"Wrong table's column name");
			returnAnswer += queryResult + " ";
		}

	}
}

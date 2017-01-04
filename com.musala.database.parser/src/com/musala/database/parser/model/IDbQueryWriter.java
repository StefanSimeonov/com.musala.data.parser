package com.musala.database.parser.model;

import java.sql.SQLException;

import com.musala.database.parser.helper.SchoolClassException;

/** Require implementation of printing logic for all classes which implements IDbQueryWriter
 *
 */
public interface IDbQueryWriter {

	void printAllRecordsInTable(String dbTable, String... columnNames)throws SQLException, NullPointerException;

	void printRecordsById(String dbTable, String id, String... columnNames)throws SQLException,NullPointerException;

	void printRecordsByName(String dbTable, String name, String... columnNames)
			throws SQLException, NullPointerException, SchoolClassException;

	IDbConnector getConnector();

	IQueryable getQuery();

}

package com.musala.database.parser.model;

import java.sql.SQLException;

import com.musala.database.parser.helper.SchoolClassException;

/**
 * Require implementation of printing logic for all classes which implements
 * IDbQueryWriter
 *
 */
public interface IDbQueryWriter {
	/**
	 * Retrieve the IDbConnector object
	 * 
	 * @return IDbConnector
	 */
	IDbConnector getConnector();

	/**
	 * Retrieve the IQueryable object
	 * 
	 * @return IQueryable
	 */
	IQueryable getQuery();

	/**
	 * Print all records in a given table without where clause
	 */
	void printAllRecordsInTable(String dbTable, String... columnNames) throws SQLException, NullPointerException;

	/**
	 * Print all records in a given table with additional where clause
	 * 
	 * @throws SQLException,
	 *             NullPointerException
	 */
	void printRecordsById(String dbTable, String id, String... columnNames) throws SQLException, NullPointerException;

	/**
	 * Print all records in a given table without where clause
	 * 
	 * @throws SQLException,SchoolClassException,
	 *             NullPointerException
	 */
	void printRecordsByName(String dbTable, String name, String... columnNames)
			throws SQLException, NullPointerException, SchoolClassException;
}

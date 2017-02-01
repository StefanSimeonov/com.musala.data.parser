package com.musala.database.web.spa.parser.model;

import java.sql.SQLException;

import com.musala.database.web.spa.parser.helper.SchoolClassException;

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
	 * @return 
	 */
	String printAllRecordsInTable(String dbTable, String... columnNames) throws SQLException, NullPointerException;

	/**
	 * Print all records in a given table with additional where clause
	 * @return 
	 * 
	 * @throws SQLException,
	 *             NullPointerException
	 */
	String printRecordsById(String dbTable, String id, String... columnNames) throws SQLException, NullPointerException;

	/**
	 * Print all records in a given table without where clause
	 * @return 
	 * 
	 * @throws SQLException,SchoolClassException,
	 *             NullPointerException
	 */
	String printRecordsByName(String dbTable, String name, String... columnNames)
			throws SQLException, NullPointerException, SchoolClassException;
}

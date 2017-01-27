package com.musala.database.web.spa.parser.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.musala.database.web.spa.parser.model.IDbConnector;
import com.musala.database.web.spa.parser.model.IDbQueryWriter;
import com.musala.database.web.spa.parser.model.IQueryable;

/**
 * Add some class variable and constructor type to IdbQueryWriter
 *
 */
public abstract class AbstractDbQueryWriter implements IDbQueryWriter {
	IDbConnector dbconnector;
	Statement statement;
	IQueryable query;
	ResultSet result;

	public AbstractDbQueryWriter(IDbConnector dbconnector, IQueryable query) {
		this.dbconnector = dbconnector;
		this.statement = this.dbconnector.getStatement();
		this.query = query;
	}

	@Override
	public IDbConnector getConnector() {
		return dbconnector;
	}

	@Override
	public IQueryable getQuery() {
		return query;
	}

	/**
	 * Print the resultSet with brought comunNames as String array. Iterate
	 * trough all table's rows.
	 * 
	 * @param columnNames
	 * @throws NullPointerException
	 * @throws SQLException
	 */
	protected abstract void printResultSet(String... columnNames) throws NullPointerException, SQLException;

	/**
	 * Getting the all results from a current table row
	 * 
	 * @param columnNames
	 * @throws SQLException
	 */
	protected abstract void printTableRow(String[] columnNames) throws SQLException;
}

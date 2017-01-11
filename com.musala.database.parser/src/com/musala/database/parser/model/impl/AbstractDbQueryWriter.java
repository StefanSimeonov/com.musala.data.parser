package com.musala.database.parser.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.musala.database.parser.model.IDbConnector;
import com.musala.database.parser.model.IDbQueryWriter;
import com.musala.database.parser.model.IQueryable;

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
	protected abstract void resultSetPrinting(String... columnNames) throws NullPointerException, SQLException;
	protected abstract void printTableRow(String[] columnNames) throws SQLException;
}

package com.musala.database.parser.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.musala.database.parser.helper.ObjectValidator;
import com.musala.database.parser.model.IQueryable;

/**
 * Safe to itself database connection and statement for executing all given
 * queries from the public methods
 *
 */
public class MySqlQueryBuilder implements IQueryable {

	private static final String SELECT_PART_OF_QUERY = "select * from ";


	 /** Execute this specific query and returns the result of it as a iterable
	 * resultSet
	 * @retun ResultSet
	 */
	@Override
	public ResultSet getRecordById(String dbTable, Statement statement, String id)
			throws NullPointerException, SQLException {

		String query = SELECT_PART_OF_QUERY + dbTable + " where Id=" + id;
		ResultSet result = ObjectValidator.checkForSQLQuery(query, statement);
		
		return result;
	}

	/**
	 * Execute this specific query and returns the result of it as a iterable
	 * resultSet
	 */
	@Override
	public ResultSet getRecordByName(String dbTable, Statement statement, String name)
			throws NullPointerException, SQLException {
		String query = SELECT_PART_OF_QUERY + dbTable + " where name='" + name + "'";
		ResultSet result = ObjectValidator.checkForSQLQuery(query, statement);
		
		return result;
	}

	/**
	 * Execute this specific query and returns the result of it as a iterable
	 * resultSet
	 */
	@Override
	public ResultSet getAllRecords(String dbTable, Statement statement) throws NullPointerException, SQLException {
		String query = SELECT_PART_OF_QUERY + dbTable;
		ResultSet result = ObjectValidator.checkForSQLQuery(query, statement);
		
		return result;
	}

}

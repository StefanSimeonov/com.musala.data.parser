package com.musala.database.web.spa.parser.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface IQueryable {

	/**
	 * Execute this specific query and returns the result of it as a iterable
	 * resultSet
	 */
	ResultSet getAllRecords(String dbTable, Statement statement) throws SQLException, NullPointerException;

	/**
	 * Execute this specific query and returns the result of it as a iterable
	 * resultSet
	 * 
	 * @return ResultSet
	 */
	ResultSet getRecordById(String dbTable, Statement statement, String Id) throws SQLException, NullPointerException;

	/**
	 * Execute this specific query and returns the result of it as a iterable
	 * resultSet
	 */
	ResultSet getRecordByName(String dbTable, Statement statement, String name)
			throws SQLException, NullPointerException;
}

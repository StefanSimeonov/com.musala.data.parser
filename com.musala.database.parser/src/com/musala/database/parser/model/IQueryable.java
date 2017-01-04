package com.musala.database.parser.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface IQueryable {

	ResultSet getRecordById(String dbTable, Statement statement, String Id) throws SQLException, NullPointerException;

	ResultSet getRecordByName(String dbTable, Statement statement, String name)
			throws SQLException, NullPointerException;

	ResultSet getAllRecords(String dbTable, Statement statement) throws SQLException, NullPointerException;
}

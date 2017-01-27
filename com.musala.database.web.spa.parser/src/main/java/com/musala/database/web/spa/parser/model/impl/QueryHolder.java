package com.musala.database.web.spa.parser.model.impl;

/**
 * Class used for holding query data
 * 
 * @author stefan.simeonov
 */
public class QueryHolder {
	private QueryType queryType;
	private String[] currentProperties;
	private String currentTable;

	public void setCurrentProperties(String[] currentProperties) {
		this.currentProperties = currentProperties;
	}

	public void setCurrentTable(String currentTable) {
		this.currentTable = currentTable;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public String[] getCurrentProperties() {
		return currentProperties;
	}

	public String getCurrentTable() {
		return currentTable;
	}

	public QueryType getQueryType() {
		return queryType;
	}

}

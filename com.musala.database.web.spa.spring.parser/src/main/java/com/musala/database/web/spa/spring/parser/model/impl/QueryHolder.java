package com.musala.database.web.spa.spring.parser.model.impl;

/**
 * Class used for holding query data
 * 
 * @author stefan.simeonov
 */
public class QueryHolder {
	private String queryType;
	private String[] currentProperties;
	private String currentTable;

	public void setCurrentProperties(String[] currentProperties) {
		this.currentProperties = currentProperties;
	}

	public void setCurrentTable(String currentTable) {
		this.currentTable = currentTable;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String[] getCurrentProperties() {
		return currentProperties;
	}

	public String getCurrentTable() {
		return currentTable;
	}

	public String getQueryType() {
		return queryType;
	}

}

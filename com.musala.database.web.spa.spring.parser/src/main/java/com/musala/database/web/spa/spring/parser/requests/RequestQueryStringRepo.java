package com.musala.database.web.spa.spring.parser.requests;

import org.springframework.stereotype.Component;

@Component
public class RequestQueryStringRepo {
	private String queriesType;
	private String tableName;
	private String properties;
	private String funcRequest;
	private String id;
	private String name;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFuncRequest(String funcRequest) {
		this.funcRequest = funcRequest;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public void setQueriesType(String queriesType) {
		this.queriesType = queriesType;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFuncRequest() {
		return funcRequest;
	}

	public String getProperties() {
		return properties;
	}

	public String getQueriesType() {
		return queriesType;
	}

	public String getTableName() {
		return tableName;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}

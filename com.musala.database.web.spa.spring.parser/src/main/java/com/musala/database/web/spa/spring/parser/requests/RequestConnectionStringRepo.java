package com.musala.database.web.spa.spring.parser.requests;

import org.springframework.stereotype.Component;

@Component
public class RequestConnectionStringRepo {
	private String serverName;
	private String databaseName;
	private String username;
	private String password;
	private String funcRequest;

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setFuncRequest(String funcRequest) {
		this.funcRequest = funcRequest;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public String getPassword() {
		return password;
	}

	public String getServerName() {
		return serverName;
	}

	public String getUsername() {
		return username;
	}

	public String getFuncRequest() {
		return funcRequest;
	}
}

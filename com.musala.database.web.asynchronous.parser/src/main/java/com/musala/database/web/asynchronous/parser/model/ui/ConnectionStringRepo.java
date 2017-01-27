package com.musala.database.web.asynchronous.parser.model.ui;

public class ConnectionStringRepo {
	private String serverName;
	private String databaseName;
	private String username;
	private String password;
	@SuppressWarnings("unused")
	private boolean status;

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
}

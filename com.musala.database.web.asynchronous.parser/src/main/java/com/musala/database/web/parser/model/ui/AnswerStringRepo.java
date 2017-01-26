package com.musala.database.web.parser.model.ui;

public class AnswerStringRepo {
	private String message;
	private boolean status;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	
	public boolean getStatus(){
		return status;
	}
}

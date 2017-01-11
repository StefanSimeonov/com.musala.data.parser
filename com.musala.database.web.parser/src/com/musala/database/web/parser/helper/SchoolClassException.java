package com.musala.database.web.parser.helper;

/**
 * This exception is occurring while the user wants the SchoolClass by
 * name(SchoolClass doesn't have name column)
 *
 */
public class SchoolClassException extends Exception {

	private static final long serialVersionUID = -6385904158210420732L;

	private String message;

	public SchoolClassException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}

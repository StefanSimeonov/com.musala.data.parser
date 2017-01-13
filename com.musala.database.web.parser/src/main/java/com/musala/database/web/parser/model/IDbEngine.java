package com.musala.database.web.parser.model;

/**
 * For cycling the all logic of the program
 *
 */
public interface IDbEngine {
	/**
	 * Connect to the given, as separate strings, database and instancing all
	 * classes that depends on it. Handle all the occurred exceptions occurred
	 * meanwhile and don't pass while proper input isn't given
	 */
	void initialize();

	/**
	 * Execute queries to database and render them using some QIueryWriter.
	 */
	void startQuering();
}

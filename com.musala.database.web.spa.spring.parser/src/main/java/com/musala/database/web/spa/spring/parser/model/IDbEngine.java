package com.musala.database.web.spa.spring.parser.model;

public interface IDbEngine {
	/**
	 * Connect to the given, as separate strings, database and instancing all
	 * classes that depends on it. Handle all the occurred exceptions occurred
	 * meanwhile and don't pass while proper input isn't given
	 * @return 
	 */
	String initialize();

	/**
	 * Execute queries to database and render them using some QIueryWriter.
	 */
  String startQuering();
}

package com.musala.database.web.spa.spring.parser.model.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.musala.database.web.spa.spring.parser.model.IDbConnector;
import com.musala.database.web.spa.spring.parser.model.IDbEngine;
import com.musala.database.web.spa.spring.parser.model.IDbQueryWriter;
import com.musala.database.web.spa.spring.parser.model.IQueryable;

/**
 * Add some class variable to IDbEngine
 */
public abstract class AbstractDbEngine implements IDbEngine {
	
	protected IQueryable query;
	
	protected IDbConnector connection;
	
	protected IDbQueryWriter writer;
}

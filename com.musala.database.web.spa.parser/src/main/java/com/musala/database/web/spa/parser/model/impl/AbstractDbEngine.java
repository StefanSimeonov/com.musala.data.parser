package com.musala.database.web.spa.parser.model.impl;

import java.io.PrintWriter;

import com.musala.database.web.spa.parser.model.IDbConnector;
import com.musala.database.web.spa.parser.model.IDbEngine;
import com.musala.database.web.spa.parser.model.IDbInputProvider;
import com.musala.database.web.spa.parser.model.IDbQueryWriter;
import com.musala.database.web.spa.parser.model.IQueryable;

/**
 * Add some class variable to IDbEngine
 */
public abstract class AbstractDbEngine implements IDbEngine {
	protected IDbInputProvider input;
	protected IDbQueryWriter writer;
	protected IQueryable query;
	protected IDbConnector connection;
	protected PrintWriter errorWriter;
}

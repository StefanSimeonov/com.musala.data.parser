package com.musala.database.web.spa.spring.parser.model.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.database.web.spa.spring.parser.model.IDbEngine;
import com.musala.database.web.spa.spring.parser.model.IDbService;
import com.musala.database.web.spa.spring.parser.requests.RequestConnectionStringRepo;
import com.musala.database.web.spa.spring.parser.requests.RequestQueryStringRepo;

@Service
public class MyDbService implements IDbService {

	@Autowired
	IDbEngine engine;

	public String initialize(RequestConnectionStringRepo connetionRequest) {
		engine = MySqlWebDbEngine.getInstanceForConnection(connetionRequest);
		return engine.initialize();
	}

	@Override
	public String startQuerying(RequestQueryStringRepo queryRequest) {
		engine = MySqlWebDbEngine.getInstanceForQuering(queryRequest);
		return engine.startQuering();
	}
}

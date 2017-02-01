package com.musala.database.web.spa.spring.parser.model;

import com.musala.database.web.spa.spring.parser.requests.RequestConnectionStringRepo;
import com.musala.database.web.spa.spring.parser.requests.RequestQueryStringRepo;

public interface IDbService {
	public String initialize(RequestConnectionStringRepo request);
	String startQuerying(RequestQueryStringRepo request);
}

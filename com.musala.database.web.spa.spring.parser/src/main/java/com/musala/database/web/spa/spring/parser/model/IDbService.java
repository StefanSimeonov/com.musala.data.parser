package com.musala.database.web.spa.spring.parser.model;

import com.musala.database.web.spa.spring.parser.request.RequestConnectionStringRepo;
import com.musala.database.web.spa.spring.parser.request.RequestQueryStringRepo;

public interface IDbService {
	public String initialize(RequestConnectionStringRepo request);
	String startQuerying(RequestQueryStringRepo request);
}

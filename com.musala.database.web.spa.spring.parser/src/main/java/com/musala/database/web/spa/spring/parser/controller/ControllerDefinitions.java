package com.musala.database.web.spa.spring.parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.musala.database.web.spa.spring.parser.model.IDbService;
import com.musala.database.web.spa.spring.parser.requests.RequestConnectionStringRepo;
import com.musala.database.web.spa.spring.parser.requests.RequestQueryStringRepo;

@RestController
public class ControllerDefinitions {

	@Autowired
	private IDbService service;

	@RequestMapping(method = RequestMethod.POST, value = "/ConnectionController", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String getConnection(@RequestBody RequestConnectionStringRepo connectionRequest) {
		String serverAnswer = service.initialize(connectionRequest);
		return serverAnswer;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/QueryController", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String getQuery(@RequestBody RequestQueryStringRepo queryRequest) {
		String serverAnswer = service.startQuerying(queryRequest);
		return serverAnswer;
	}

}

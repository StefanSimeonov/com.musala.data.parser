package com.musala.database.web.spa.parser.test.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.JsonObject;
import com.musala.database.web.spa.spring.parser.model.impl.MySqlWebDbEngine;
import com.musala.database.web.spa.spring.parser.requests.RequestConnectionStringRepo;
import com.musala.database.web.spa.spring.parser.requests.RequestQueryStringRepo;

@RunWith(MockitoJUnitRunner.class)
public class EngineTest {

	JsonObject jsonObj;
	@Mock
	HttpServletResponse response;

	@Before
	public void initJsonObject() {
		jsonObj = new JsonObject();
		jsonObj.addProperty("serverName", "localhost");
		jsonObj.addProperty("databaseName", "schools");
		jsonObj.addProperty("username", "root");
		jsonObj.addProperty("password", "");
		jsonObj.addProperty("funcRequest", "first");
	}

	@Test
	public void testInitializationWithEmpryConnectionRequest() throws IOException {
		RequestConnectionStringRepo connetionRequest=new RequestConnectionStringRepo();
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstanceForConnection( connetionRequest);
		assertEquals("{\"status\":false}", db.initialize());
	}
	
	@Test
	public void testInitializationWithProperConnectionRequest() throws IOException {
		RequestConnectionStringRepo connetionRequest=new RequestConnectionStringRepo();
		connetionRequest.setServerName("localhost");
		connetionRequest.setDatabaseName("schools");
		connetionRequest.setPassword("");
		connetionRequest.setUsername("root");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstanceForConnection( connetionRequest);
		assertEquals("{\"serverName\":\"localhost\",\"databaseName\":\"schools\",\"username\":\"root\",\"password\":\"\",\"status\":true}", db.initialize());
	}

	@Test
	public void testQueryingWithoutAnyArguments() throws FileNotFoundException, IOException {
	
		RequestQueryStringRepo queryRequest=new RequestQueryStringRepo();
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstanceForQuering(queryRequest);
		try {
			db.startQuering();
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testQueryingWitFourthQueryType() throws FileNotFoundException, IOException {
		RequestQueryStringRepo queryRequest=new RequestQueryStringRepo();
		queryRequest.setQueriesType("closeTheQuery");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstanceForQuering(queryRequest);
		assertEquals("{\"message\":\"Thank you\",\"status\":false}", db.startQuering());
	}

	@Test
	public void testQueryingWithProperRequest() throws IOException {
		RequestQueryStringRepo queryRequest=new RequestQueryStringRepo();
		queryRequest.setQueriesType("getAllRecords");
		queryRequest.setProperties("name");
		queryRequest.setTableName("schools");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstanceForQuering(queryRequest);
		assertEquals("{\"message\":\"Ivan Vazov ,Hristo Botev \",\"status\":false}", db.startQuering());
	}

	@Test
	public void testExtendedQuerying() throws IOException {
		RequestQueryStringRepo queryRequest=new RequestQueryStringRepo();
		queryRequest.setQueriesType("getRecordById");
		queryRequest.setProperties("name");
		queryRequest.setTableName("schools");
		queryRequest.setId("1");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstanceForQuering(queryRequest);
		assertEquals("{\"message\":\"Succesfull\",\"status\":true}", db.startQuering());
	}

	@Test
	public void testQueryingWithPropertiesInvalidRequest() throws IOException {
		RequestQueryStringRepo queryRequest=new RequestQueryStringRepo();
		queryRequest.setQueriesType("getAllRecords");
		queryRequest.setProperties("namesd");
		queryRequest.setTableName("schools");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstanceForQuering(queryRequest);
		assertEquals("{\"message\":\"Invalid query\",\"status\":false}", db.startQuering());
	}
	
	@Test
	public void testQueryingWithTableNameInvalidRequest() throws IOException {
		RequestQueryStringRepo queryRequest=new RequestQueryStringRepo();
		queryRequest.setQueriesType("getAllRecords");
		queryRequest.setProperties("name");
		queryRequest.setTableName("schooassls");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstanceForQuering(queryRequest);
		assertEquals("{\"message\":\"Invalid query\",\"status\":false}", db.startQuering());
	}
	
	@Test
	public void testQueryingWithInvalidQueryTypeRequest() throws IOException {
		RequestQueryStringRepo queryRequest=new RequestQueryStringRepo();
		queryRequest.setQueriesType("getAllRecssords");
		queryRequest.setProperties("name");
		queryRequest.setTableName("schools");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstanceForQuering(queryRequest);
		assertNull(db.startQuering());
	}
}

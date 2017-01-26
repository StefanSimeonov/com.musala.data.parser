package com.musala.database.web.spa.parser.test.jsonmaker;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.HashMap;

import org.junit.Test;

import com.musala.database.web.parser.model.ui.JsonMaker;

public class JsonMakerTest {

	@Test
	public void testBuildCheckForProperResponse() {
		HashMap<String, String> map = new HashMap<>();
		map.put("serverName", "localhost");
		map.put("databaseName", "schools");
		map.put("username", "root");
		map.put("password", "");
		map.put("status", "true");
		String obj = JsonMaker.build("connection", map);
		assertEquals(
				"{\"serverName\":\"localhost\",\"databaseName\":\"schools\",\"username\":\"root\",\"password\":\"\",\"status\":true}",
				obj);
	}

	@Test
	public void testBuildCheckForNullPointingMap() {
		HashMap<String, String> map = new HashMap<>();
		try {
			 JsonMaker.build("connection", map);
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testBuildCheckForNullReturnObj() {
		String obj = JsonMaker.build("", new HashMap<>());
		assertNull(obj);
	}
}
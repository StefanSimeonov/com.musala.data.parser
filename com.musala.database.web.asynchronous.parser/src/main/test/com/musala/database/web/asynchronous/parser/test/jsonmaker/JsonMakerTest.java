package com.musala.database.web.asynchronous.parser.test.jsonmaker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Test;
import org.mockito.Mock;
import static org.hamcrest.CoreMatchers.equalTo;
import com.musala.database.web.parser.model.ui.JsonMaker;

public class JsonMakerTest  {
	
	@Test
	public void testBuildCheckForProperResponse() {
		HashMap<String, String> map=new HashMap<>();
		map.put("serverName", "localhost");
		map.put("databaseName", "schools");
		map.put("username", "root");
		map.put("password","");
		map.put("status", "true");
		String obj=JsonMaker.build("connection", map);
		assertEquals("{\"serverName\":\"localhost\",\"databaseName\":\"schools\",\"username\":\"root\",\"password\":\"\",\"status\":true}",obj);
	}

	@Test
	public void testBuildCheckForNullPointingMap() {
		HashMap<String, String> map=new HashMap<>();
		try{
			String obj=JsonMaker.build("connection", map);
		}catch(NullPointerException e){
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}
	
	@Test
	public void testBuildCheckForNullReturnObj() {
			String obj=JsonMaker.build("", new HashMap<>());
		assertNull(obj);
	}
}

package com.musala.database.web.spa.parser.test.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.JsonObject;
import com.musala.database.web.parser.model.impl.MySqlWebDbEngine;

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
	public void testInitializationWithProperParameters() throws IOException {
		File file = File.createTempFile("temp", ".txt");
		PrintWriter writer = new PrintWriter(file);
		when(response.getWriter()).thenReturn(writer);
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, jsonObj);
		db.initialize();
		writer.flush();
		Scanner scan = new Scanner(file);
		String returnedResponseAsString = scan.nextLine();
		int index = returnedResponseAsString.lastIndexOf(":");
		String returnedStatus = returnedResponseAsString.substring(index + 1, returnedResponseAsString.length() - 1);
		assertEquals("true", returnedStatus);
		scan.close();
	}

	@Test
	public void testInitializationWithWrongParameters() throws IOException {
		File file = File.createTempFile("temp", ".txt");
		PrintWriter writer = new PrintWriter(file);
		when(response.getWriter()).thenReturn(writer);
		jsonObj.addProperty("serverName", "smthWrong");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, jsonObj);
		db.initialize();
		writer.flush();
		Scanner scan = new Scanner(file);
		String returnedResponseAsString = scan.nextLine();
		int index = returnedResponseAsString.lastIndexOf(":");
		String returnedStatus = returnedResponseAsString.substring(index + 1, returnedResponseAsString.length() - 1);
		assertEquals("false", returnedStatus);
		scan.close();
	}

	@Test
	public void testQueryingWithoutAnyArguments() throws FileNotFoundException, IOException {
		when(response.getWriter()).thenReturn(new PrintWriter("smth"));
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, jsonObj);
		db.initialize();
		try {
			db.startQuering();
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testQueryingWitFourthQueryType() throws FileNotFoundException, IOException {
		File file = File.createTempFile("temp", ".txt");
		PrintWriter writer = new PrintWriter(file);
		when(response.getWriter()).thenReturn(writer);
		jsonObj.addProperty("queriesType", "closeTheQuery");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, jsonObj);
		db.initialize();
		db.startQuering();
		writer.flush();
		Scanner scan = new Scanner(file);
		String returnedResponseAsString = scan.nextLine();
		int index = returnedResponseAsString.lastIndexOf(":");
		String returnedStatus = returnedResponseAsString.substring(index + 1, returnedResponseAsString.length() - 1);
		assertEquals("false", returnedStatus);
		scan.close();
	}

	@Test
	public void testQueryingWithProperRequest() throws IOException {
		File file = File.createTempFile("temp", ".txt");
		PrintWriter writer = new PrintWriter(file);
		when(response.getWriter()).thenReturn(writer);
		jsonObj.addProperty("queriesType", "getAllRecords");
		jsonObj.addProperty("properties", "id");
		jsonObj.addProperty("tableName", "schools");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, jsonObj);
		db.initialize();
		db.startQuering();
		writer.flush();
		Scanner scan = new Scanner(file);
		String returnedResponseAsString = scan.nextLine();
		int index = returnedResponseAsString.lastIndexOf(":");
		String returnedStatus = returnedResponseAsString.substring(index + 1, returnedResponseAsString.length() - 1);
		assertEquals("false", returnedStatus);
		scan.close();

	}

	@Test
	public void testExtendedQuerying() throws IOException {
		File file = File.createTempFile("temp", ".txt");
		PrintWriter writer = new PrintWriter(file);
		when(response.getWriter()).thenReturn(writer);
		jsonObj.addProperty("queriesType", "getRecordById");
		jsonObj.addProperty("properties", "id");
		jsonObj.addProperty("tableName", "schools");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, jsonObj);
		db.initialize();
		db.startQuering();
		writer.flush();
		Scanner scan = new Scanner(file);
		String returnedResponseAsString = scan.nextLine();
		int index = returnedResponseAsString.lastIndexOf(":");
		String returnedStatus = returnedResponseAsString.substring(index + 1, returnedResponseAsString.length() - 1);
		assertEquals("true", returnedStatus);
		scan.close();

	}

}

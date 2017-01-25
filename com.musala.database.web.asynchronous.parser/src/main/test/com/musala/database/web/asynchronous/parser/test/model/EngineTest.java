package com.musala.database.web.asynchronous.parser.test.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.musala.database.web.parser.model.impl.MySqlWebDbEngine;

@RunWith(MockitoJUnitRunner.class)
public class EngineTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@InjectMocks
	MySqlWebDbEngine engine = MySqlWebDbEngine.getInstance(response, request);

	@Test
	public void testInitializationWithoutRequestParameters() throws IOException, ClassNotFoundException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);// manual
																			// mock
																			// the
																			// object
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);// manual
																				// mock
																				// the
																				// object
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		File file = File.createTempFile("file", ".txt");
		PrintWriter writer = new PrintWriter(file);
		when(response.getWriter()).thenReturn(writer);
		db.initialize();
		writer.flush();
		Scanner scan = new Scanner(file);
		assertEquals("{\"status\":false}", scan.nextLine());
		scan.close();
	}

	@Test
	public void testInitializationWithRequestParameters() throws IOException {
		// automatically object mocking
		Mockito.when(request.getParameter("serverName")).thenReturn("localhost");
		Mockito.when(request.getParameter("databaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("username")).thenReturn("root");
		Mockito.when(request.getParameter("password")).thenReturn("");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		File file = File.createTempFile("file", ".txt");
		PrintWriter writer = new PrintWriter(file);
		when(response.getWriter()).thenReturn(writer);
		db.initialize();
		writer.flush();
		Scanner scan = new Scanner(file);
		assertEquals(
				"{\"serverName\":\"localhost\",\"databaseName\":\"schools\",\"username\":\"root\",\"password\":\"\",\"status\":true}",
				scan.nextLine());
		scan.close();
	}

	@Test
	public void testQueryingWithoutAnyArguments() throws FileNotFoundException, IOException {
		Mockito.when(request.getParameter("serverName")).thenReturn("localhost");
		Mockito.when(request.getParameter("databaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("username")).thenReturn("root");
		Mockito.when(request.getParameter("password")).thenReturn("");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		when(response.getWriter()).thenReturn(new PrintWriter("somewhere"));
		db.initialize();
		try {
			db.startQuering();
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testQueryingWithoutEnoughArguments() throws FileNotFoundException, IOException {
		Mockito.when(request.getParameter("serverName")).thenReturn("localhost");
		Mockito.when(request.getParameter("databaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("username")).thenReturn("root");
		Mockito.when(request.getParameter("password")).thenReturn("");
		Mockito.when(request.getParameter("queriesType")).thenReturn("getAllRecords");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		when(response.getWriter()).thenReturn(new PrintWriter("somewhere"));
		db.initialize();
		try {
			db.startQuering();
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testQueryingWitFourthQueryType() throws IOException {
		Mockito.when(request.getParameter("serverName")).thenReturn("localhost");
		Mockito.when(request.getParameter("databaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("username")).thenReturn("root");
		Mockito.when(request.getParameter("password")).thenReturn("");
		Mockito.when(request.getParameter("queriesType")).thenReturn("closeTheQuery");
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("somefile.txt"));
		when(response.getWriter()).thenReturn(new PrintWriter("somewhere"));
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		db.initialize();
		db.startQuering();
		verify(response, times(2)).getWriter();
	}

	@Test
	public void testQueryingForProperResponse() throws IOException {
		Mockito.when(request.getParameter("serverName")).thenReturn("localhost");
		Mockito.when(request.getParameter("databaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("username")).thenReturn("root");
		Mockito.when(request.getParameter("password")).thenReturn("");
		Mockito.when(request.getParameter("queriesType")).thenReturn("getAllRecords");
		Mockito.when(request.getParameter("properties")).thenReturn("id");
		Mockito.when(request.getParameter("tableName")).thenReturn("schools");
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("somefile.txt"));
		when(response.getWriter()).thenReturn(new PrintWriter("somewhere"));
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		db.initialize();
		db.startQuering();
		verify(response, times(2)).getWriter();
	}

	@Test
	public void testExtendedQueryingForProperResponse() throws IOException {
		Mockito.when(request.getParameter("serverName")).thenReturn("localhost");
		Mockito.when(request.getParameter("databaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("username")).thenReturn("root");
		Mockito.when(request.getParameter("password")).thenReturn("");
		Mockito.when(request.getParameter("queriesType")).thenReturn("getRecordById");
		Mockito.when(request.getParameter("properties")).thenReturn("id");
		Mockito.when(request.getParameter("tableName")).thenReturn("schools");
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("somefile.txt"));
		when(response.getWriter()).thenReturn(new PrintWriter("somewhere"));
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		db.initialize();
		db.startQuering();
		verify(response, times(2)).getWriter();
	}
}

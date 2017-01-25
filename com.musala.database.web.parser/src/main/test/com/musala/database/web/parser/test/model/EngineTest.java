package com.musala.database.web.parser.test.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.musala.database.web.parser.helper.RedirectURLBuilder;
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
	public void testInitializationWithoutRequestParameters() throws IOException {
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);// manual
																			// mock
																			// the
																			// object
		HttpServletResponse response = Mockito.mock(HttpServletResponse.class);// manual
																				// mock
																				// the
																				// object

		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		db.initialize();
		verify(response, times(1)).sendRedirect(RedirectURLBuilder.getPath() + "Invalid SQL operation");
	}

	@Test
	public void testInitializationWithRequestParameters() throws IOException {
		// automatically object mocking
		Mockito.when(request.getParameter("ServerName")).thenReturn("localhost");
		Mockito.when(request.getParameter("DatabaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("Username")).thenReturn("root");
		Mockito.when(request.getParameter("Password")).thenReturn("");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		db.initialize();
		verify(response, never()).sendRedirect("");
	}

	@Test
	public void testQueryingWithoutAnyArguments() {
		Mockito.when(request.getParameter("ServerName")).thenReturn("localhost");
		Mockito.when(request.getParameter("DatabaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("Username")).thenReturn("root");
		Mockito.when(request.getParameter("Password")).thenReturn("");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		db.initialize();
		try {
			db.startQuering();
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testQueryingWithoutEnoughArguments() {
		Mockito.when(request.getParameter("ServerName")).thenReturn("localhost");
		Mockito.when(request.getParameter("DatabaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("Username")).thenReturn("root");
		Mockito.when(request.getParameter("Password")).thenReturn("");
		Mockito.when(request.getParameter("queriesType")).thenReturn("getAllRecords");
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		db.initialize();
		try {
			db.startQuering();
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testQueryingWitFourthQueryType() throws IOException {
		Mockito.when(request.getParameter("ServerName")).thenReturn("localhost");
		Mockito.when(request.getParameter("DatabaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("Username")).thenReturn("root");
		Mockito.when(request.getParameter("Password")).thenReturn("");
		Mockito.when(request.getParameter("queriesType")).thenReturn("closeTheQuery");
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("somefile.txt"));
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		db.initialize();
		db.startQuering();
		verify(response, times(1)).getWriter();
	}

	@Test
	public void testQueryingForProperResponse() throws IOException {
		Mockito.when(request.getParameter("ServerName")).thenReturn("localhost");
		Mockito.when(request.getParameter("DatabaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("Username")).thenReturn("root");
		Mockito.when(request.getParameter("Password")).thenReturn("");
		Mockito.when(request.getParameter("queriesType")).thenReturn("getAllRecords");
		Mockito.when(request.getParameter("properties")).thenReturn("id");
		Mockito.when(request.getParameter("tableName")).thenReturn("schools");
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("somefile.txt"));
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		db.initialize();
		db.startQuering();
		verify(response, times(2)).getWriter();
	}

	@Test
	public void testExtendedQueryingForProperResponse() throws IOException {
		Mockito.when(request.getParameter("ServerName")).thenReturn("localhost");
		Mockito.when(request.getParameter("DatabaseName")).thenReturn("schools");
		Mockito.when(request.getParameter("Username")).thenReturn("root");
		Mockito.when(request.getParameter("Password")).thenReturn("");
		Mockito.when(request.getParameter("queriesType")).thenReturn("getRecordById");
		Mockito.when(request.getParameter("properties")).thenReturn("id");
		Mockito.when(request.getParameter("tableName")).thenReturn("schools");
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("somefile.txt"));
		MySqlWebDbEngine db = MySqlWebDbEngine.getInstance(response, request);
		db.initialize();
		db.startQuering();
		verify(response, times(1)).getWriter();
	}
}

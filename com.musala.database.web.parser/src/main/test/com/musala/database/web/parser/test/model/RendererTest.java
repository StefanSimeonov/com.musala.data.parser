package com.musala.database.web.parser.test.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.musala.database.web.parser.helper.ObjectValidator;
import com.musala.database.web.parser.helper.SchoolClassException;
import com.musala.database.web.parser.model.IDbConnector;
import com.musala.database.web.parser.model.IQueryable;
import com.musala.database.web.parser.model.impl.MyDbWebQueryRenderer;
import com.musala.database.web.parser.model.impl.MySqlDbConnector;
import com.musala.database.web.parser.model.impl.MySqlQueryBuilder;

@RunWith(MockitoJUnitRunner.class)
public class RendererTest {

	@Mock
	HttpServletResponse response;

	IDbConnector dbconnector;
	IQueryable query;

	@Before
	public void Initialize() throws SQLException, ClassNotFoundException {
		ObjectValidator.checkForDatabaseDrivers("com.mysql.jdbc.Driver");
		dbconnector = new MySqlDbConnector().buildWithAdditionalCredentials("localhost", "schools", "root", "");
		query = new MySqlQueryBuilder();

	}

	@Test
	public void TestPrintAllRecordsInTable() throws SQLException, FileNotFoundException, IOException {
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("sometex.txt"));
		MyDbWebQueryRenderer renderer = new MyDbWebQueryRenderer(dbconnector, query, response);
		renderer.printAllRecordsInTable("schools", "name");
		Mockito.verify(response, times(2)).getWriter();
	}

	@Test
	public void TestPrintAllRecordsInTableWithWrongTableName() throws SQLException, FileNotFoundException, IOException {
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("sometex.txt"));
		MyDbWebQueryRenderer renderer = new MyDbWebQueryRenderer(dbconnector, query, response);
		try {
			renderer.printAllRecordsInTable("schol", "name");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void TestPrintAllRecordsInTableWithWrongColumnName()
			throws SQLException, FileNotFoundException, IOException {
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("sometex.txt"));
		MyDbWebQueryRenderer renderer = new MyDbWebQueryRenderer(dbconnector, query, response);
		try {
			renderer.printAllRecordsInTable("school", "smth");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void TestPrintRecordsById() throws SQLException, FileNotFoundException, IOException {
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("sometex.txt"));
		MyDbWebQueryRenderer renderer = new MyDbWebQueryRenderer(dbconnector, query, response);
		renderer.printRecordsById("schools", "1", "name");
		Mockito.verify(response, times(1)).getWriter();
	}

	@Test
	public void TestPrintRecordsByIdWithWrongTableName() throws SQLException, FileNotFoundException, IOException {
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("sometex.txt"));
		MyDbWebQueryRenderer renderer = new MyDbWebQueryRenderer(dbconnector, query, response);
		try {
			renderer.printRecordsById("scho", "1", "name");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void TestPrintRecordsByIdWithWrongColumnName() throws SQLException, FileNotFoundException, IOException {
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("sometex.txt"));
		MyDbWebQueryRenderer renderer = new MyDbWebQueryRenderer(dbconnector, query, response);
		try {
			renderer.printRecordsById("schools", "1", "smth");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void TestPrintRecordsByIdWithWrongId() throws SQLException, FileNotFoundException, IOException {
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("sometex.txt"));
		MyDbWebQueryRenderer renderer = new MyDbWebQueryRenderer(dbconnector, query, response);
		try {
			renderer.printRecordsById("schools", "23", "smth");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void TestPrintRecordsByName()
			throws SQLException, FileNotFoundException, IOException, NullPointerException, SchoolClassException {
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("sometex.txt"));
		MyDbWebQueryRenderer renderer = new MyDbWebQueryRenderer(dbconnector, query, response);
		renderer.printRecordsByName("schools", "Ivan Vazov", "name");
		Mockito.verify(response, times(1)).getWriter();
	}

	@Test
	public void TestPrintRecordsByNameWithWrongTableName()
			throws SQLException, FileNotFoundException, IOException, NullPointerException, SchoolClassException {
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("sometex.txt"));
		MyDbWebQueryRenderer renderer = new MyDbWebQueryRenderer(dbconnector, query, response);
		try {
			renderer.printRecordsByName("schos", "Ivan Vazov", "name");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void TestPrintRecordsByNameWithWrongColumnName()
			throws SQLException, FileNotFoundException, IOException, NullPointerException, SchoolClassException {
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("sometex.txt"));
		MyDbWebQueryRenderer renderer = new MyDbWebQueryRenderer(dbconnector, query, response);
		try {
			renderer.printRecordsByName("schools", "Ivan Vazov", "smth");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void TestPrintRecordsByNameWithWrongGivenName()
			throws SQLException, FileNotFoundException, IOException, NullPointerException, SchoolClassException {
		Mockito.when(response.getWriter()).thenReturn(new PrintWriter("sometex.txt"));
		MyDbWebQueryRenderer renderer = new MyDbWebQueryRenderer(dbconnector, query, response);
		try {
			renderer.printRecordsByName("schools", "Kosta Conev", "name");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}
}

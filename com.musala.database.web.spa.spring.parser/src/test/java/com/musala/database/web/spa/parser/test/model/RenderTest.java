package com.musala.database.web.spa.parser.test.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.musala.database.web.spa.spring.parser.helper.ObjectValidator;
import com.musala.database.web.spa.spring.parser.helper.SchoolClassException;
import com.musala.database.web.spa.spring.parser.model.IDbConnector;
import com.musala.database.web.spa.spring.parser.model.IQueryable;
import com.musala.database.web.spa.spring.parser.model.impl.MyDbWebQueryRenderer;
import com.musala.database.web.spa.spring.parser.model.impl.MySqlDbConnector;
import com.musala.database.web.spa.spring.parser.model.impl.MySqlQueryBuilder;

@RunWith(MockitoJUnitRunner.class)
public class RenderTest {

	IDbConnector dbconnector;
	IQueryable query;
	MyDbWebQueryRenderer renderer;

	@Before
	public void Initialize() throws SQLException, ClassNotFoundException {
		ObjectValidator.checkForDatabaseDrivers("com.mysql.jdbc.Driver");
		dbconnector = new MySqlDbConnector().buildWithAdditionalCredentials("localhost", "schools", "root", "");
		query = new MySqlQueryBuilder();
		renderer = new MyDbWebQueryRenderer(dbconnector, query);
	}

	@Test
	public void TestPrintAllRecordsInTable() throws SQLException, FileNotFoundException, IOException {
		assertEquals("{\"message\":\"Ivan Vazov ,Hristo Botev \",\"status\":false}",
				renderer.printAllRecordsInTable("schools", "name"));
	}

	@Test
	public void TestPrintAllRecordsInTableWithWrongTableName() throws SQLException, FileNotFoundException, IOException {
		try {
			renderer.printAllRecordsInTable("schol", "name");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}
	
	 @Test
	 public void TestPrintAllRecordsInTableWithWrongColumnName()
	 throws SQLException, FileNotFoundException, IOException {
	 try {
	 renderer.printAllRecordsInTable("school", "smth");
	 } catch (SQLException e) {
	 assertThat(e.getClass(), equalTo(SQLException.class));
	 }
	 }
	
	 @Test
	 public void TestPrintRecordsById() throws SQLException,
	 FileNotFoundException, IOException {
	assertEquals("{\"message\":\"Ivan Vazov \",\"status\":false}",renderer.printRecordsById("schools", "1", "name"));
	 }
	
	 @Test
	 public void TestPrintRecordsByIdWithWrongTableName() throws SQLException,
	 FileNotFoundException, IOException {
	 try {
	 renderer.printRecordsById("scho", "1", "name");
	 } catch (SQLException e) {
	 assertThat(e.getClass(), equalTo(SQLException.class));
	 }
	 }
	
	 @Test
	 public void TestPrintRecordsByIdWithWrongColumnName() throws
	 SQLException, FileNotFoundException, IOException {
	 try {
	 renderer.printRecordsById("schools", "1", "smth");
	 } catch (SQLException e) {
	 assertThat(e.getClass(), equalTo(SQLException.class));
	 }
	 }
	
	 @Test
	 public void TestPrintRecordsByIdWithWrongId() throws SQLException,
	 FileNotFoundException, IOException {
	 try {
	 renderer.printRecordsById("schools", "23", "smth");
	 } catch (SQLException e) {
	 assertThat(e.getClass(), equalTo(SQLException.class));
	 }
	 }
	
	 @Test
	 public void TestPrintRecordsByName()
	 throws SQLException, FileNotFoundException, IOException,
	 NullPointerException, SchoolClassException {
	assertEquals("{\"message\":\"Ivan Vazov \",\"status\":false}", renderer.printRecordsByName("schools", "Ivan Vazov", "name"));
	 }
	
	 @Test
	 public void TestPrintRecordsByNameWithWrongTableName()
	 throws SQLException, FileNotFoundException, IOException,
	 NullPointerException, SchoolClassException {
	 try {
	 renderer.printRecordsByName("schos", "Ivan Vazov", "name");
	 } catch (SQLException e) {
	 assertThat(e.getClass(), equalTo(SQLException.class));
	 }
	 }
	
	 @Test
	 public void TestPrintRecordsByNameWithWrongColumnName()
	 throws SQLException, FileNotFoundException, IOException,
	 NullPointerException, SchoolClassException {
	 try {
	 renderer.printRecordsByName("schools", "Ivan Vazov", "smth");
	 } catch (SQLException e) {
	 assertThat(e.getClass(), equalTo(SQLException.class));
	 }
	 }
	
	 @Test
	 public void TestPrintRecordsByNameWithWrongGivenName()
	 throws SQLException, FileNotFoundException, IOException,
	 NullPointerException, SchoolClassException {
	 try {
	 renderer.printRecordsByName("schools", "Kosta Conev", "name");
	 } catch (SQLException e) {
	 assertThat(e.getClass(), equalTo(SQLException.class));
	 }
	 }
}
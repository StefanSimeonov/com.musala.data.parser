package com.musala.database.web.spa.parser.test.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.musala.database.web.spa.spring.parser.model.IDbConnector;
import com.musala.database.web.spa.spring.parser.model.IQueryable;
import com.musala.database.web.spa.spring.parser.model.impl.MySqlDbConnector;
import com.musala.database.web.spa.spring.parser.model.impl.MySqlQueryBuilder;

public class QueryTest {
	IQueryable query;
	Statement statement;

	@Before
	public void Initialize() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		IDbConnector connect = new MySqlDbConnector().buildWithAdditionalCredentials("localhost", "schools", "root", "");
		statement = connect.getStatement();
		query = new MySqlQueryBuilder();
	}

	@Test
	public void testgetAllRecordsWithWrongTableName() throws NullPointerException, SQLException {
		try {
			query.getAllRecords("school", statement);
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void testgetAllRecords() throws NullPointerException, SQLException {
		Statement stat = Mockito.mock(Statement.class);
		ResultSet set = Mockito.mock(ResultSet.class);
		Mockito.when(stat.executeQuery("select * from schools")).thenReturn(set);
		query.getAllRecords("schools", stat);
		Mockito.verify(stat, times(1)).executeQuery("select * from schools");
	}

	@Test
	public void testgetAllRecordsWithWrongMockitoVerifyObj() throws NullPointerException, SQLException {
		Statement stat = Mockito.mock(Statement.class);
		ResultSet set = Mockito.mock(ResultSet.class);
		Mockito.when(stat.executeQuery("select * from schools")).thenReturn(set);
		query.getAllRecords("schools", stat);
		Mockito.verify(stat, Mockito.never()).executeQuery("select * from pesho");
	}

	@Test
	public void testgetRecordsByIdWithWrongTableName() throws NullPointerException, SQLException {
		try {
			query.getRecordById("school", statement, "1");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void testgetRecordsById() throws NullPointerException, SQLException {
		Statement stat = Mockito.mock(Statement.class);
		ResultSet set = Mockito.mock(ResultSet.class);
		Mockito.when(stat.executeQuery("select * from schools where Id=1")).thenReturn(set);
		query.getRecordById("schools", stat, "1");
		Mockito.verify(stat, times(1)).executeQuery("select * from schools where Id=1");
	}

	@Test
	public void testgetRecordsByIdWithWrongMockitoVerifyObj() throws NullPointerException, SQLException {
		Statement stat = Mockito.mock(Statement.class);
		ResultSet set = Mockito.mock(ResultSet.class);
		Mockito.when(stat.executeQuery("select * from schools where Id=1")).thenReturn(set);
		query.getRecordById("schools", stat, "1");
		Mockito.verify(stat, Mockito.never()).executeQuery("select * from pesho");
	}

	@Test
	public void testgetRecordsByNameWithWrongTableName() throws NullPointerException, SQLException {
		try {
			query.getRecordByName("school", statement, "Ivan Kostov");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void testgetRecordsByName() throws NullPointerException, SQLException {
		Statement stat = Mockito.mock(Statement.class);
		ResultSet set = Mockito.mock(ResultSet.class);
		Mockito.when(stat.executeQuery("select * from schools where name='Ivan'")).thenReturn(set);
		query.getRecordByName("schools", stat, "Ivan");
		Mockito.verify(stat, times(1)).executeQuery("select * from schools where name='Ivan'");
	}

	@Test
	public void testgetRecordsByNameWithWrongMockitoVerifyObj() throws NullPointerException, SQLException {
		Statement stat = Mockito.mock(Statement.class);
		ResultSet set = Mockito.mock(ResultSet.class);
		Mockito.when(stat.executeQuery("select * from schools where name='Ivan'")).thenReturn(set);
		query.getRecordByName("schools", stat, "Ivan");
		Mockito.verify(stat, Mockito.never()).executeQuery("select * from pesho");
	}

}
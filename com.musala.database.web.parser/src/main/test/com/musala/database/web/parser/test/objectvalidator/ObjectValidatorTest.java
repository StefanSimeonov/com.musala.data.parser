package com.musala.database.web.parser.test.objectvalidator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.InputMismatchException;

import org.junit.Test;

import com.musala.database.web.parser.helper.ObjectValidator;
import com.musala.database.web.parser.helper.SchoolClassException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLNonTransientConnectionException;

public class ObjectValidatorTest extends ObjectValidator {

	@Test
	public void testcheckAndMoveCursorToNextPosition() throws SQLException {
		ResultSet set = mock(ResultSet.class);
		when(set.next()).thenThrow(SQLException.class);
		try {
			checkAndMoveCursorToNextPosition(set, "Smth wrong");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void testCheckForDatabaseDrivers() {
		try {
			checkForDatabaseDrivers("some drivers here");
		} catch (ClassNotFoundException e) {
			assertThat(e.getClass(), equalTo(ClassNotFoundException.class));
		}
	}

	@Test
	public void testCheckForProperDatabaseDrivers() {
		Exception e = null;
		try {
			checkForDatabaseDrivers("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			e = ex;
		}
		assertNull(e);
	}

	@Test
	public void testCheckForPropertyNameValidaty() throws SQLException {
		ResultSet set = mock(ResultSet.class);
		when(set.getString("name")).thenReturn("Ivan");
		assertEquals("Ivan", checkForPropertyNameValidaty(set, "name", "some exc message"));
	}

	@Test
	public void testCheckForInputMissmatchForMissmatch() {
		try {
			checkForInputMissmatch("Ivan", new ObjectValidator(), "Smth wrong");
		} catch (InputMismatchException e) {
			assertThat(e.getClass(), equalTo(InputMismatchException.class));
		}
	}

	@Test
	public void testCheckForInputMissmatchForProperObjectMatching() {
		Exception ex = null;
		try {
			checkForInputMissmatch("Ivan", "Pesho", "");
		} catch (Exception e) {
			ex = e;
		}
		assertNull(ex);
	}

	@Test
	public void testCheckForPropertyNameValidatyWithWrongMocking() throws SQLException {
		ResultSet set = mock(ResultSet.class);
		when(set.getString("name")).thenReturn("Ivaaaan");
		assertNotEquals("Ivan", checkForPropertyNameValidaty(set, "name", "some exc message"));
	}

	@Test
	public void testCheckForPropertyNameValidatyForSqlExc() throws SQLException {
		ResultSet set = mock(ResultSet.class);
		try {
			checkForPropertyNameValidaty(set, "name", "some exc message");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void testCheckForSchoolClassException() {
		try {
			checkForSchoolClassException("schoolclasses", "Error Message");
		} catch (SchoolClassException e) {
			assertThat(e.getClass(), equalTo(SchoolClassException.class));
		}
	}

	@Test
	public void testCheckForSchoolClassExceptionPassing() {
		Exception ex = null;
		try {
			checkForSchoolClassException("schools", "");
		} catch (SchoolClassException e) {
			ex = e;
		}
		assertNull(ex);
	}

	@Test
	public void testCheckForSQLQuery() throws SQLException {
		Statement statement = mock(Statement.class);
		when(statement.executeQuery("select * from the base")).thenReturn(new ResultSet(0, 0, null, null));
		assertNotNull(checkForSQLQuery("select * from the base", statement));
	}

	@Test
	public void testCheckForSQLQueryWithWrongMocking() throws SQLException {
		Statement statement = mock(Statement.class);
		when(statement.executeQuery("")).thenReturn(new ResultSet(0, 0, null, null));
		try {
			assertNotNull(checkForSQLQuery("select * from the base", statement));
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testCheckForSQLQueryForSQLException() throws NullPointerException, SQLException {
		try {
			assertNotNull(checkForSQLQuery("select * from the base", new Statement(null, "")));
		} catch (MySQLNonTransientConnectionException e) {
			assertThat(e.getClass(), equalTo(MySQLNonTransientConnectionException.class));
		}
	}

	@Test
	public void testCheckForSQLConnectionException() {
		try {
			checkForSQLConnectionException(null, null, null, null);
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void testCheckForSQLConnectionExceptionForPassing() {
		Exception ex = null;
		try {
			checkForSQLConnectionException("jdbc:mysql://localhost/schools", "Some exc message here", "root", "");
		} catch (SQLException e) {
			ex = e;
		}
		assertNull(ex);
	}

	@Test
	public void testCheckForSQLStatementExceptionForNullPointerExc() throws SQLException {
		try {
			checkForSQLStatementException(null, "");
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testCheckForSQLStatementExceptionForSQLExc() throws SQLException {
		Connection con = mock(Connection.class);
		when(con.createStatement()).thenThrow(SQLException.class);
		try {
			checkForSQLStatementException(con, "");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void testCheckForSQLStatementExceptionForProperPass() throws SQLException {
		Statement stat = mock(Statement.class);
		Connection con = mock(Connection.class);
		Exception ex = null;
		when(con.createStatement()).thenReturn(stat);
		try {
			checkForSQLStatementException(con, "");
		} catch (SQLException e) {
			ex = e;
		}
		assertNull(ex);
	}

	@Test
	public void testCheckIfObjectIsNull() {
		try {
			checkIfObjectIsNull(null, "");
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testCheckIfObjectIsNullForProperPass() {
		Exception ex = null;
		try {
			checkIfObjectIsNull(new String(), "");
		} catch (NullPointerException e) {
			ex = e;
		}
		assertNull(ex);
	}
}

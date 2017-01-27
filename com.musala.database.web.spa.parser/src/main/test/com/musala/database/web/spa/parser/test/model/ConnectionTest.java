package com.musala.database.web.spa.parser.test.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;

import com.musala.database.web.spa.parser.model.impl.MySqlDbConnector;

public class ConnectionTest extends MySqlDbConnector {

	@Test
	public void testBuildMethodForProperCon() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		MySqlDbConnector con = buildWithAdditionalCredentials("localhost", "schools", "root", "");
		assertNotNull(con);
	}

	@Test
	public void testBuildMethodForWrongCredentials() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			buildWithAdditionalCredentials("smth", "schools", "rosot", "");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

	@Test
	public void testBuildMethodForMissingDrivers() throws ClassNotFoundException {
		try {
			buildWithAdditionalCredentials("localhost", "schools", "root", "");
		} catch (Exception e) {
			assertThat(e.getClass(), equalTo(ClassNotFoundException.class));
		}
	}

	@Test
	public void testgetConString() {
		assertEquals("jdbc:mysql://null/null", this.getConServerName());
	}

	@Test
	public void testBuild() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			build("smth", "schools");
		} catch (SQLException e) {
			assertThat(e.getClass(), equalTo(SQLException.class));
		}
	}

}

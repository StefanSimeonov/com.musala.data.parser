package com.musala.database.web.parser.test.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.musala.database.web.parser.model.impl.MyWebInputDbProvider;

@RunWith(MockitoJUnitRunner.class)
public class InputTest {

	@Mock
	HttpServletRequest request;

	@InjectMocks
	MyWebInputDbProvider input = new MyWebInputDbProvider(request);

	@Test
	public void testConnection() {
		when(request.getParameter("ServerName")).thenReturn("localhost");
		assertEquals("localhost", input.getConnection());
	}

	@Test
	public void testDatabase() {
		when(request.getParameter("DatabaseName")).thenReturn("smth");
		assertEquals("smth", input.getDatabase());
	}

	@Test
	public void testPassword() {
		when(request.getParameter("Password")).thenReturn("root");
		assertEquals("root", input.getPassword());
	}

	@Test
	public void testUsername() {
		when(request.getParameter("Username")).thenReturn("coolguy");
		assertEquals("coolguy", input.getUserName());
	}

	@Test
	public void testTableName() {
		when(request.getParameter("tableName")).thenReturn("getSmth");
		assertEquals("getSmth", input.getQueryTableName());
	}

	@Test
	public void testQueryType() {
		when(request.getParameter("queriesType")).thenReturn("getSmth");
		assertEquals(null, input.getQueryType());
	}

	@Test
	public void testRecordPropertiesName() {
		when(request.getParameter("properties")).thenReturn("gosho ivan nasko");
		String[] massToCheck = input.getRecordPropertiesName();
		String[] checker = new String[] { "gosho", "ivan", "nasko" };
		try {
			for (int i = 0; i < massToCheck.length; i++) {
				if (!massToCheck[i].equals(checker[i])) {
					throw new Exception();
				}
			}
		} catch (Exception e) {
			assertEquals(true, false);
		}
		assertEquals(true, true);
	}

}

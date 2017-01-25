package com.musala.database.web.parser.test.servletgetter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.musala.database.web.parser.servlet.getter.ServletGetter;

public class TestServletGetter extends ServletGetter {

	@Test
	public void testdoGet() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		File f = File.createTempFile("text", ".txt");
		PrintWriter writer = new PrintWriter(f);
		when(response.getWriter()).thenReturn(writer);
		doGet(request, response);
		writer.flush();
		Scanner scan = new Scanner(f);
		assertEquals(
				"<html><body><form action='SecondGetter' method='GET'><p>Enter type of query your want: </p><select name='queriesType'><option value='getAllRecords'>getAllRecords</option><option value='getRecordById'>getRecordById</option><option value='getRecordByName' >getRecordByName</option><option value='closeTheQuery'>close</option></select><p>Enter table you want: </p><select name='tableName'><option value='schools'>schools</option><option value='schoolclasses'>school classes</option><option value='students' >students</option><option value='teachers' >teachers</option></select><p>Please enter the record's propeties you want, splited by space: </p><input type='text' name='properties'> <br><br><input type='submit'></form>",
				scan.nextLine());
		scan.close();
	}

}

package com.musala.database.web.parser.test.servlet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.musala.database.web.parser.servlet.Servlet;

public class TestServlet extends Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -192704657641609144L;

	@Test
	public void TestDoGet() throws FileNotFoundException, IOException, ServletException {
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		File f = File.createTempFile("text", ".txt");
		PrintWriter writer = new PrintWriter(f);
		when(response.getWriter()).thenReturn(writer);
		doGet(request, response);
		writer.flush();
		Scanner scan = new Scanner(f);
		assertEquals(
				"<html><body><form id='firstForm' action='ServletGetter' method='GET'>Please enter the server name:<br><input type='text' name='ServerName' value='localhost'><br> Please enter database name:<br><input type='text' name='DatabaseName' value='schools'><br>Please enter username:<br><input type='text' name='Username' value='root'><br>Please enter user's password:<br><input type='text' name='Password' value=''><br><br><input type='submit' value='Submit'>",
				scan.nextLine());
		scan.close();
	}

	@Test
	public void TestDoGett() throws IOException, ServletException {
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("error")).thenReturn("true");
		when(request.getParameter("errorMessage")).thenReturn("write this message");
		File f = File.createTempFile("text", ".txt");
		PrintWriter writer = new PrintWriter(f);
		when(response.getWriter()).thenReturn(writer);
		doGet(request, response);
		writer.flush();
		Scanner scan = new Scanner(f);
		assertEquals(
				"<html><body><form id='firstForm' action='ServletGetter' method='GET'>Please enter the server name:<br><input type='text' name='ServerName' value='localhost'><br> Please enter database name:<br><input type='text' name='DatabaseName' value='schools'><br>Please enter username:<br><input type='text' name='Username' value='root'><br>Please enter user's password:<br><input type='text' name='Password' value=''><br><br><p style=\"color:red;\">write this message</p><input type='submit' value='Submit'>",
				scan.nextLine());
		scan.close();
	}

}

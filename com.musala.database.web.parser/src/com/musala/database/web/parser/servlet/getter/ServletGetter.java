package com.musala.database.web.parser.servlet.getter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.musala.database.web.parser.model.impl.MySqlDbConnector;
import com.musala.database.web.parser.model.impl.MySqlWebDbEngine;

/**
 * Servlet implementation class ServletGetter
 */
@WebServlet("/ServletGetter")
public class ServletGetter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletGetter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MySqlWebDbEngine engine = MySqlWebDbEngine.getInstance(response, request);
		engine.initialize();
		response.getWriter().append(
				"<html><body><form action='SecondGetter' method='GET'><p>Enter type of query your want: </p><select name='queriesType'><option value='getAllRecords'>getAllRecords</option><option value='getRecordById'>getRecordById</option><option value='getRecordByName' >getRecordByName</option><option value='closeTheQuery'>close</option></select><p>Enter table you want: </p><select name='tableName'><option value='schools'>schools</option><option value='schoolclasses'>school classes</option><option value='students' >students</option><option value='teachers' >teachers</option></select><p>Please enter the record's propeties you want, splited by space: </p><input type='text' name='properties'> <br><br><input type='submit'></form>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

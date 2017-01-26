package com.musala.database.web.parser.servlet.second.getter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.musala.database.web.parser.model.impl.MySqlWebDbEngine;

/**
 * Servlet implementation class SecondGetter
 */
@WebServlet("/SecondGetter")
public class SecondGetter extends HttpServlet {

	private static final long serialVersionUID = 6135655281703848916L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SecondGetter() {
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
		engine.startQuering();
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

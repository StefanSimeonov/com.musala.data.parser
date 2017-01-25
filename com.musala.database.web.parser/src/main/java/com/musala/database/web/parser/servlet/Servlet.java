package com.musala.database.web.parser.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean errorParam = Boolean.valueOf(request.getParameter("error"));
		String errorMessage = request.getParameter("errorMessage");
		PrintWriter writer=response.getWriter();
				writer.append("<html><body><form id='firstForm' action='ServletGetter' method='GET'>Please enter the server name:<br><input type='text' name='ServerName' value='localhost'><br> Please enter database name:<br><input type='text' name='DatabaseName' value='schools'><br>Please enter username:<br><input type='text' name='Username' value='root'><br>Please enter user's password:<br><input type='text' name='Password' value=''><br><br>"
						+ (errorParam == true ? "<p style=\"color:red;\">" + errorMessage + "</p>" : "")
						+ "<input type='submit' value='Submit'>");
				
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

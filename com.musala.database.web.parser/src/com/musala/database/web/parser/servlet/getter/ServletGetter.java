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
		if (request.getParameter("Server name").equals("alex")) {
			response.sendRedirect("http://localhost:8080/com.musala.database.web.parser/Servlet?error=true");
		} else {
			String serverName = request.getParameter("Server name");
			String databaseName = request.getParameter("Database name");
			String userName = request.getParameter("Username");
			String password = request.getParameter("Password");
			response.getWriter().println("<html><body>");
			response.getWriter().println("<p>" + "The name of the server is:" + serverName + "</p>");
			response.getWriter().println("<p>" + "The name of the database is:" + databaseName + "</p>");
			response.getWriter().println("<p>" + "The name of the user is:" + userName + "</p>");
			response.getWriter().println("<p>" + "The password is:" + password + "</p>");
			// response.getWriter().println("</body></html>");
			// try {
			// Class.forName("com.mysql.jdbc.Driver");
			// Connection con = DriverManager.getConnection("jdbc:mysql://" +
			// serverName + "/" + databaseName, userName,
			// password);
			// Statement st = con.createStatement();
			// ResultSet res = st.executeQuery("select * from schools");
			// while(res.next()){
			// String name=res.getString("name");
			// response.getWriter().println("<p>" +name +
			// "</p>");
			// }
			// response.getWriter().println("</body></html>");

			//
			try {
				Class.forName("com.mysql.jdbc.Driver");
				MySqlDbConnector con = new MySqlDbConnector();
				con.buildWithAdditionalCredentials(serverName, databaseName, userName, password);

			} catch (ClassNotFoundException e) {

			} catch (SQLException e) {
				response.sendRedirect("http://localhost:8080/com.musala.database.web.parser/Servlet?error=true&errorMessage=sqlbadconnection");

			}
		}
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

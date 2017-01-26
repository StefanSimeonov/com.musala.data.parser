package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.musala.database.web.parser.model.impl.MySqlWebDbEngine;

/**
 * Servlet implementation class AjaxController
 */
@WebServlet("/AjaxController")
public class AjaxController extends HttpServlet {

	private static final long serialVersionUID = -7948233786480773518L;
	protected static final String FIRST_FUNC_REQUEST = "first";
	protected static final String SECOND_FUNC_REQUEST = "second";
	protected static final String THIRD_FUNC_REQUEST = "third";
	protected static final String FUNC_REQUEST_AS_STRING = "funcRequest";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Displays server initialization message
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		MySqlWebDbEngine con;
		String req = request.getParameter(FUNC_REQUEST_AS_STRING);
		switch (req) {
			case FIRST_FUNC_REQUEST: {
				con = MySqlWebDbEngine.getInstance(response, request);
				con.initialize();
				return;
			}
			case SECOND_FUNC_REQUEST: {
				con = MySqlWebDbEngine.getInstance(response, request);
				con.startQuering();
				return;
			}
			case THIRD_FUNC_REQUEST: {
				con = MySqlWebDbEngine.getInstance(response, request);
				con.startQuering();
				break;
			}
		}
	}
}

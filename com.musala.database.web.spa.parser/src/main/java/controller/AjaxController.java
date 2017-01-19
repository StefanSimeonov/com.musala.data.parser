package controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.musala.database.web.parser.model.impl.MySqlWebDbEngine;

/**
 * Servlet implementation class AjaxController
 */
@WebServlet("/AjaxController")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FIRST_FUNC_REQUEST = "first";
	private static final String SECOND_FUNC_REQUEST = "second";
	private static final String THIRD_FUNC_REQUEST = "third";
	private static final String FUNC_REQUEST_AS_STRING = "funcRequest";

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
		byte[] reqBytes = new byte[request.getContentLength()];
		request.getInputStream().read(reqBytes);

		JsonParser parser = new JsonParser();
		JsonObject reqJson = parser.parse(new String(reqBytes)).getAsJsonObject();

		MySqlWebDbEngine con;
		JsonElement req = reqJson.get(FUNC_REQUEST_AS_STRING);
		switch (req.getAsString()) {
		case FIRST_FUNC_REQUEST: {
			con = MySqlWebDbEngine.getInstance(response, reqJson);
			con.initialize();
			return;
		}
		case SECOND_FUNC_REQUEST: {
			con = MySqlWebDbEngine.getInstance(response, reqJson);
			con.startQuering();
			return;
		}
		case THIRD_FUNC_REQUEST: {
			System.out.println("asdasd");
			con = MySqlWebDbEngine.getInstance(response,  reqJson);
			con.startQuering();
			break;
		}
		}
	}
}

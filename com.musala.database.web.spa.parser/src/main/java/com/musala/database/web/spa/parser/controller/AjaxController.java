package com.musala.database.web.spa.parser.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		JsonObject reqJson = fromPostRequestToJson(request);
		MySqlWebDbEngine con;
		
		try {
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
					con = MySqlWebDbEngine.getInstance(response, reqJson);
					con.startQuering();
					return;
				}
			}
		} catch (Exception e) {
			// fictive
		}
	}


	/**
	 * Processes POST request params into a generic json object
	 * 
	 * @param HttpServletRequest
	 *            request
	 * @return JsonObject
	 * @throws IOException
	 */
	protected JsonObject fromPostRequestToJson(HttpServletRequest request) throws IOException {

		byte[] reqBytes = new byte[request.getContentLength()];
		request.getInputStream().read(reqBytes);// because of the post request
		JsonParser parser = new JsonParser();
		JsonObject reqJson = parser.parse(new String(reqBytes)).getAsJsonObject();

		return reqJson;
	}
}

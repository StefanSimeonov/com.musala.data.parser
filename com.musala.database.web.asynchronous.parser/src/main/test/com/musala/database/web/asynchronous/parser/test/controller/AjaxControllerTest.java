package com.musala.database.web.asynchronous.parser.test.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.musala.database.web.asynchronous.parser.controller.AjaxController;

@RunWith(MockitoJUnitRunner.class)
public class AjaxControllerTest extends AjaxController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5922363153821073498L;
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;

	@InjectMocks
	AjaxControllerTest test;

	@Test
	public void testDoPostWithProperRequest() throws ServletException, IOException {
		when(request.getParameter(FUNC_REQUEST_AS_STRING)).thenReturn(FIRST_FUNC_REQUEST);
		when(response.getWriter()).thenReturn(new PrintWriter("dummyvalue"));
		doPost(request, response);
		verify(response, times(1)).getWriter();
	}

	@Test
	public void testDoPostWithEmptyResponse() throws ServletException, IOException {
		when(request.getParameter(FUNC_REQUEST_AS_STRING)).thenReturn(FIRST_FUNC_REQUEST);
		try {
			doPost(request, response);
		} catch (NullPointerException ex) {
			assertThat(ex.getClass(), equalTo(NullPointerException.class));
		}

	}
}

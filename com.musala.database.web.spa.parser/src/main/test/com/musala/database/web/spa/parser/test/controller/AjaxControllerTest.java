package com.musala.database.web.spa.parser.test.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.JsonObject;
import com.musala.database.web.spa.parser.controller.AjaxController;

@RunWith(MockitoJUnitRunner.class)
public class AjaxControllerTest extends AjaxController {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	ServletInputStream inputStream;

	@Test
	public void testFromPostRequestToJson() throws IOException, ServletException {

		ServletInputStream stream = new ServletInputStream() {
			InputStream stream;
			byte[] bytemass = new byte[250];

			public void initialize() throws IOException {
				String source = "{\"serverName\":\"localhost\",\"databaseName\":\"schools\",\"username\":\"root\",\"password\":\"\",\"funcRequest\":\"first\"}";
				stream = IOUtils.toInputStream(source, "UTF-8");
				int i, j = 0;
				byte[] masa = new byte[150];
				while ((i = stream.read()) != -1) {
					byte b = (byte) i;
					bytemass[j] = b;
					j++;
				}
			}

			public int read(byte[] filledbyte) throws IOException {
				initialize();
				for (int i = 0; i < filledbyte.length; i++) {
					filledbyte[i] = bytemass[i];
				}
				return 0;
			}

			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		when(request.getContentLength()).thenReturn(105);
		when(request.getInputStream()).thenReturn(stream);
		JsonObject obj = fromPostRequestToJson(request);
		String compare = obj.toString();
		if (compare.equals(
				"{\"serverName\":\"localhost\",\"databaseName\":\"schools\",\"username\":\"root\",\"password\":\"\",\"funcRequest\":\"first\"}")) {
			assertEquals(true, true);
		} else
			assertEquals(true, false);
	}

}

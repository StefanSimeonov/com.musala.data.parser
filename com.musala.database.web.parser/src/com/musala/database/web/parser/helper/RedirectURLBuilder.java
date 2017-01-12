package com.musala.database.web.parser.helper;

public class RedirectURLBuilder {
private final static String DEFAULT_PROTOCOL="http://";
private final static String DEFAULT_HOST="localhost";
private final static String DEFAULT_PORT=":8080";
private final static String DEFAULT_PAGE_NAME="/com.musala.database.web.parser/Servlet?error=true&errorMessage=";
	public static String getPath(){
		String url=DEFAULT_PROTOCOL+DEFAULT_HOST+DEFAULT_PORT+DEFAULT_PAGE_NAME;
		return url;
	}
}

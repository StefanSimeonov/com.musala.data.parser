package com.musala.database.web.parser.test.servletgetter;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	@Test
	public void test() {
		Result run = JUnitCore.runClasses(TestServletGetter.class);
		for (Failure fail : run.getFailures()) {
			System.out.println(fail.getTestHeader());
		}
		if (run.wasSuccessful()) {
			System.out.println("All test are passed");
		}
	}

}
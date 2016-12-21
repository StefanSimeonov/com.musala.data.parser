package com.musala.xml.parser.executable.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MainTest.class);
    
        for (Failure failure : result.getFailures()) {
            String failureMehod=failure.getTestHeader();
            System.out.println("The test method is:" + failureMehod.substring(0, failureMehod.indexOf('(')));
            System.out.println(failure.getMessage());
      System.out.println("-----------------------------------------------------");
        }
if(result.wasSuccessful()){
    System.out.println("All tests are passed");
}
    }

}

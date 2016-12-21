package com.musala.xml.parser.executable.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class ObjectValidator {
    public static void checkIfObjectIsNull(Object obj, String exceptionMessage) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException(exceptionMessage);
        }
    }

    public static void checkIfFileExists(File obj, String exceptionMessage) throws FileNotFoundException {
        if (!obj.exists()) {
            throw new FileNotFoundException(exceptionMessage);
        }
    }

    public static void checkCreateValidJaxb(String firstClassName, String secondClassName, String exceptionMessage)
            throws JAXBException {
        try {
            JAXBContext.newInstance(Class.forName(firstClassName), Class.forName(secondClassName));

        } catch (JAXBException ex) {
            throw new JAXBException(exceptionMessage);
        } catch (ClassNotFoundException e) {

        }

    }

    public static void checkForCast(Object objectToCast, Object castingObject, String exceptionMessage) {
        try {
            objectToCast.getClass().cast(castingObject);
        } catch (ClassCastException ex) {
            throw new ClassCastException(exceptionMessage);
        }
    }

    public static void checkIfStringMatch(String input,String pattern) throws WrongStringInputException {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(input);
        if (!matcher.find()) {
            throw new WrongStringInputException();
        }

    }

}

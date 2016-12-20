package com.musala.xml.parser.executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.musala.xml.parser.executable.model.School;
import com.musala.xml.parser.executable.model.SchoolObject;
import com.musala.xml.parser.executable.model.Student;
import com.musala.xml.parser.executable.model.Teacher;

public class Main {

    public static void main(String[] args) {

        File configFile = null;
        try {
            configFile = new File(args[0]);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        JAXBContext jaxb = null;
        Unmarshaller jaxbUnmarsheller = null;
        try {
            jaxb = JAXBContext.newInstance(School.class, Student.class);
            jaxbUnmarsheller = jaxb.createUnmarshaller();
        } catch (JAXBException ex) {
            System.err.println(ex.getMessage());
        }

        School mySchool = null;
        try {
            mySchool = (School) jaxbUnmarsheller.unmarshal(configFile);
        } catch (NullPointerException ex) {
            System.err.println("The school is empty");
        } catch (JAXBException ex) {
            System.err.println(ex.getMessage());
        }
        String propertyFileOutputName = args[1].substring(0, args[1].indexOf('.'))
                + Constants.PROPERTY_FILE_STRING_EXTENTION;

        PrintWriter writer = null;
        PrintWriter keyValueWriter = null;
        try {
            writer = new PrintWriter(args[1], "UTF-8");
            keyValueWriter = new PrintWriter(propertyFileOutputName);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }

        TreeMap<String, String> keyValuePair = new TreeMap<>();
        Print(mySchool, writer, keyValuePair, keyValueWriter);
    }

    protected static ClassNumberText turnClassNumberIntoText(int numberOfClass) {
        ClassNumberText word;
        switch (numberOfClass) {
            case 0:
                word = ClassNumberText.first;
                break;
            case 1:
                word = ClassNumberText.second;
                break;
            case 2:
                word = ClassNumberText.third;
                break;
            case 3:
                word = ClassNumberText.fourth;
                break;
            case 4:
                word = ClassNumberText.fifth;
                break;
            case 5:
                word = ClassNumberText.sixth;
                break;
            case 6:
                word = ClassNumberText.seventh;
                break;
            case 7:
                word = ClassNumberText.eighth;
                break;
            case 8:
                word = ClassNumberText.ninth;
                break;
            default:
                word = ClassNumberText.nth;
        }

        return word;
    }

    protected static String getTheObjectProp(SchoolObject currentObj, int numberOfProp) {
        switch (numberOfProp) {
            case 0:
                return currentObj.getName();
            case 1:
                return Integer.toString(currentObj.getAge());
            case 2:
                return currentObj.getReferenceNumber();
        }
        if (currentObj instanceof Student) {
            return Integer.toString(((Student) currentObj).getFacultyNumber());
        }
        return null;
    }

    protected static void buildKeyValue(int j, SchoolObject currentObj, TreeMap<String, String> keyValuePair) {
        List<String> props = new ArrayList<>();
        Collections.addAll(props, "name", "age", "referenceNumber", "facultyNumber");
        String nameOfObject = currentObj.getClass().getName();
        nameOfObject = nameOfObject.substring(nameOfObject.lastIndexOf('.') + 1);
        StringBuilder keyBuild = new StringBuilder();
        keyBuild.append(nameOfObject);
        keyBuild.append(".");
        keyBuild.append(j + 1 + ".");
        for (int i = 0; i < (currentObj instanceof Student ? props.size() : props.size() - 1); i++) {
            keyBuild.append(props.get(i));
            String currentValue = null;
            try {
                currentValue = getTheObjectProp(currentObj, i);
            } catch (NullPointerException ex) {
                System.err.println("The object is neither Student nor Teacher");
            }

            keyValuePair.put(keyBuild.toString(), currentValue);
            int propsize = props.get(i).length();
            keyBuild.replace(keyBuild.length() - propsize, keyBuild.length(), "");
        }
    }

    protected static void keyValueFilePrint(TreeMap<String, String> keyValuePair, PrintWriter keyValueWriter) {
        Iterator<Entry<String, String>> itr = keyValuePair.entrySet().iterator();
        try {
            while (itr.hasNext()) {
                Entry<String, String> temp = itr.next();
                keyValueWriter.println(temp.getKey() + "=" + temp.getValue());
            }
        } catch (Exception ex) {
            System.err.println("Something Wrong while iterating");
        }
    }

    protected static void Print(School myschool, PrintWriter writer, TreeMap<String, String> keyValuePair,
            PrintWriter keyValueWriter) {
        int numberOfClasses = myschool.getSchoolClasses().size();
        writer.printf("The school name is: %s!%nThere %s %d school class%s.%n", myschool.getName(),
                numberOfClasses > 1 ? "are" : "is", numberOfClasses, numberOfClasses == 1 ? "" : "es");
        printClassesData(myschool, numberOfClasses, writer, keyValuePair);
        writer.close();
        keyValueFilePrint(keyValuePair, keyValueWriter);
        keyValueWriter.close();
    }

    protected static void printClassesData(School myschool, int numberOfClasses, PrintWriter writer,
            TreeMap<String, String> keyValuePair) {
        for (int currentClassNum = 0; currentClassNum < numberOfClasses; currentClassNum++) {
            printObject(myschool, currentClassNum, writer, keyValuePair, "Student");
            printObject(myschool, currentClassNum, writer, keyValuePair, "Teacher");
        }
    }

    protected static void printObject(School myschool, int currentClassNum, PrintWriter writer,
            TreeMap<String, String> keyValuePair, String typeOfObject) {
        String classNum = turnClassNumberIntoText(currentClassNum).toString();
        writer.printf("The %s class has %d %s: %n", classNum,
                myschool.getSchoolClasses().get(currentClassNum).getStudentsReference().size(), typeOfObject);
        for (int schoolObject = 0; schoolObject < (typeOfObject.equals("Student")
                ? myschool.getSchoolClasses().get(currentClassNum).getStudentsReference().size()
                : Constants.NUMBER_OF_TEACHERS_IN_SIMPLE_SCHOOLCLASS); schoolObject++) {
            String currentReference;
            if (typeOfObject.equals("Student")) {
                currentReference = myschool.getSchoolClasses().get(currentClassNum).getStudentsReference()
                        .get(schoolObject);
            } else {
                currentReference = myschool.getSchoolClasses().get(currentClassNum).getTeacherReference();
            }
            SchoolObject currentObject = null;
            try {
                currentObject = searchObjectByReference(currentReference, myschool, typeOfObject);
            } catch (NullPointerException ex) {
                System.out.println("There isnt any student with this reference number");
            }
            writer.printf("%s is %d years old ", currentObject.getName().toString(), currentObject.getAge());
            if (currentObject instanceof Student) {
                writer.printf("with fac. number %d.%n", ((Student) currentObject).getFacultyNumber());
            }
            buildKeyValue(schoolObject, currentObject, keyValuePair);
        }
    }

    protected static SchoolObject searchObjectByReference(String objectReference, School myschool,
            String typeOfPerson) {
        switch (typeOfPerson) {
            case Constants.STUDENT_AS_STRING:
                List<Student> students = myschool.getStudents().getStudent();
                return checkReferenceExistence(students, objectReference);

            case Constants.TEACHER_AS_STRING: {
                List<Teacher> teachers = myschool.getTeachers().getTeacher();
                return checkReferenceExistence(teachers, objectReference);
            }
        }
        return null;
    }

    protected static SchoolObject checkReferenceExistence(Object ListOfObjects, String objectReference) {
        @SuppressWarnings("unchecked")
        List<SchoolObject> checkList = (List<SchoolObject>) ListOfObjects;
        for (int i = 0; i < checkList.size(); i++) {
            if (objectReference.equals(checkList.get(i).getReferenceNumber())) {
                return checkList.get(i);
            }
        }
        return null;
    }

}

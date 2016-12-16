package com.musala.xml.parser.executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.musala.xml.model.executable.School;
import com.musala.xml.model.executable.Student;
import com.musala.xml.model.executable.Teacher;

public class Main {

    public static void main(String[] args) throws IOException, JAXBException {

        ClassLoader classload = Main.class.getClassLoader();
        File configFile = new File(args[0]);

        JAXBContext jaxb = JAXBContext.newInstance(School.class);
        Unmarshaller jaxbUnmarsheller = jaxb.createUnmarshaller();
        School mySchool = (School) jaxbUnmarsheller.unmarshal(configFile);
        Properties keyValuePair = new Properties();
        PrintWriter keyValueWriter = new PrintWriter(args[2]);
        Print(mySchool, args[1], keyValuePair, keyValueWriter);
    }

    private static void Print(School myschool, String outputName, Properties keyValuePair, PrintWriter keyValueWriter)
            throws IOException {
        PrintWriter writer = new PrintWriter(outputName, "UTF-8");
        int numberOfClasses = myschool.getSchoolClasses().size();
        writer.printf("The school name is: %s!%n There %s %d school classes.%n", myschool.getName(),
                numberOfClasses > 1 ? "are" : "is", numberOfClasses);
        writer.printf("The school name is: %s!%n There %s %d school classes.%n", myschool.getName(),
                numberOfClasses > 1 ? "are" : "is", numberOfClasses);
        printClassesData(myschool, numberOfClasses, writer, keyValuePair, keyValueWriter);
        writer.close();
        keyValueWriter.close();
    }

    private static void printClassesData(School myschool, int numberOfClasses, PrintWriter writer,
            Properties keyValuePair, PrintWriter keyValueWriter) {
        for (int currentClassNum = 0; currentClassNum < numberOfClasses; currentClassNum++) {
            printStudents(myschool, currentClassNum, writer, keyValuePair, keyValueWriter);
            printTeacher(myschool, currentClassNum, writer, keyValuePair, keyValueWriter);
        }
    }

    private static void printStudents(School myschool, int currentClassNum, PrintWriter writer, Properties keyValuePair,
            PrintWriter keyValueWriter) {
        String classNum = turnClassNumberIntoText(currentClassNum).toString();
        writer.printf("The %s class have %d students: %n", classNum,
                myschool.getSchoolClasses().get(currentClassNum).getStudentsReference().size());
        for (int j = 0; j < myschool.getSchoolClasses().get(currentClassNum).getStudentsReference().size(); j++) {
            String currentReference = myschool.getSchoolClasses().get(currentClassNum).getStudentsReference().get(j);
            Student currentStudent = null;
            try {
                currentStudent = (Student) searchObjectByReference(currentReference, myschool, "Student");
            } catch (NullPointerException ex) {
System.out.println("There isnt any student with this reference number");
            }
            writer.printf("%s is %d years old with fac. number %s.%n", currentStudent.getName(),
                    currentStudent.getAge(), currentStudent.getFacultyNumber());
            KeyValueExecuting(j, currentStudent, keyValuePair, keyValueWriter);
        }
    }

    private static void KeyValueExecuting(int j, Object currentObj, Properties keyValuePair,
            PrintWriter keyValueWriter) {
        List<String> props = new ArrayList<>();
        Collections.addAll(props, "name", "age", "referenceNumber", "facultyNumber");
        String nameOfObject = currentObj.getClass().getName();
        nameOfObject = nameOfObject.substring(nameOfObject.lastIndexOf('.') + 1);
        StringBuilder keyBuild = new StringBuilder();
        keyBuild.append(nameOfObject);
        keyBuild.append(".");
        keyBuild.append(j + 1 + ".");
        for (int i = 0; i < props.size(); i++) {
            keyBuild.append(props.get(i));
            keyBuild.append(".");
            System.out.println(keyBuild.toString());
            String currentValue = null;
            try {
                currentValue = getTheObjectProp(currentObj, i, nameOfObject);
            } catch (NullPointerException ex) {
                System.err.println("The object is neither Student nor Teacher");
            }
            keyValuePair.put(keyBuild, currentValue);
            keyValueWriter.println(keyBuild + "=" + currentValue);
            int propsize = props.get(i).length();
            keyBuild.replace(keyBuild.length() - propsize - 1, keyBuild.length(), "");

        }

    }

    private static String getTheObjectProp(Object currentObj, int numberOfProp, String nameOfObject)
            throws NullPointerException {
        switch (nameOfObject) {
            case "Student":
                Student currentObjAsStudent = (Student) currentObj;
                switch (numberOfProp) {
                    case 0:
                        return currentObjAsStudent.getName();
                    case 1:
                        return Integer.toString(currentObjAsStudent.getAge());
                    case 2:
                        return currentObjAsStudent.getReferenceNumber();
                    case 3:
                        return Integer.toString(currentObjAsStudent.getFacultyNumber());
                }
            case "Teacher":
                Teacher currentObjAsTeacher = (Teacher) currentObj;
                switch (numberOfProp) { // i know about the replicate code but we need an interface for Student and
                                        // Teacher to wrap the same props
                    case 0:
                        return currentObjAsTeacher.getName();
                    case 1:
                        return Integer.toString(currentObjAsTeacher.getAge());
                    case 2:
                        return currentObjAsTeacher.getReferenceNumber();
                }
            default:
                break;
        }
        return null;
    }

    private static void printTeacher(School myschool, int currentClassNum, PrintWriter writer, Properties keyValuePair,
            PrintWriter keyValueWriter) {
        String currentTeacherReference = null;
        try {
            currentTeacherReference = myschool.getSchoolClasses().get(currentClassNum).getTeacherReference();
        } catch (IndexOutOfBoundsException ex) {
            System.err.println("The school class dont have any teacher reference ");
        }
        Teacher currentTeacher = null;
        try {
            currentTeacher = (Teacher) searchObjectByReference(currentTeacherReference, myschool, "Teacher");
            KeyValueExecuting(currentClassNum, currentTeacher, keyValuePair, keyValueWriter);

        } catch (NullPointerException ex) {
            System.err.println("There isnt any teacher with this reference number");
        }
        writer.printf("The teacher of the class is %s- %d years old", currentTeacher.getName(),
                currentTeacher.getAge());
    }

    private static Object searchObjectByReference(String objectReference, School myschool, String typeOfPerson)
            throws NullPointerException {
        switch (typeOfPerson) {
            case Constants.STUDENT_AS_STRING:
                List<Student> students = myschool.getStudents().getStudent();
                for (int i = 0; i < students.size(); i++) {
                    if (objectReference.equals(students.get(i).getReferenceNumber())) {
                        return students.get(i);
                    }
                }
                return null;

            case Constants.TEACHER_AS_STRING: {
                List<Teacher> teachers = myschool.getTeachers().getTeacher();
                for (int i = 0; i < teachers.size(); i++) {
                    if (objectReference.equals(teachers.get(i).getReferenceNumber())) {
                        return teachers.get(i);
                    }
                }
                return null;
            }
        }

        return null;
    }

    private static ClassNumberText turnClassNumberIntoText(int numberOfClass) {
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

}

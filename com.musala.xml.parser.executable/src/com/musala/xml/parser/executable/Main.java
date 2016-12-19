package com.musala.xml.parser.executable;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.musala.xml.model.executable.School;
import com.musala.xml.model.executable.Student;
import com.musala.xml.model.executable.Teacher;

public class Main {

    public static void main(String[] args) throws IOException, JAXBException, Exception {

        ClassLoader classload = Main.class.getClassLoader();
        File configFile = null;
        try {
            configFile = new File(args[0]);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        JAXBContext jaxb = null;
        Unmarshaller jaxbUnmarsheller = null;
        try {
            jaxb = JAXBContext.newInstance(School.class);
            jaxbUnmarsheller = jaxb.createUnmarshaller();
        } catch (JAXBException ex) {
            System.err.println(ex.getMessage());
        }

        School mySchool = null;
        try {
            mySchool = (School) jaxbUnmarsheller.unmarshal(configFile);
        } catch (NullPointerException ex) {
            System.err.println("The school is empty");
        }

        TreeMap<String, String> keyValuePair = new TreeMap<>();
        PrintWriter keyValueWriter = new PrintWriter(args[2]);
        Print(mySchool, args[1], keyValuePair, keyValueWriter);
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

    private static String getTheObjectProp(Object currentObj, int numberOfProp, String nameOfObject)
            throws NullPointerException {
        switch (nameOfObject) {
            case "Student":
                Student currentObjAsStudent = null;
                try {
                    currentObjAsStudent = (Student) currentObj;
                } catch (Exception ex) {
                    System.err.println("The cast operation is failed");
                }
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
                Teacher currentObjAsTeacher = null;
                try {
                    currentObjAsTeacher = (Teacher) currentObj;
                } catch (Exception ex) {
                    System.err.println("The cast operation is failed");
                }
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

    private static void KeyValueBuilding(int j, Object currentObj, TreeMap<String, String> keyValuePair) {
        List<String> props = new ArrayList<>();
        Collections.addAll(props, "name", "age", "referenceNumber", "facultyNumber");
        String nameOfObject = currentObj.getClass().getName();
        nameOfObject = nameOfObject.substring(nameOfObject.lastIndexOf('.') + 1);
        StringBuilder keyBuild = new StringBuilder();
        keyBuild.append(nameOfObject);
        keyBuild.append(".");
        keyBuild.append(j + 1 + ".");
        for (int i = 0; i < (nameOfObject.equals("Student") ? props.size() : props.size() - 1); i++) {
            keyBuild.append(props.get(i));
            String currentValue = null;
            try {
                currentValue = getTheObjectProp(currentObj, i, nameOfObject);
            } catch (NullPointerException ex) {
                System.err.println("The object is neither Student nor Teacher");
            }

            keyValuePair.put(keyBuild.toString(), currentValue);
            int propsize = props.get(i).length();
            keyBuild.replace(keyBuild.length() - propsize - 1, keyBuild.length(), "");
        }
    }

    private static void KeyValueFilePrint(TreeMap<String, String> keyValuePair, PrintWriter keyValueWriter) {
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

    private static void Print(School myschool, String outputName, TreeMap<String, String> keyValuePair,
            PrintWriter keyValueWriter) throws IOException {
        PrintWriter writer = new PrintWriter(outputName, "UTF-8");
        int numberOfClasses = myschool.getSchoolClasses().size();
        writer.printf("The school name is: %s!%n There %s %d school classes.%n", myschool.getName(),
                numberOfClasses > 1 ? "are" : "is", numberOfClasses);
        writer.printf("The school name is: %s!%n There %s %d school classes.%n", myschool.getName(),
                numberOfClasses > 1 ? "are" : "is", numberOfClasses);
        printClassesData(myschool, numberOfClasses, writer, keyValuePair);
        writer.close();
        KeyValueFilePrint(keyValuePair, keyValueWriter);
        keyValueWriter.close();
    }

    private static void printClassesData(School myschool, int numberOfClasses, PrintWriter writer,
            TreeMap<String, String> keyValuePair) {
        for (int currentClassNum = 0; currentClassNum < numberOfClasses; currentClassNum++) {
            printStudents(myschool, currentClassNum, writer, keyValuePair);
            printTeacher(myschool, currentClassNum, writer, keyValuePair);
        }
    }

    private static void printStudents(School myschool, int currentClassNum, PrintWriter writer,
            TreeMap<String, String> keyValuePair) {
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

            KeyValueBuilding(j, currentStudent, keyValuePair);
        }
    }

    private static void printTeacher(School myschool, int currentClassNum, PrintWriter writer,
            TreeMap<String, String> keyValuePair) {
        String currentTeacherReference = null;
        try {
            currentTeacherReference = myschool.getSchoolClasses().get(currentClassNum).getTeacherReference();
        } catch (IndexOutOfBoundsException ex) {
            System.err.println("The school class dont have any teacher reference ");
        }
        Teacher currentTeacher = null;
        try {
            currentTeacher = (Teacher) searchObjectByReference(currentTeacherReference, myschool, "Teacher");
            KeyValueBuilding(currentClassNum, currentTeacher, keyValuePair);

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

}

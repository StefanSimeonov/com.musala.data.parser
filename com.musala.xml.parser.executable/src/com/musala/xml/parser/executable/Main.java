package com.musala.xml.parser.executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
        Print(mySchool,args[1]);
    }

    private static void Print(School myschool,String outputName) throws IOException  {
PrintWriter writer=new PrintWriter(outputName,"UTF-8");
        int numberOfClasses = myschool.getSchoolClasses().size();
        writer.printf("The school name is: %s!%n There %s %d school classes.%n", myschool.getName(),
                numberOfClasses > 1 ? "are" : "is", numberOfClasses);
        writer.printf("The school name is: %s!%n There %s %d school classes.%n", myschool.getName(),
                numberOfClasses > 1 ? "are" : "is", numberOfClasses);
        printClassesData(myschool, numberOfClasses,writer);
writer.close();
    }

    private static void printClassesData(School myschool, int numberOfClasses,PrintWriter writer) {
        for (int currentClassNum = 0; currentClassNum < numberOfClasses; currentClassNum++) {
            printStudents(myschool, currentClassNum,writer);
            printTeacher(myschool, currentClassNum,writer);
        }
    }

    private static void printStudents(School myschool, int currentClassNum,PrintWriter writer) {
        String classNum =  turnClassNumberIntoText(currentClassNum).toString();
       writer.printf("The %s class have %d students: %n", classNum,
                myschool.getSchoolClasses().get(currentClassNum).getStudentsReference().size());
        for (int j = 0; j < myschool.getSchoolClasses().get(currentClassNum).getStudentsReference().size(); j++) {
            String currentReference = myschool.getSchoolClasses().get(currentClassNum).getStudentsReference().get(j);
            Student currentStudent = (Student) searchObjectByReference(currentReference, myschool, "Student");
            writer.printf("%s is %d years old with fac. number %s.%n", currentStudent.getName(),
                    currentStudent.getAge(), currentStudent.getFacultyNumber());
        }
    }

    private static void printTeacher(School myschool, int currentClassNum,PrintWriter writer) {
        String currentTeacherReference = myschool.getSchoolClasses().get(currentClassNum).getTeacherReference();
        Teacher currentTeacher = (Teacher) searchObjectByReference(currentTeacherReference, myschool, "Teacher");
        writer.printf("The teacher of the class is %s- %d years old", currentTeacher.getName(),
                currentTeacher.getAge());
    }

    private static Object searchObjectByReference(String objectReference, School myschool, String typeOfPerson) {
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

    private static ClassNumberText  turnClassNumberIntoText(int numberOfClass) {
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

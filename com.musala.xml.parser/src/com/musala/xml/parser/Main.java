package com.musala.xml.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.musala.xml.parser.model.School;
import com.musala.xml.parser.model.Student;
import com.musala.xml.parser.model.Teacher;

public class Main {

    public static void main(String[] args) throws IOException, JAXBException {

        ClassLoader classload = Main.class.getClassLoader();
        File configFile = new File(classload.getResource(Constants.XML_INTERNAL_FILE_PATH).getFile());
        JAXBContext jaxb = JAXBContext.newInstance(School.class);
        Unmarshaller jaxbUnmarsheller = jaxb.createUnmarshaller();
        School mySchool = (School) jaxbUnmarsheller.unmarshal(configFile);
        Print(mySchool);
    }

    private static void Print(School myschool) {

        int numberOfClasses = myschool.getSchoolClasses().size();
        System.out.printf("The school name is: %s!%n There %s %d school class%s.%n", myschool.getName(),
                numberOfClasses > 1 ? "are" : "is", numberOfClasses, numberOfClasses == 1 ? "" : "es");
        printClassesData(myschool, numberOfClasses);

    }

    private static void printClassesData(School myschool, int numberOfClasses) {
        for (int currentClassNum = 0; currentClassNum < numberOfClasses; currentClassNum++) {
            printStudents(myschool, currentClassNum);
            printTeacher(myschool, currentClassNum);
        }
    }

    private static void printStudents(School myschool, int currentClassNum) {
        String classNum = turnClassNumberIntoText(currentClassNum).toString();
        System.out.printf("The %s class has %d students: %n", classNum,
                myschool.getSchoolClasses().get(currentClassNum).getStudentsReference().size());
        for (int j = 0; j < myschool.getSchoolClasses().get(currentClassNum).getStudentsReference().size(); j++) {
            String currentStudentReference = myschool.getSchoolClasses().get(currentClassNum).getStudentsReference()
                    .get(j);
            Student currentStudent = (Student) searchObjectByReference(currentStudentReference, myschool, "Student");
            System.out.printf("%s is %d years old with fac. number %s.%n", currentStudent.getName(),
                    currentStudent.getAge(), currentStudent.getFacultyNumber());
        }
    }

    private static void printTeacher(School myschool, int currentClassNum) {
        Teacher currentTeacher = (Teacher) searchObjectByReference(
                myschool.getSchoolClasses().get(currentClassNum).getTeacherReference(), myschool, "Teacher");
        System.out.printf("The teacher of the class is %s- %d years old", currentTeacher.getName(),
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

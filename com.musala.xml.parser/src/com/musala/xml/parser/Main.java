package com.musala.xml.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.musala.xml.parser.model.School;
import com.musala.xml.parser.model.SchoolListWrapper;
import com.musala.xml.parser.model.Student;
import com.musala.xml.parser.model.Teacher;

/**
 * This program is create to get xml school data information from the "resources" folder, located in the current project
 * and fill up previously created school object with it. The objects is represented on the console.
 * 
 * @author stefan.simeonov
 * @version 1.0
 * @since 20.12.2016
 */
public class Main {

    public static void main(String[] args) throws IOException, JAXBException {
        ClassLoader classload = Main.class.getClassLoader();
        File configFile = new File(classload.getResource(Constants.XML_INTERNAL_FILE_PATH).getFile());
        JAXBContext jaxb = JAXBContext.newInstance(School.class);
        Unmarshaller jaxbUnmarsheller = jaxb.createUnmarshaller();
        School mySchool = (School) jaxbUnmarsheller.unmarshal(configFile);
   //     Print(mySchool);
        classload=Main.class.getClassLoader();
       configFile=new File(classload.getResource(Constants.XML_EXTENTION_FILE_PATH).getFile());
       jaxb=JAXBContext.newInstance(SchoolListWrapper.class);
        jaxbUnmarsheller=jaxb.createUnmarshaller();
        SchoolListWrapper schools= (SchoolListWrapper) jaxbUnmarsheller.unmarshal(configFile);
     PrintSchools(schools);
    }
protected static void PrintSchools(SchoolListWrapper schools){
	int numberOfSchools=schools.getSchools().size();
	for (int i = 0; i < numberOfSchools; i++) {
		School currentSchool=schools.getSchools().get(i);
		String schoolNumAsString=turnClassNumberIntoText(i).toString();
		int numberOfClasses=currentSchool.getSchoolClasses().size();
		System.out.printf(MessageProvider.STRING_SCHOOLS_INFO.getValue(),schoolNumAsString, currentSchool.getName(),
                numberOfClasses > 1 ? "are" : "is", numberOfClasses, numberOfClasses == 1 ? "" : "es");
        printClassesData(currentSchool, numberOfClasses);
	}
}
    protected static void Print(School myschool) {
        int numberOfClasses = myschool.getSchoolClasses().size();
        System.out.printf(MessageProvider.STRING_SCHOOL_INFO.getValue(), myschool.getName(),
                numberOfClasses > 1 ? "are" : "is", numberOfClasses, numberOfClasses == 1 ? "" : "es");
        printClassesData(myschool, numberOfClasses);

    }

    protected static void printClassesData(School myschool, int numberOfClasses) {
        for (int currentClassNum = 0; currentClassNum < numberOfClasses; currentClassNum++) {
            printStudents(myschool, currentClassNum);
            printTeacher(myschool, currentClassNum);
        }
    }

    protected static void printStudents(School myschool, int currentClassNum) {
        String classNum = turnClassNumberIntoText(currentClassNum).toString();
        List<String> studentReferences = myschool.getSchoolClasses().get(currentClassNum).getStudentsReference();
        System.out.printf(MessageProvider.STRING_NUMBER_OF_STUDENTS_INFO.getValue(), classNum,
                studentReferences.size());

        for (int j = 0; j < studentReferences.size(); j++) {
            String currentStudentReference = studentReferences.get(j);
            Student currentStudent = (Student) searchPersonByReference(currentStudentReference, myschool,
                    Constants.STUDENT_AS_STRING);
            System.out.printf(MessageProvider.STRING_STUDENTS_INFO.getValue(), currentStudent.getName(),
                    currentStudent.getAge(), currentStudent.getFacultyNumber());
        }
    }

    protected static void printTeacher(School myschool, int currentClassNum) {
        Teacher currentTeacher = (Teacher) searchPersonByReference(
                myschool.getSchoolClasses().get(currentClassNum).getTeacherReference(), myschool,
                Constants.TEACHER_AS_STRING);
        System.out.printf(MessageProvider.STRING_TEACHER_INFO.getValue(), currentTeacher.getName(),
                currentTeacher.getAge());
    }

    protected static Object searchPersonByReference(String objectReference, School myschool, String typeOfPerson) {
        switch (typeOfPerson) {
            case Constants.STUDENT_AS_STRING:
                List<Student> students = myschool.getStudents().getStudent();
                for (int i = 0; i < students.size(); i++) {
                    if (objectReference.equals(students.get(i).getReferenceNumber())) {
                        return students.get(i);
                    }
                }
            case Constants.TEACHER_AS_STRING: {
                List<Teacher> teachers = myschool.getTeachers().getTeacher();
                for (int i = 0; i < teachers.size(); i++) {
                    if (objectReference.equals(teachers.get(i).getReferenceNumber())) {
                        return teachers.get(i);
                    }
                }
            }
            default:
                return null;
        }

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

}
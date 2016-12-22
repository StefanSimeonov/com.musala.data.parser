package com.musala.xml.parser.executable;

import java.io.File;
import java.io.FileNotFoundException;
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

import com.musala.xml.parser.executable.helpers.ObjectValidator;
import com.musala.xml.parser.executable.helpers.WrongStringInputException;
import com.musala.xml.parser.executable.model.School;
import com.musala.xml.parser.executable.model.SchoolObject;
import com.musala.xml.parser.executable.model.Student;
import com.musala.xml.parser.executable.model.Teacher;

/**
 * This program is create to get xml school data information like first argument given from command line or run
 * configuration's argument, fill pre-made school object with it. The information is set towards second given argument
 * in two formats. First is .txt with descriptive school information, second is .property file with key-value data.
 *  The key is in format: {TypeOfSchoolPerson}.{NumberOfThisPersonInTheCurrentSchoolClass}.{Person'sProperty}
 * The value give the information of the current person's property e.g.: age,name...and so on.  
 * 
 * @author stefan.simeonov
 * @version 1.1
 * @since 22.12.2016
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, WrongStringInputException {

        File configFile = new File(args[0]);
        ObjectValidator.checkIfFileExists(configFile, "The file is not exists");
        JAXBContext jaxb = null;
        Unmarshaller jaxbUnmarsheller = null;
        School mySchool = null;
        try {
            jaxb = JAXBContext.newInstance(School.class, Student.class);
            jaxbUnmarsheller = jaxb.createUnmarshaller();
            mySchool = new School();
            ObjectValidator.checkForCast(mySchool, jaxbUnmarsheller.unmarshal(configFile), "Wrong casting");
            mySchool = (School) jaxbUnmarsheller.unmarshal(configFile);
        } catch (JAXBException ex) {
            System.out.println(("Something wrong with jaxb creation"));
        }
        ObjectValidator.checkIfObjectIsNull(mySchool, "The school is empty");
        ObjectValidator.checkIfStringMatch(args[1], Constants.TEXT_OUTPUT_FILE_STRING_PATTERN,
                "The output .txt file has incorrent name");
        String propertyFileOutputName = args[1].substring(0, args[1].indexOf('.'))
                + Constants.PROPERTY_FILE_STRING_EXTENTION;
        ObjectValidator.checkIfStringMatch(propertyFileOutputName, Constants.PROPERTY_OUTPUT_FILE_STRING_PATTERN,
                "The output property file has incorrent name");
        PrintWriter writer = new PrintWriter(args[1]);
        PrintWriter keyValueWriter = new PrintWriter(propertyFileOutputName);
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

    protected static void buildKeyValue(int personClassNumber, SchoolObject currentObj,
            TreeMap<String, String> keyValuePair) {
        List<String> props = new ArrayList<>();
        Collections.addAll(props, PersonProperties.name.toString(), PersonProperties.age.toString(),
                PersonProperties.referenceNumber.toString(), PersonProperties.facultyNumber.toString());
        String nameOfObject = currentObj.getClass().getName();
        nameOfObject = nameOfObject.substring(nameOfObject.lastIndexOf('.') + 1);
        StringBuilder keyBuild = new StringBuilder();
        keyBuild.append(nameOfObject);
        keyBuild.append(".");
        keyBuild.append(personClassNumber + 1 + ".");
        for (int i = 0; i < (currentObj instanceof Student ? props.size() : props.size() - 1); i++) {
            keyBuild.append(props.get(i));
            ObjectValidator.checkIfObjectIsNull(getTheObjectProp(currentObj, i),
                    "Something wrong with getting object's properties");
            String currentValue = getTheObjectProp(currentObj, i);
            keyValuePair.put(keyBuild.toString(), currentValue);
            int propsize = props.get(i).length();
            keyBuild.replace(keyBuild.length() - propsize, keyBuild.length(), "");
        }
    }

    protected static void keyValueFilePrint(TreeMap<String, String> keyValuePair, PrintWriter keyValueWriter) {
        Iterator<Entry<String, String>> itr = keyValuePair.entrySet().iterator();
        while (itr.hasNext()) {
            Entry<String, String> temp = itr.next();
            keyValueWriter.println(temp.getKey() + "=" + temp.getValue());
        }

    }

    protected static void Print(School myschool, PrintWriter writer, TreeMap<String, String> keyValuePair,
            PrintWriter keyValueWriter) {
        int numberOfClasses = myschool.getSchoolClasses().size();
        ObjectValidator.checkIfObjectIsNull(numberOfClasses, "There isnt any classes in the school");
        writer.printf(MessageProvider.STRING_SCHOOL_INFO.getValue(), myschool.getName(),
                numberOfClasses > 1 ? "are" : "is", numberOfClasses, numberOfClasses == 1 ? "" : "es");
        printClassesData(myschool, numberOfClasses, writer, keyValuePair,keyValueWriter);
        writer.close();
        keyValueWriter.close();
    }

    protected static void printClassesData(School myschool, int numberOfClasses, PrintWriter writer,
            TreeMap<String, String> keyValuePair,PrintWriter keyValueWriter) {
        for (int currentClassNum = 0; currentClassNum < numberOfClasses; currentClassNum++) {
            printObject(myschool, currentClassNum, writer, keyValuePair, Constants.STUDENT_AS_STRING);
            printObject(myschool, currentClassNum, writer, keyValuePair, Constants.TEACHER_AS_STRING);
            keyValueFilePrint(keyValuePair, keyValueWriter);
            keyValuePair=new TreeMap<>();
        }
    }

    protected static void printObject(School myschool, int currentClassNum, PrintWriter writer,
            TreeMap<String, String> keyValuePair, String typeOfObject) {
        String classNum = turnClassNumberIntoText(currentClassNum).toString();
        List<String> studentReferences = myschool.getSchoolClasses().get(currentClassNum).getStudentsReference();
        ObjectValidator.checkIfObjectIsNull(studentReferences, "There isnt any students in this class");
       if(typeOfObject.equals(Constants.STUDENT_AS_STRING)) writer.printf(MessageProvider.STRING_NUMBER_OF_CLASS_OBJECTS_INFO.getValue(), classNum,
                studentReferences.size(), typeOfObject);
       else{
    	   writer.printf("The teacher of the class is: ");
       }
        for (int schoolObject = 0; schoolObject < (typeOfObject.equals(Constants.STUDENT_AS_STRING)
                ? studentReferences.size() : Constants.NUMBER_OF_TEACHERS_IN_SIMPLE_SCHOOLCLASS); schoolObject++) {
            String currentReference = typeOfObject.equals(Constants.STUDENT_AS_STRING)
                    ? studentReferences.get(schoolObject)
                    : myschool.getSchoolClasses().get(currentClassNum).getTeacherReference();
            SchoolObject currentObject = searchPersonByReference(currentReference, myschool, typeOfObject);
            ObjectValidator.checkIfObjectIsNull(currentReference, "There isnt any student with this reference number");
            writer.printf(MessageProvider.STRING_STUDENTSANDTEACHERS_INFO.getValue(),
                    currentObject.getName().toString(), currentObject.getAge());
            if (currentObject instanceof Student) {
                writer.printf(MessageProvider.STRING_STUDENTS_FACNUMBER_INFO.getValue(),
                        ((Student) currentObject).getFacultyNumber());
            }
            buildKeyValue(schoolObject, currentObject, keyValuePair);
        }
    }

    protected static SchoolObject searchPersonByReference(String objectReference, School myschool,
            String typeOfPerson) {
        switch (typeOfPerson) {
            case Constants.STUDENT_AS_STRING:
                List<Student> students = myschool.getStudents().getStudent();
                return checkReferenceExistence(students, objectReference);

            case Constants.TEACHER_AS_STRING: {
                List<Teacher> teachers = myschool.getTeachers().getTeacher();
                ObjectValidator.checkIfObjectIsNull(teachers, "There isnt any teachers in the school");
                return checkReferenceExistence(teachers, objectReference);
            }
            default:
                return null;
        }
    }

    protected static SchoolObject checkReferenceExistence(Object ListOfObjects, String objectReference) {
        ObjectValidator.checkIfObjectIsNull(objectReference, "The object reference is null");
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

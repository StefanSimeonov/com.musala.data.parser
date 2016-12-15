package com.musala.xml.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.musala.xml.model.School;
import com.musala.xml.model.SchoolClass;
import com.musala.xml.model.Student;
import com.musala.xml.model.Teacher;

public class Main {

    public static void main(String[] args) throws IOException, JAXBException {
        String pathname = "C:\\Users\\stefan.simeonov\\Desktop\\ThePath.xml";
        Path pathname2 = Paths.get("C:\\Users\\stefan.simeonov\\Desktop\\ThePath.xml");
        // File file = new File(pathname);
        /*
         * Scanner in = null;
         * try {
         * in = new Scanner(file);
         * 
         * while (in.hasNextLine()) {
         * System.out.println(in.nextLine());
         * }
         * } catch (FileNotFoundException e) {
         * System.out.println(e.getMessage());
         * } finally {
         * if (in != null)
         * in.close();
         * }
         */

        /*
         * FileInputStream input = null;
         * try {
         * input = new FileInputStream(file);
         * int content;
         * while((content=input.read())!=-1){
         * System.out.print((char)content);
         * }
         * } catch ( IOException ex) {
         * System.out.println(ex.getMessage());
         * }finally{
         * if(input!=null) input.close();
         * }
         */
        // FileReader inpt=new FileReader(file);
        // char[] mass=new char[1000];
        // inpt.read(mass);
        // for(char symbol:mass){
        // System.out.print(symbol);
        // }
        /*
         * int content;
         * while((content=inpt.read())!=-1){
         * System.out.print((char)content);
         * }
         */
        // byte[] arr = Files.readAllBytes(pathname2);
        // System.out.println(new String(arr, "UTF-8"));
        String pathname3 = "ThePath.xml";
        getFile(Constants.filepath);

        ClassLoader classload = Main.class.getClassLoader();
        File configFile = new File(classload.getResource(pathname3).getFile());
        JAXBContext jaxb = JAXBContext.newInstance(School.class);
        Unmarshaller jaxbUnmarsheller = jaxb.createUnmarshaller();
        School mySchool = (School) jaxbUnmarsheller.unmarshal(configFile);
        System.out.println(mySchool.getStudents().getStudent().get(0).getAge());
        System.out.println(mySchool.getStudents().getStudent().get(1).getAge());
        System.out.println(mySchool.getSchoolClasses().get(0).getTeacherReference());
        System.out.println(mySchool.getTeachers().getTeacher().get(0).getAge());
        Print(mySchool);
    }

private static void Print(School myschool){
    int numberOfClasses=myschool.getSchoolClasses().size();
   System.out.printf("The school name is: %s!%n There %s %d school classes.%n",myschool.getName(),numberOfClasses>1?"are":"is",numberOfClasses);

for (int i = 0; i < numberOfClasses; i++) {
    String classNum=classTransferator(i+1);
    System.out.printf("The %s class have %d students: %n",classNum,myschool.getSchoolClasses().get(i).getStudentsReference().size());
   for (int j = 0; j <myschool.getSchoolClasses().get(i).getStudentsReference().size(); j++) {
       String currentReference=myschool.getSchoolClasses().get(i).getStudentsReference().get(j);
    Student currentStudent= (Student)searchTheStudentByReference(currentReference, myschool,"Student");
   System.out.printf("%s is %d years old with fac. number %s.%n",currentStudent.getName(),currentStudent.getAge(),currentStudent.getFacultyNumber());
   }
   
   String currentReference=myschool.getSchoolClasses().get(i).getTeacherReference();
   Teacher currentTeacher= (Teacher)searchTheStudentByReference(currentReference, myschool,"Teacher");
   System.out.printf("The teacher of the class is %s- %d years old",currentTeacher.getName(),currentTeacher.getAge());
}
   
}            
 


    private static Object searchTheStudentByReference(String studentReference, School myschool, String typeOfPerson) {
        switch (typeOfPerson) {
            case "Student":
                for (int i = 0; i < myschool.getStudents().getStudent().size(); i++) {
                    if (studentReference.equals(myschool.getStudents().getStudent().get(i).getReferenceNumber())) {
                        return myschool.getStudents().getStudent().get(i);
                    }
                }
                return null;

            case "Teacher": {
                for (int i = 0; i < myschool.getTeachers().getTeacher().size(); i++) {
                    if (studentReference.equals(myschool.getTeachers().getTeacher().get(i).getReferenceNumber())) {
                        return myschool.getTeachers().getTeacher().get(i);
                    }
                }
                return null;
            }
        }

        return null;
    }

    private static String classTransferator(int numberOfClass) {
        String word;
        switch (numberOfClass) {
            case 1:
                word = "first";
                break;
            case 2:
                word = "second";
                break;
            case 3:
                word = "third";
                break;
            case 4:
                word = "fourth";
                break;
            case 5:
                word = "fifth";
                break;
            case 6:
                word = "sixth";
                break;
            case 7:
                word = "seventh";
                break;
            case 8:
                word = "eighth";
                break;
            case 9:
                word = "ninth";
                break;
            default:
                word = "nth";
        }
        return word;
    }

    private static void getFile(String pathname) throws IOException {
        FileInputStream inputStream = null;

        try {
            ClassLoader classload = Main.class.getClassLoader();
            File configFile = new File(classload.getResource(pathname).getFile());

            inputStream = new FileInputStream(configFile);
            System.out.print(new String(Files.readAllBytes(configFile.toPath())));
        } catch (IOException ex) {

        } finally {
            inputStream.close();
        }

    }
}

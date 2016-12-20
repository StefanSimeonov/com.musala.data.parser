package Test;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.musala.xml.parser.executable.Main;
import com.musala.xml.parser.executable.model.School;
import com.musala.xml.parser.executable.model.SchoolObject;
import com.musala.xml.parser.executable.model.Student;
import com.musala.xml.parser.executable.model.StudentListWrapper;
import com.musala.xml.parser.executable.model.Teacher;

import junit.framework.TestCase;

public class MainTest extends Main {

    @Test
    public void testCheckReferenceExistence() {
        String objReference = "1";
        ArrayList<SchoolObject> objList = new ArrayList<SchoolObject>();
        Collections.addAll(objList, new Student(null, 0, "1", 0), new Teacher(null, 0, "3"));
        assertNotNull(Main.checkReferenceExistence(objList, "3"));
    }

    @Test
    public void testCheckReferenceOfObjectIsNull() {
        ArrayList<SchoolObject> objList = new ArrayList<SchoolObject>();
        Collections.addAll(objList, new Student(), new Teacher());
        try {
            Main.checkReferenceExistence(objList, null);
        } catch (NullPointerException ex) {
            System.out.println("The null value of reference is catch in testCheckReferenceOfObjectIsNull method");
        }

    }

    @Test
    public void testsearchObjectByReference() {

        List<Student> tempListOfStudents = new ArrayList<Student>();
        Collections.addAll(tempListOfStudents, new Student(null, 0, "1", 0));
        StudentListWrapper tempListWraper = new StudentListWrapper(tempListOfStudents);
        School myschool = new School(tempListWraper, null, null, null);
        assertEquals("1", Main.searchObjectByReference("1", myschool, "Student").getReferenceNumber());
    }

    @Test
    public void testsearchObjectByReferenceIsNull() {

        School myschool = new School();
        try {
            Main.searchObjectByReference("1", myschool, "Student");
        } catch (NullPointerException ex) {
            System.out.println("The null object is catch in testsearchObjectByReferenceIsNull method");
        }
    }

    // @Test
    // public void testprintObject(){
    // PrintWriter writer=new PrintWriter(System.out);
    // TreeMap<String, String> keyValuePair=new TreeMap<>();
    // School myschool=new School(students, teachers, schoolClasses,null);
    // Main.printObject(myschool, 1, writer, keyValuePair,"Student");
    // }

    @Test
    public void testprintObjectWithNullSchoolReference() {

        try {
            Main.printObject(null, 1, new PrintWriter(System.out), new TreeMap<String, String>(), "Student");
        } catch (NullPointerException ex) {
            System.out.println("The null value of school is catch in testprintObjectWithNullSchoolReference method");
        }
    }
}

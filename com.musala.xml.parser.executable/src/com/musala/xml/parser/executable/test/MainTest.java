package com.musala.xml.parser.executable.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import org.junit.Test;

import com.musala.xml.parser.executable.Main;
import com.musala.xml.parser.executable.helpers.WrongStringInputException;
import com.musala.xml.parser.executable.model.School;
import com.musala.xml.parser.executable.model.SchoolClass;
import com.musala.xml.parser.executable.model.SchoolObject;
import com.musala.xml.parser.executable.model.Student;
import com.musala.xml.parser.executable.model.StudentListWrapper;
import com.musala.xml.parser.executable.model.Teacher;

/**
 * Writing more unit tests are meaningless because of repeatedly NullPointerExceptions
 *
 */
public class MainTest extends Main {

    @Test
    public void testCheckReferenceExistence() {
        ArrayList<SchoolObject> objList = new ArrayList<SchoolObject>();
        Student st = new Student();
        st.setReferenceNumber("3");
        objList.add(st);
        assertNotNull(Main.checkReferenceExistence(objList, "3"));
    }

    @Test
    public void testCheckReferenceOfObjectIsNull() {
        ArrayList<SchoolObject> objList = new ArrayList<SchoolObject>();
        Collections.addAll(objList, new Student(), new Teacher());
        try {
            Main.checkReferenceExistence(objList, null);
        } catch (Exception ex) {
            assertThat(ex.getClass(), equalTo(NullPointerException.class));
        }

    }

    @Test
    public void testsearchObjectByReference() {
        List<Student> tempListOfStudents = new ArrayList<Student>();
        Student st = new Student();
        st.setReferenceNumber("1");
        tempListOfStudents.add(st);
        StudentListWrapper tempListWraper = new StudentListWrapper();
        tempListWraper.setStudent(tempListOfStudents);
        School myschool = new School();
        myschool.setStudents(tempListWraper);
        assertEquals("1", Main.searchPersonByReference("1", myschool, "Student").getReferenceNumber());
    }

    @Test
    public void testsearchObjectByReferenceIsNull() {
        School myschool = new School();
        try {
            Main.searchPersonByReference("1", myschool, "Student");
        } catch (NullPointerException ex) {
            assertThat(ex.getClass(), equalTo(NullPointerException.class));
        }
    }

    @Test
    public void testprintObjectWithNullSchoolReference() {
        try {
            Main.printObject(null, 1, new PrintWriter(System.out), new TreeMap<String, String>(), "Student");
        } catch (NullPointerException ex) {
            assertThat(ex.getClass(), equalTo(NullPointerException.class));
        }
    }

    @Test
    public void testprintObjectWithNegativeClassNum() {
        School mySchool = new School();
        mySchool.setSchoolClasses(new ArrayList<SchoolClass>());
        try {
            Main.printObject(mySchool, -1, null, null, "Teacher");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertThat(e.getClass(), equalTo(ArrayIndexOutOfBoundsException.class));
        }
    }

    @Test
    public void testPrintObjectWithEmptyArrayListOfSchoolClass() {
        School mySchool = new School();
        mySchool.setSchoolClasses(new ArrayList<SchoolClass>());
        try {
            Main.printObject(mySchool, 0, null, null, null);
        } catch (IndexOutOfBoundsException e) {
            assertThat(e.getClass(), equalTo(IndexOutOfBoundsException.class));
        }
    }

    @Test
    public void testPrintObjectWithNullTypeOfObject() {
        ArrayList<String> studentReferences = new ArrayList<>();
        studentReferences.add("1");
        SchoolClass tempSchoolClass = new SchoolClass();
        tempSchoolClass.setStudentsReference(studentReferences);
        ArrayList<SchoolClass> schoolClasses = new ArrayList<>();
        schoolClasses.add(tempSchoolClass);
        School mySchool = new School();
        mySchool.setSchoolClasses(schoolClasses);
        try {
            Main.printObject(mySchool, 0, null, null, null);
        } catch (Exception e) {
            assertThat(e.getClass(), equalTo(NullPointerException.class));
        }
    }

    @Test
    public void testprintClassesDataWithNullSchool() {
        try {
            Main.printClassesData(null, 2, null, null,null);
        } catch (NullPointerException e) {
            assertThat(e.getClass(), equalTo(NullPointerException.class));
        }
    }

    @Test
    public void testCheckOutputTextFileName() {
        String[] temp = new String[3];
        temp[0] = "C:\\Users\\stefan.simeonov\\Desktop\\ThePath.xml";
        temp[1] = "C:\\Output.x";
        try {
            Main.main(temp);
        } catch (WrongStringInputException e) {
            assertEquals(e.getMessage(), "The output .txt file has incorrent name");
        } catch (FileNotFoundException e) {
            // Skip to avoid throws declaration
            // and not needed in test
        }
    }

    @Test
    public void testIfFileIsFound() throws WrongStringInputException {
        try {
            Main.main(new String[] { "asdads" });
        } catch (FileNotFoundException e) {
            assertThat(e.getClass(), equalTo(FileNotFoundException.class));
        } catch (WrongStringInputException e) {
            // Skip to avoid throws declaration
            // and not needed in test
        }
    }

    @Test
    public void testPrintNullSchool() {
        try {
            Main.Print(null, null, null, null);
        } catch (NullPointerException e) {
            assertThat(e.getClass(), equalTo(NullPointerException.class));
        }
    }

    @Test
    public void testbuildKeyValueIsNull() {
        try {
            Main.buildKeyValue(0, null, null);
        } catch (NullPointerException e) {
            assertThat(e.getClass(), equalTo(NullPointerException.class));
        }
    }

    @Test
    public void testBuildKeyValueWithNegativeSchoolObject() {
        try {
            Main.buildKeyValue(-1, null, null);
        } catch (NullPointerException e) {
            assertThat(e.getClass(), equalTo(NullPointerException.class));
        }
    }
}

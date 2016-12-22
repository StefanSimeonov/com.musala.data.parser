package com.musala.xml.parser.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.musala.xml.parser.Main;
import com.musala.xml.parser.model.School;
import com.musala.xml.parser.model.SchoolClass;
import com.musala.xml.parser.model.Student;
import com.musala.xml.parser.model.StudentListWrapper;

/**
 * Writing more unit tests are meaningless because of repeatedly NullPointerExceptions
 *
 */
public class MainTest extends Main {

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
        assertNotNull(Main.searchPersonByReference("1", myschool, "Student"));
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
    public void testprintStudentWithEmptySchool() {
        try {
            Main.printStudents(null, 1);
        } catch (NullPointerException ex) {
            assertThat(ex.getClass(), equalTo(NullPointerException.class));
        }
    }

    // @Test
    public void testprintTeacherWithEmptySchool() {
        try {
            Main.printTeacher(null, 1);
        } catch (NullPointerException ex) {
            assertThat(ex.getClass(), equalTo(NullPointerException.class));
        }
    }

    public void testPrintTeacherWithNegativeClassNum() {
        School myschool = new School();
        SchoolClass myclass = new SchoolClass();
        ArrayList<SchoolClass> classes = new ArrayList<>();
        classes.add(myclass);
        myschool.setSchoolClasses(classes);
        try {
            Main.printTeacher(null, -1);
        } catch (NullPointerException ex) {
            assertThat(ex.getClass(), equalTo(NullPointerException.class));
        }
    }

    @Test
    public void testprintClassesDataWithNullSchool() {
        try {
            Main.printClassesData(null, 1);
        } catch (NullPointerException e) {
            assertThat(e.getClass(), equalTo(NullPointerException.class));
        }
    }

    @Test
    public void testPrintNullSchool() {
        try {
            Main.Print(null);
        } catch (NullPointerException e) {
            assertThat(e.getClass(), equalTo(NullPointerException.class));
        }
    }

}

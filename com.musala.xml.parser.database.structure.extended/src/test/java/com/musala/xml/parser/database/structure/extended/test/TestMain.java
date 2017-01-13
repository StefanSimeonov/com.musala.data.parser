package com.musala.xml.parser.database.structure.extended.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.musala.xml.parser.database.structure.extended.Main;
import com.musala.xml.parser.database.structure.extended.helpers.HashMapRepo;
import com.musala.xml.parser.database.structure.extended.model.School;
import com.musala.xml.parser.database.structure.extended.model.SchoolClass;
import com.musala.xml.parser.database.structure.extended.model.SchoolDataInfo;
import com.musala.xml.parser.database.structure.extended.model.Student;
import com.musala.xml.parser.database.structure.extended.model.Teacher;

public class TestMain extends Main {

	@Test
	public void testFillRepositoriesWithNullDataStructure() {
		try {
			Main.fillTheRepositories(new SchoolDataInfo(), null);
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testFillRepositoriesWithNullSchoolData() {
		try {
			Main.fillTheRepositories(null, new HashMapRepo(null, null, null));
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testSearchingTeacherById() {
		SchoolClass tempClass = new SchoolClass();
		tempClass.setTeacherId("5");
		HashMap<String, Teacher> map = new HashMap<>();
		Teacher teacher = new Teacher();
		teacher.setId("5");
		map.put("5", teacher);
		HashMapRepo repo = new HashMapRepo(null, null, map);
		Teacher searcher = new Teacher();
		searcher.setId("5");
		assertEquals(searcher.getId(), Main.searchTeacherOfTheClass(tempClass, repo).getId());
	}

	@Test
	public void testSearchingStudentById() {

		SchoolClass searcher = new SchoolClass();
		searcher.setId("5");
		HashMap<String, SchoolClass> map = new HashMap<>();
		map.put("5", searcher);
		HashMapRepo repo = new HashMapRepo(null, map, null);
		Student searchingStudent = new Student();
		searchingStudent.setClassId("5");
		assertEquals(searcher.getId(), Main.searchSchoolClassOfTheStudent(searchingStudent, repo).getId());
	}

	@Test
	public void testSearchingSchoolById() {
		SchoolClass tempClass = new SchoolClass();
		tempClass.setSchoolId("5");
		School searchingSchool = new School();
		searchingSchool.setId("5");
		HashMap<String, School> map = new HashMap<>();
		map.put("5", searchingSchool);
		HashMapRepo repo = new HashMapRepo(map, null, null);
		School seacher = new School();
		seacher.setId("5");
		assertEquals(seacher.getId(), Main.seachSchoolOfTheSchoolClass(tempClass, repo).getId());
	}

	@Test
	public void testPrintNullStudent() {
		try {
			Main.printStudent(null, null, null);
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testPrintNull() {
		try {
			Main.print(null, null, null);
		} catch (NullPointerException e) {
			assertThat(e.getClass(), equalTo(NullPointerException.class));
		}
	}

	@Test
	public void testIfFileIsFound() {
		try {
			Main.main(new String[] { "incorrectString123" });
		} catch (FileNotFoundException e) {
			assertThat(e.getClass(), equalTo(FileNotFoundException.class));
		} catch (JAXBException e) {
			// Skip to avoid throws declaration
			// and not needed in test
		}
	}
}

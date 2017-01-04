package com.musala.xml.parser.database.structure.extended;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.musala.xml.parser.database.structure.extended.helpers.HashMapRepo;
import com.musala.xml.parser.database.structure.extended.model.School;
import com.musala.xml.parser.database.structure.extended.model.SchoolClass;
import com.musala.xml.parser.database.structure.extended.model.SchoolDataInfo;
import com.musala.xml.parser.database.structure.extended.model.Student;
import com.musala.xml.parser.database.structure.extended.model.Teacher;

public class Main {

	/**
	 * The initialization method for the program
	 * 
	 * @param args-
	 *            expect 2 string file names with proper directory locations
	 * @throws FileNotFoundException
	 * @throws WrongStringInputException
	 */
	public static void main(String[] args) throws JAXBException, FileNotFoundException {

		JAXBContext jaxb = JAXBContext.newInstance(SchoolDataInfo.class);
		Unmarshaller unmarshal = jaxb.createUnmarshaller();
		SchoolDataInfo schoolData = (SchoolDataInfo) unmarshal.unmarshal(new File(args[0]));
		HashMapRepo repo = new HashMapRepo(new HashMap<String, School>(), new HashMap<String, SchoolClass>(),
				new TreeMap<String, Teacher>());
		fillTheRepositories(schoolData, repo);
		PrintWriter writer = new PrintWriter(args[1]);
		print(schoolData, repo, writer);
	}

	/**
	 * List every student in the xml file
	 * 
	 * @param schoolData
	 * @param repo
	 * @param writer
	 */
	protected static void print(SchoolDataInfo schoolData, HashMapRepo repo, PrintWriter writer) {
		List<Student> students = schoolData.getStudents().getStudents();
		for (int i = 0; i < students.size(); i++) {
			printStudent(students.get(i), repo, writer);
		}
		writer.close();
	}

	/**
	 * Print collecting properties for every student
	 * 
	 * @param student
	 * @param repo
	 * @param writer
	 */
	protected static void printStudent(Student student, HashMapRepo repo, PrintWriter writer) {
		SchoolClass studentClass = searchSchoolClassOfTheStudent(student, repo);
		Teacher studentTeacher = searchTeacherOfTheClass(studentClass, repo);
		School studentSchool = seachSchoolOfTheSchoolClass(studentClass, repo);
		writer.printf(Constants.PRINT_TEXT, student.getName(), student.getAge(), student.getFacultyNumber(),
				studentSchool.getName(), studentSchool.getTrend(), studentSchool.getLocation(),
				studentClass.getSubject(), studentTeacher.getName(), studentTeacher.getAge());
	}

	/**
	 * Search trough HashMapRepo for Teacher with the same Class's teacher
	 * reference
	 * 
	 * @param schoolclass
	 * @param repo
	 * @return Teacher
	 */
	protected static Teacher searchTeacherOfTheClass(SchoolClass schoolclass, HashMapRepo repo) {
		return repo.searchTeacherById(schoolclass.getTeacherId());
	}

	/**
	 * Search trough HashMapRepo for Class with the same Student's class
	 * reference
	 * 
	 * @param student
	 * @param repo
	 * @return SchoolClass
	 */
	protected static SchoolClass searchSchoolClassOfTheStudent(Student student, HashMapRepo repo) {
		return repo.searchSchoolClassById(student.getClassId());
	}

	/**
	 * Search trough HashmapRepo for School with the same Class's school
	 * reference
	 * 
	 * @param schoolClass
	 * @param repo
	 * @return School
	 */
	protected static School seachSchoolOfTheSchoolClass(SchoolClass schoolClass, HashMapRepo repo) {
		return repo.serchSchoolById(schoolClass.getSchoolId());
	}

	/**
	 * Fill all HashMaps in the HashMapRepo with the xml info
	 * 
	 * @param schoolData
	 * @param repo
	 */
	protected static void fillTheRepositories(SchoolDataInfo schoolData, HashMapRepo repo) {

		for (int i = 0; i < Constants.NUMBER_OF_FILL_REPOS_OPERATIONS; i++) {
			switch (i) {
			case 0:
				List<School> schoolList = schoolData.getSchools().getSchools();
				for (int j = 0; j < schoolList.size(); j++) {
					School temp = schoolList.get(j);
					repo.putSchool(temp.getId(), temp);
				}
			case 1:
				List<SchoolClass> schoolClassesList = schoolData.getSchoolClasses().getSchoolClasses();
				for (int j = 0; j < schoolClassesList.size(); j++) {
					SchoolClass temp = schoolClassesList.get(j);
					repo.putSchoolClass(temp.getId(), temp);
				}

			case 2:
				List<Teacher> teacherList = schoolData.getTeachers().getTeachers();
				for (int j = 0; j < teacherList.size(); j++) {
					Teacher temp = teacherList.get(j);
					repo.putTeacher(temp.getId(), temp);
				}
			}
		}
	}

}

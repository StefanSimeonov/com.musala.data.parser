package com.musala.xml.parser.database.structure.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.musala.xml.parser.database.structure.model.helpers.TreeMapRepo;
import com.musala.xml.parser.database.structure.model.models.School;
import com.musala.xml.parser.database.structure.model.models.SchoolClass;
import com.musala.xml.parser.database.structure.model.models.SchoolClassListWrapper;
import com.musala.xml.parser.database.structure.model.models.SchoolDataInfo;
import com.musala.xml.parser.database.structure.model.models.Student;
import com.musala.xml.parser.database.structure.model.models.Teacher;

public class Main {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {

		JAXBContext jaxb = JAXBContext.newInstance(SchoolDataInfo.class);
		Unmarshaller unmarshal = jaxb.createUnmarshaller();
		SchoolDataInfo schoolData = (SchoolDataInfo) unmarshal.unmarshal(new File(args[0]));
		TreeMapRepo repo = new TreeMapRepo(new TreeMap<String, School>(), new TreeMap<String, SchoolClass>(),
				new TreeMap<String, Student>(), new TreeMap<String, Teacher>());

		fillTheRepositories(schoolData, repo);
		PrintWriter writer = new PrintWriter(args[1]);
		print(schoolData, repo, writer);
	}

	private static void print(SchoolDataInfo schoolData, TreeMapRepo repo, PrintWriter writer) {
		List<Student> students = schoolData.getStudents().getStudents();
		for (int i = 0; i < students.size(); i++) {
			printStudent(students.get(i), repo, writer);
		}
		writer.close();
	}

	private static void printStudent(Student student, TreeMapRepo repo, PrintWriter writer) {
		SchoolClass studentClass = searchSchoolClassOfTheStudent(student, repo);
		Teacher studentTeacher = searchTeacherOfTheClass(studentClass, repo);
		School studentSchool = seachSchoolOfTheSchoolClass(studentClass, repo);
		writer.printf(
				"%s, %d years old, with %d, study in %s,%s school,located on %s.His subject is %s.The teacher of his class is %s, %d years old.%n",
				student.getName(), student.getAge(), student.getFacultyNumber(), studentSchool.getName(),
				studentSchool.getTrend(), studentSchool.getLocation(), studentClass.getSubject(),
				studentTeacher.getName(), studentTeacher.getAge());
	}

	private static Teacher searchTeacherOfTheClass(SchoolClass schoolclass, TreeMapRepo repo) {
		return repo.searchTeacherById(schoolclass.getTeacherId());
	}

	private static SchoolClass searchSchoolClassOfTheStudent(Student student, TreeMapRepo repo) {
		return repo.searchSchoolClassById(student.getClassId());
	}

	private static School seachSchoolOfTheSchoolClass(SchoolClass schoolClass, TreeMapRepo repo) {
		return repo.serchSchoolById(schoolClass.getSchoolId());
	}

	private static void fillTheRepositories(SchoolDataInfo schoolData, TreeMapRepo repo) {

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
				List<Student> studentList = schoolData.getStudents().getStudents();
				for (int j = 0; j < studentList.size(); j++) {
					Student temp = studentList.get(j);
					repo.putStudent(temp.getId(), temp);
				}
			case 3:
				List<Teacher> teacherList = schoolData.getTeachers().getTeachers();
				for (int j = 0; j < teacherList.size(); j++) {
					Teacher temp = teacherList.get(j);
					repo.putTeacher(temp.getId(), temp);
				}
			default:
				return;
			}
		}

	}

	private static void fillSchoolClassesRepo(SchoolClassListWrapper schoolClasses,
			TreeMap<String, SchoolClass> schoolClassesRepo) {
		List<SchoolClass> schoolClassesList = schoolClasses.getSchoolClasses();
		for (int i = 0; i < schoolClassesList.size(); i++) {
			SchoolClass temp = schoolClassesList.get(i);
			schoolClassesRepo.put(temp.getId(), temp);
		}
	}

}

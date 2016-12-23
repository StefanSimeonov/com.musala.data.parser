package com.musala.xml.parser.database.structure.model.helpers;

import java.util.TreeMap;

import com.musala.xml.parser.database.structure.model.models.School;
import com.musala.xml.parser.database.structure.model.models.SchoolClass;
import com.musala.xml.parser.database.structure.model.models.Student;
import com.musala.xml.parser.database.structure.model.models.Teacher;

public class TreeMapRepo {
	TreeMap<String, School> schoolRepo;
	TreeMap<String, SchoolClass> schoolClassRepo;
	TreeMap<String, Student> studentsRepo;
	TreeMap<String, Teacher> teachersRepo;

	public TreeMapRepo(TreeMap<String, School> schoolRepo, TreeMap<String, SchoolClass> schoolClassesRepo,
			TreeMap<String, Student> studentsRepo, TreeMap<String, Teacher> teachersRepo) {
		this.schoolRepo = schoolRepo;
		this.schoolClassRepo = schoolClassesRepo;
		this.studentsRepo = studentsRepo;
		this.teachersRepo = teachersRepo;
	}

	public TreeMap<String, SchoolClass> getSchoolClassesRepo() {
		return schoolClassRepo;
	}

	public TreeMap<String, School> getSchoolRepo() {
		return schoolRepo;
	}

	public TreeMap<String, Student> getStudentsRepo() {
		return studentsRepo;
	}

	public TreeMap<String, Teacher> getTeachersRepo() {
		return teachersRepo;
	}

	public void putSchool(String key, School value) {
		schoolRepo.put(key, value);
	}

	public void putSchoolClass(String key, SchoolClass value) {
		schoolClassRepo.put(key, value);
	}

	public void putStudent(String key, Student value) {
		studentsRepo.put(key, value);
	}

	public void putTeacher(String key, Teacher value) {
		teachersRepo.put(key, value);
	}
	
public School serchSchoolById(String id){
	return schoolRepo.get(id);
}
public SchoolClass searchSchoolClassById(String id){
	return schoolClassRepo.get(id);
}
public Teacher searchTeacherById(String id){
	return teachersRepo.get(id);
}
}

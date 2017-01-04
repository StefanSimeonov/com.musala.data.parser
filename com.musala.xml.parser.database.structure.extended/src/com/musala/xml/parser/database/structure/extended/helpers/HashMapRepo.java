package com.musala.xml.parser.database.structure.extended.helpers;

import java.util.Map;

import com.musala.xml.parser.database.structure.extended.model.School;
import com.musala.xml.parser.database.structure.extended.model.SchoolClass;
import com.musala.xml.parser.database.structure.extended.model.Teacher;

public class HashMapRepo {
	Map<String, School> schoolRepo;
	Map<String, SchoolClass> schoolClassRepo;
	Map<String, Teacher> teachersRepo;

	public HashMapRepo(Map<String, School> schoolRepo, Map<String, SchoolClass> schoolClassesRepo,
			Map<String, Teacher> teachersRepo) {
		this.schoolRepo = schoolRepo;
		this.schoolClassRepo = schoolClassesRepo;
		this.teachersRepo = teachersRepo;
	}

	public Map<String, SchoolClass> getSchoolClassesRepo() {
		return schoolClassRepo;
	}

	public Map<String, School> getSchoolRepo() {
		return schoolRepo;
	}

	public Map<String, Teacher> getTeachersRepo() {
		return teachersRepo;
	}

	public void putSchool(String key, School value) {
		schoolRepo.put(key, value);
	}

	public void putSchoolClass(String key, SchoolClass value) {
		schoolClassRepo.put(key, value);
	}

	public void putTeacher(String key, Teacher value) {
		teachersRepo.put(key, value);
	}

	public School serchSchoolById(String id) {
		return schoolRepo.get(id);
	}

	public SchoolClass searchSchoolClassById(String id) {
		return schoolClassRepo.get(id);
	}

	public Teacher searchTeacherById(String id) {
		return teachersRepo.get(id);
	}
}

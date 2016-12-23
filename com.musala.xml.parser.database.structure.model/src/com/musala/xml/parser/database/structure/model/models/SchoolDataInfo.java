package com.musala.xml.parser.database.structure.model.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="schoolDatabaseRepo")
public class SchoolDataInfo {

	private SchoolClassListWrapper schoolClasses;
	private SchoolListWrapper schools;
	private StudentListWrapper students;
	private TeacherListWrapper teachers;
	
	@XmlElement(name="schoolClasses")
	public void setSchoolClasses(SchoolClassListWrapper schoolClasses) {
		this.schoolClasses = schoolClasses;
	}
	
	@XmlElement(name="schools")
	public void setSchools(SchoolListWrapper schools) {
		this.schools = schools;
	}
	
	@XmlElement(name="students")
	public void setStudents(StudentListWrapper students) {
		this.students = students;
	}
	
	@XmlElement(name="teachers")
	public void setTeachers(TeacherListWrapper teachers) {
		this.teachers = teachers;
	}
	
	public SchoolClassListWrapper getSchoolClasses() {
		return schoolClasses;
	}
	
	public SchoolListWrapper getSchools() {
		return schools;
	}
	
	public StudentListWrapper getStudents() {
		return students;
	}
	
	public TeacherListWrapper getTeachers() {
		return teachers;
	}
}

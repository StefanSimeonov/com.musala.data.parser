package com.musala.xml.parser.database.structure.extended.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
public class Student extends SchoolObject {

	private int facultyNumber;
	private String ClassId;

	@XmlElement(name = "schoolClassForeignKey")
	public void setClassId(String classId) {
		ClassId = classId;
	}

	@XmlElement(name = "facultyNumber")
	public void setFacultyNumber(int facultyNumber) {
		this.facultyNumber = facultyNumber;
	}

	public String getClassId() {
		return ClassId;
	}

	public int getFacultyNumber() {
		return facultyNumber;
	}

}

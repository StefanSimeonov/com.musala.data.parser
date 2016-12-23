package com.musala.xml.parser.database.structure.model.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
public class Student {

	private String name;
	private int age;
	private String id;
	private int facultyNumber;
	private String ClassId;

	@XmlElement(name = "age")
	public void setAge(int age) {
		this.age = age;
	}

	@XmlElement(name="schoolClassForeignKey")
	public void setClassId(String classId) {
		ClassId = classId;
	}
	@XmlElement(name = "facultyNumber")
	public void setFacultyNumber(int facultyNumber) {
		this.facultyNumber = facultyNumber;
	}

	@XmlElement(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "primaryKey")
	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}
	
	public String getClassId() {
		return ClassId;
	}

	public int getFacultyNumber() {
		return facultyNumber;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
	
	
}

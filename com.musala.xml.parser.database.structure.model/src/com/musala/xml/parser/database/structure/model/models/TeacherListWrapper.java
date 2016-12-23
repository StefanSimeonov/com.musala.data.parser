package com.musala.xml.parser.database.structure.model.models;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="teachers")
public class TeacherListWrapper {
List<Teacher> teachers;

@XmlElement(name="teacher")
public void setTeachers(List<Teacher> teachers) {
	this.teachers = teachers;
}

public List<Teacher> getTeachers() {
	return teachers;
}
}

package com.musala.xml.parser.database.structure.model.models;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="students")
public class StudentListWrapper {
	List<Student> students;
	
	@XmlElement(name="student")
public void setStudents(List<Student> students) {
	this.students =students;
}
public List<Student> getStudents() {
	return students;
}
}


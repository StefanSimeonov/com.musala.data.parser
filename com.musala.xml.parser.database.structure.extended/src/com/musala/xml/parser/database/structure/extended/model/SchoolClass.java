package com.musala.xml.parser.database.structure.extended.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "schoolClass")
public class SchoolClass{

	private String id;
	private String teacherId;
	private String schoolId;
	private String subject;
	
	@XmlAttribute(name="primaryKey")
public void setId(String id) {
	this.id = id;
}

	@XmlElement(name="schoolForeignKey")
public void setSchoolId(String schoolId) {
	this.schoolId = schoolId;
}
@XmlElement(name="subject")
public void setSubject(String subject) {
	this.subject = subject;
}

@XmlElement(name="teacherForeingKey")
public void setTeacherId(String teacherId) {
	this.teacherId = teacherId;
}

public String getId() {
	return id;
}

public String getSchoolId() {
	return schoolId;
}

public String getSubject() {
	return subject;
}

public String getTeacherId() {
	return teacherId;
}

}
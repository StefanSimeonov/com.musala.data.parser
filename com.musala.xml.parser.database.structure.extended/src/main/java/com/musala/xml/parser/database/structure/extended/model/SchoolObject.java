package com.musala.xml.parser.database.structure.extended.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({ Student.class, Teacher.class })
public abstract class SchoolObject {

	private String name;
	private int age;
	private String id;

	public void setAge(int age) {
		this.age = age;
	}

	@XmlAttribute(name = "primaryKey")
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}

package com.musala.xml.parser.database.structure.extended.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="schools")
public class SchoolListWrapper {

private	List<School> schools;
	
	@XmlElement(name="school")
	public void setSchools(List<School> schools) {
		this.schools = schools;
	}
	
	public List<School> getSchools() {
		return schools;
	}
}

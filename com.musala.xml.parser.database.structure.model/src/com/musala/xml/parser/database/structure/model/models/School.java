package com.musala.xml.parser.database.structure.model.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="school")
public class School {
	
	private String Id;
	private String location;
	private String name;
	private String trend;
	
	@XmlAttribute(name="primaryKey")
	public void setId(String id) {
		Id = id;
	}
	@XmlElement(name="location")
	public void setLocation(String location) {
		this.location = location;
	}
	@XmlElement(name="name")
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name="trend")
	public void setTrend(String trend) {
		this.trend = trend;
	}
	
	public String getId() {
		return Id;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTrend() {
		return trend;
	}
}

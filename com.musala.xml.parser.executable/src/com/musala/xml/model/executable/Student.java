package com.musala.xml.model.executable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Student {

    private String name;
    private int age;
    private String referenceNumber;
    private int facultyNumber;

    @XmlElement(name = "age")
    public void setAge(int age) {
        this.age = age;
    }

    @XmlElement(name = "facultyNumber")
    public void setFacultyNumber(int facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "referenceNumber")
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public int getAge() {
        return age;
    }

    public int getFacultyNumber() {
        return facultyNumber;
    }

    public String getName() {
        return name;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

}

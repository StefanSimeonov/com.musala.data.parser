package com.musala.xml.parser.executable.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student extends SchoolObject {

    private int facultyNumber;

    public Student(String name, int age, String referenceNumber, int facultyNumber) {
        super(name, age, referenceNumber);
        setFacultyNumber(facultyNumber);
    }

    public Student() {

    }

    @XmlElement(name = "facultyNumber")
    public void setFacultyNumber(int facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public int getFacultyNumber() {
        return facultyNumber;
    }

}

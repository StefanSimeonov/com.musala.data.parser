package com.musala.xml.parser.executable.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student extends SchoolObject {

    private int facultyNumber;

    @XmlElement(name = "facultyNumber")
    public void setFacultyNumber(int facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public int getFacultyNumber() {
        return facultyNumber;
    }

}

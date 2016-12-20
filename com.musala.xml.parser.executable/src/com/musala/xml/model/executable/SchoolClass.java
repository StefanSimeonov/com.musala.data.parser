package com.musala.xml.model.executable;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "schoolClass")
public class SchoolClass {

    private String teacherReference;
    private List<String> studentsReference;

    @XmlElement(name = "studentReference")
    public void setStudentsReference(List<String> studentsReference) {
        this.studentsReference = studentsReference;
    }

    @XmlElement(name = "teacherReference")
    public void setTeacherReference(String teacherReference) {
        this.teacherReference = teacherReference;
    }

    public List<String> getStudentsReference() {
        return studentsReference;
    }

    public String getTeacherReference() {
        return teacherReference;
    }

}

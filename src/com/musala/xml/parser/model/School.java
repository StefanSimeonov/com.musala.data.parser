package com.musala.xml.parser.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "school")
public class School {

    private StudentListWrapper students;
    private TeacherListWrapper teachers;
    private List<SchoolClass> schoolClasses;
    private String name;

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "schoolClass")
    public void setSchoolClasses(List<SchoolClass> schoolClasses) {
        this.schoolClasses = schoolClasses;
    }

    public void setStudents(StudentListWrapper students) {
        this.students = students;
    }

    public void setTeachers(TeacherListWrapper teachers) {
        this.teachers = teachers;
    }

    public String getName() {
        return name;
    }

    public List<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }

    public StudentListWrapper getStudents() {
        return students;
    }

    public TeacherListWrapper getTeachers() {
        return this.teachers;
    }

}

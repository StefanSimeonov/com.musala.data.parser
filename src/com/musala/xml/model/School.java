package com.musala.xml.model;

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

    public void addStudents(SchoolClass... schoolClasses) {
        for (SchoolClass sc : schoolClasses) {
            this.schoolClasses.add(sc);
        }
    }

    @XmlElement(name = "schoolClass")
    public void setSchoolClasses(List<SchoolClass> schoolClasses) {
        this.schoolClasses = schoolClasses;
    }

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public List<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }

    public StudentListWrapper getStudents() {
        return students;
    }

    public void setStudents(StudentListWrapper students) {
        this.students = students;
    }

    public void setTeachers(TeacherListWrapper teachers) {
        this.teachers = teachers;
    }

    public TeacherListWrapper getTeachers() {
        return this.teachers;
    }
    public String getName() {
        return name;
    }
}

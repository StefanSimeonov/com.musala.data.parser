package com.musala.xml.parser.executable.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "teachers")
public class TeacherListWrapper {

    private List<Teacher> teacher;

    public TeacherListWrapper(List<Teacher> teacher) {
        setTeacher(teacher);
    }

    public TeacherListWrapper() {

    }

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }

    public List<Teacher> getTeacher() {
        return teacher;
    }

}

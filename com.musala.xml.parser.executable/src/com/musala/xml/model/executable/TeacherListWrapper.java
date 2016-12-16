package com.musala.xml.model.executable;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "teachers")
public class TeacherListWrapper {

    private List<Teacher> teacher;

    public void setTeacher(List<Teacher> teacher) {
        this.teacher = teacher;
    }

    public List<Teacher> getTeacher() {
        return teacher;
    }

}

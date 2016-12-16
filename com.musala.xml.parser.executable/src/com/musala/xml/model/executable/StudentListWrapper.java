package com.musala.xml.model.executable;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "students")
public class StudentListWrapper {

    private List<Student> student;

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public List<Student> getStudent() {
        return student;
    }

}

package com.musala.xml.parser.database.structure.model.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Teacher {

    private String name;
    private int age;
    private String id;

    @XmlElement(name = "age")
    public void setAge(int age) {
        this.age = age;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "primaryKey")
    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}

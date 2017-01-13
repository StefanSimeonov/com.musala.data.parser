package com.musala.xml.parser.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Teacher {

    private String name;
    private int age;
    private String referenceNumber;

    @XmlElement(name = "age")
    public void setAge(int age) {
        this.age = age;
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

    public String getName() {
        return name;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

}
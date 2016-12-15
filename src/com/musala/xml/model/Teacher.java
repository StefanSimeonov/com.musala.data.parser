package com.musala.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Teacher {
    public Teacher() {
    }
    private String name;
    private int age;
    private String referenceNumber;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "age")
    public void setAge(int age) {
        this.age = age;
    } 
    @XmlElement( name = "name" )
    public void setName(String name) {
        this.name = name;
    }
    public String getReferenceNumber() {
        return referenceNumber;
    }
    @XmlAttribute(name = "referenceNumber")
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
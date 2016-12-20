package com.musala.xml.model.executable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Student.class,Teacher.class})
public abstract class SchoolObject {
    String name;
    int age;
    String referenceNumber;

   
    public void setAge(int age) {
        this.age = age;
    }

    
    public void setName(String name) {
        this.name = name;
    }

   @XmlAttribute(name="referenceNumber")
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

package com.musala.xml.parser.executable;

enum ClassNumberText {
    first,
    second,
    third,
    fourth,
    fifth,
    sixth,
    seventh,
    eighth,
    ninth,
    nth
}

enum MessageProvider {
    STRING_NUMBER_OF_CLASS_OBJECTS_INFO("%nThe %s class has %d %s: %n"),
    STRING_STUDENTSANDTEACHERS_INFO("%s is %d years old"),
    STRING_STUDENTS_FACNUMBER_INFO("with fac. number %d.%n"),
    STRING_SCHOOL_INFO("The school name is: %s!%nThere %s %d school class%s.");
    private String value;

    private MessageProvider(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

enum PersonProperties {
    name,
    age,
    referenceNumber,
    facultyNumber
}


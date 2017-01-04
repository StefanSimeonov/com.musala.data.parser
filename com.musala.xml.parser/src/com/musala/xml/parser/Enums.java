package com.musala.xml.parser;

enum ClassNumberText {
	first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, nth
}

enum MessageProvider {
	STRING_NUMBER_OF_STUDENTS_INFO("The %s class has %d students: %n"), STRING_TEACHER_INFO(
			"The teacher of the class is %s- %d years old.%n"), STRING_STUDENTS_INFO(
					"%s is %d years old with fac. number %s.%n"), STRING_SCHOOL_INFO(
							"The school name is: %s!%nThere %s %d school class%s.%n"), STRING_SCHOOLS_INFO(
									"The %s school name is: %s!%nThere %s %d school class%s.%n");
	private String value;

	private MessageProvider(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
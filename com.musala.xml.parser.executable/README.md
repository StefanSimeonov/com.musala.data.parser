# ``XML-OBJECT MAPPING``
### &nbsp; &nbsp; &nbsp; &nbsp; ``external file path``
------------------------
This program is create to transform xml school's data information to simple java objects. This is happening with
the Java Architecutre for XML Binding [JAXB](http://javatpoint.com/jaxb-tutorial)  which provides a fast and convenient way for bind schemas. The schools information is in format: 

```xml
	<school name="...">
	  <students
		 <student referenceNumber="...">
			<name>...</name>
			<age>...</age>
			<facultyNumber>...</facultyNumber>
		 </student>
		 <student referenceNumber="....">
			<name>...</name>
			<age>...</age>
			<facultyNumber>...</facultyNumber>
		 </student>
	  </students>
	  <teachers>
		 <teacher referenceNumber="...">
			<name>...</name>
			<age>...</age>
	   </teacher>
		 <teacher referenceNumber="...">
			<name>...</name>
			<age>...</age>
	  </teachers>
	  <schoolClass>
		 <teacherReference>...</teacherReference>
		 <studentReference>...</studentReference>
		 <studentReference>...</studentReference>
	</school>
```

The java objects **have to** be written with proper **setter** and getter <br /> methods, and proper **annotations**, because of jaxb's parsing <br />requirement.
The program read this data and after transfer it to <br /> java objects, represent them descriptively in two files. <br />The xml file is givven from a **"resourses"** folder in the project. <br />. It is obligatory to give a path to computer's direktory with a <br /> file name and **<... .txt>** file format, where all information is <br /> going to be stored. Second **<... .property>** file is attached in <br /> the same direktory. Its information is displayed in this way:<br /> 
<pre>
<TypeOfSchoolPerson>.<currentClassNumber>.<PersonProperty>=<TheValueOfTheProperty>
</pre>
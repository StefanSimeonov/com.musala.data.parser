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
The program read this data and after transfer it to <br /> java objects, represent them descriptively in two files. <br />The xml file is given from a **"resourses"** folder in the project. <br />. It is obligatory to give a path to computer's directory with a <br /> file name and **<... .txt>** file format, where all information is <br /> going to be stored. Second **<... .property>** file is attached in <br /> the same direktory. Its information is displayed in this way:<br /> 
<pre>
<TypeOfSchoolPerson>.<currentClassNumber>.<PersonProperty>=<TheValueOfTheProperty>
</pre>


<h3> How to build the executable jar </h3>
Navigate to the project root folder, open a command prompt and type `mvn clean install` (it is required that you have **Maven** set up on your machine).This's going to open the root pom, which have module in ../src, which have modules in ..src/main and ..src/test. Install command will cause cascade falling down from root pom to each child poms. In this latest childer pom's builds exist references to 'where to compiling and testing' commands setted in `<testSourceDirectory>` and `<sourceDirectory>`. The .class files is going to be created from all .java ones. The jar files are located in the `target` folders of the latest child poms (..src/main/test and ..src/main/java).
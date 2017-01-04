# ``XML-OBJECT MAPPING``
### &nbsp; &nbsp; ``xml-database similarity ``
------------------------
This program is create to transform xml school's data information to simple java objects. This is happening with
the Java Architecutre for XML Binding [JAXB](http://javatpoint.com/jaxb-tutorial)  which provides a fast and convenient way for bind schemas. The schools information is in format: 
<pre>
<code>

 &lt;schoolDatabaseRepo>
   &lt;schools>
      &lt;school primaryKey="...">
         &lt;name>...&lt;/name>
         &lt;location>...&lt;/location>
         &lt;trend>...&lt;/trend>
      &lt;/school>
      &lt;school primaryKey="...">
         &lt;name>...&lt;/name>
         &lt;location>...&lt;/location>
         &lt;trend>...&lt;/trend>
      &lt;/school>
       ...
   &lt;/schools>
   &lt;schoolClasses>
      &lt;schoolClass primaryKey="...">
         &lt;schoolForeignKey>...&lt;/schoolForeignKey>
         &lt;teacherForeingKey>...&lt;/teacherForeingKey>
         &lt;subject>...&lt;/subject>
       ...
   &lt;/schoolClasses>
   &lt;students>
      &lt;student primaryKey="...">
         &lt;schoolClassForeignKey>...&lt;/schoolClassForeignKey>
         &lt;name...&lt;/name>
         &lt;age>...&lt;/age>
         &lt;facultyNumber>...&lt;/facultyNumber>
      &lt;/student>
      ...
   &lt;/students>
   &lt;teachers>
      &lt;teacher primaryKey="...">
         &lt;name>...&lt;/name>
         &lt;age>...&lt;/age>
      ...
   &lt;/teachers>
&lt;/schoolDatabaseRepo>
</pre>

The java objects **have to** be written with proper **setter** and getter <br /> methods, and proper **annotations**, because of jaxb's parsing <br />requirement.
The program read this data and after transfer it to <br /> java objects, represent them descriptively on the console. <br />The xml file should be givven as a first argument on the console. <br /> It is obligatory to give a path to computer's direktory with a <br /> file name and **<... .txt>** suffix , where all information is <br /> going to be stored.
# ``XML-OBJECT MAPPING``
### &nbsp; &nbsp; &nbsp; &nbsp; ``external file path``
------------------------
This program is create to transform xml school's data information to simple java objects. This is happening with
the Java Architecutre for XML Binding [JAXB](http://javatpoint.com/jaxb-tutorial)  which provides a fast and convenient way for bind schemas. The schools information is in format: 
<pre>
<code>

   &lt;school name="..."&gt;
      &lt;students
         &lt;student referenceNumber="..."&gt;
            &lt;name&gt;...&lt;/name&gt;
            &lt;age&gt;...&lt;/age&gt;
            &lt;facultyNumber&gt;...&lt;/facultyNumber&gt;
         &lt;/student&gt;
         &lt;student referenceNumber="...."&gt;
            &lt;name&gt;...&lt;/name&gt;
            &lt;age&gt;...&lt;/age&gt;
            &lt;facultyNumber&gt;...&lt;/facultyNumber&gt;
         &lt;/student&gt;
      &lt;/students&gt;
      &lt;teachers&gt;
         &lt;teacher referenceNumber="..."&gt;
            &lt;name&gt;...&lt;/name&gt;
            &lt;age&gt;...&lt;/age&gt;
       &lt;/teacher&gt;
         &lt;teacher referenceNumber="..."&gt;
            &lt;name>...&lt;/name&gt;
            &lt;age>...&lt;/age&gt;
      &lt;/teachers&gt;
      &lt;schoolClass&gt;
         &lt;teacherReference&gt;...&lt;/teacherReference&gt;
         &lt;studentReference&gt;...&lt;/studentReference&gt;
         &lt;studentReference&gt;...&lt;/studentReference&gt;
    &lt;/school&gt;
</pre>

The java objects **have to** be written with proper **setter** and getter <br /> methods, and proper **annotations**, because of jaxb's parsing <br />requirement.
The program read this data and after transfer it to <br /> java objects, represent them descriptively on the console. <br />The xml file should be givven as a first argument on the console. <br /> It is obligatory to give a path to computer's direktory with a <br /> file name and **<... .txt>** file format, where all information is <br /> going to be stored. Second **<... .property>** file is attached in <br /> the same direktory. His information is displayed in this way:<br /> 
<pre>
&lt;TypeOfSchoolPerson&gt;.&lt;currentClassNumber&gt;.&lt;PersonProperty&gt;=&lt;TheValueOfTheProperty&gt;
</pre>
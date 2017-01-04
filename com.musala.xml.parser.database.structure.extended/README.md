# ``XML-OBJECT MAPPING``
### &nbsp; &nbsp; ``xml-database similarity ``
------------------------
This program is create to transform xml school's data information to simple java objects. This is happening with
the Java Architecutre for XML Binding [JAXB](http://javatpoint.com/jaxb-tutorial)  which provides a fast and convenient way for bind schemas. The schools information is in format: 

```xml
 <schoolDatabaseRepo>
   <schools>
      <school primaryKey="...">
         <name>...</name>
         <location>...</location>
         <trend>...</trend>
      </school>
      <school primaryKey="...">
         <name>...</name>
         <location>...</location>
         <trend>...</trend>
      </school>
       ...
   </schools>
   <schoolClasses>
      <schoolClass primaryKey="...">
         <schoolForeignKey>...</schoolForeignKey>
         <teacherForeingKey>...</teacherForeingKey>
         <subject>...</subject>
       ...
   </schoolClasses>
   <students>
      <student primaryKey="...">
         <schoolClassForeignKey>...</schoolClassForeignKey>
         <name...</name>
         <age>...</age>
         <facultyNumber>...</facultyNumber>
      </student>
      ...
   </students>
   <teachers>
      <teacher primaryKey="...">
         <name>...</name>
         <age>...</age>
      ...
   </teachers>
</schoolDatabaseRepo>
```
The java objects **have to** be written with proper **setter** and getter <br /> methods, and proper **annotations**, because of jaxb's parsing <br />requirement.
The program read this data and after transfer it to <br /> java objects, represent them descriptively on the console. <br />The xml file should be givven as a first argument on the console. <br /> It is obligatory to give a path to computer's direktory with a <br /> file name and **<... .txt>** suffix , where all information is <br /> going to be stored.
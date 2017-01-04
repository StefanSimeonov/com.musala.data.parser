# ``Database-Console program``
### &nbsp;  &nbsp; &nbsp; &nbsp;console input and output rendering
------------------------
This program is create to communicate between relational database and java's console, as showing the givin database table and his own columns consecutively. The user interface is asking for every step, from database server name, to each simply table's column name and choosen command on it.<br/>
<li>**In our particular implementation:** <br/>
We were using console rendering to all information. The input stream is also given by the java's console.
**XAMPP**, free and open source cross-platform web server providing our working MySQL server, where database has been set. The graphic interface for creating,altering and etc. the base is **HeidiSQL**. The connection string is associate with this server.
<br/><li>**Modeling the base in this way:**<br/>
Our base is schools relationship model. Each school has many school classes. In every class has many students with only one teacher. Each teacher could be in more than one school. Database modeling have to be in this format:<br/>
<ul>
<li>schools table has columns: 
<ol>
<li>id:int-primary key 
<li>name:varchar
<li>location: varchar
<li>trend:varchar
</ol>
<li>schoolclasses table has columns:
<ol>
<li>id:int-primary key
<li>teacherId:int-foreign key to teachers table
<li>schoolId:int-foreign key to schools table
<li>subject:varchar
</ol>
<li>teacher table has columns:
<ol>
<li>id:int-primary key
<li>name:varchar
<li>age:int
</ol>
<li> strudents table has columns:
<ol>
<li>id:int-primary key
<li>name:varchar
<li>age:int
<li>facultyNumber:int
<li>schoolClassId:int-foreign key to schoolclasses table
</ol>
**Pay Attention: Before using this program ensure that XAMPP MySQL server is turned on and mysql connector jar file is referenced in project library!**



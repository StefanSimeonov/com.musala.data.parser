# Web single page parser

An implementation of the database parser program using web interface based on asynchronous client-server communication and single-page design based on **AngularJS**. 

The program executes asynchronous post requests in single **servlet**, which handles the request body, comes from front-end.<br/>
**Apache Tomcat** is responsible for taking user request from accurate end points.
When the first html is being loaded the program is going to expect from the user a proper connection **credentials**. When the query is being triggered the post request, holding the credentials, is send to the servlet. If the connection is succesfull, the second html view is loading, where the user should give exact query. The request to the same servlet is going to reload. If the request failed in some reason, the "welcome" html view is going to be load.<br/>
In the back-end, represented by java servlet, have 3 types of query to the database. Each one depends on what kind of request comes from the front end. The engine takes responsibility to handle all requests to the base and response adequately. **JDBC** is used for this purpose. </br>
A front-end validation is used for elude explicit connection to second and third html views.
All html views are ensured by AngularJs's mechanism for single page applications where all things happened in just one page with different views, depending on given user commands
**Pay Attention: Before using this program ensure that XAMPP MySQL and Tomcat servers are turned on and mysql connector jar file is referenced in the project library!**
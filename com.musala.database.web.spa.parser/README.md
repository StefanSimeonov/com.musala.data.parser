# Web single page parser

An implementation of the database parser program using web interface based on asynchronous client-server communication and single-page design based on **AngularJS**. 

The program executes asynchronous post requests in single **servlet**, which handles the request body, comes from front-end.<br/>
**Apache Tomcat** is responsible for taking user request from accurate end points.
When the first html is being loaded the program is going to expect from the user a proper connection **credentials**. When the query is being triggered the post request, holding the credentials, is send to the servlet. If the connection is succesfull, the second html view is loading, where the user should give exact query. The request to the same servlet is going to reload. If the request failed in some reason, the "welcome" html view is going to be load.<br/>
In the back-end, represented by java servlet, have 3 types of query to the database. Each one depends on what kind of request comes from the front end. The engine takes responsibility to handle all requests to the base and response adequately. **JDBC** is used for this purpose. </br>
A front-end validation is used for elude explicit connection to second and third html views.
All html views are ensured by AngularJs's mechanism for single page applications where all things happened in just one page with different views, depending on given user commands, thus providing a more comfortable front-end data processing. </br>

**Back-end tests** are accomplished with **mocking** the http request and response objects, provided by the Java Servlets. Mocking is a technique for set precisely defined behaviour of every mocked object. Using this "overrided" object we can skip creating objects, which depends our tested ones, also skip the incapabillity to creating them in some cases. </br>
**Front-end tests** are accomplished in two different ways: Mocking the angular's models with Jasmine and testing their existence and changings, and integration end-to-end tests with Protractor.</br>
The first approach lies on injecting from the real angular's module, controllers, scope and rootScope to our own objects and using them to reach the real one's variables. We use a fake created functions for changing some variables. This is happening with spyOn function, distributed with Jasmine libraries.</br>
The second testing approach needs nodejs platform. After that installing the protractor using `npm install -g protractor` and make sure **Selenium server** is updated and turned on with `webdriver-manager update`, `webdriver-manager start`. Protractor needs two files to run, a spec file and a configuration file. In the first one stays the logical code, but in the second one stays a configuration code with additional specifics. The tests have to be run pushing on the command-line the exact command: `protractor conf_file_name_.js` from the current configuration's diretory.

**Pay Attention: Before using this program ensure that XAMPP MySQL and Tomcat servers are turned on and mysql connector jar file is referenced in the project library!**
# Web single page spring boots parser

An implementation of a database parser program using **web interface** based on asynchronous client-server communication, single-page design based on **AngularJS**, **Maven** for injection the dependacies and **Spring boots** for communication the all layers.

The program executes asynchronous post requests to **spring mvc controller**, which handles the request bodies and wrap it in previous made java objects for that reason.This controllers expects requests in **Json** format and return String answers to the front-end formatted as **Json objects.**<br/>
**Apache Tomcat** is responsible for taking user request from accurate end points. The server comes **implicitly** with spring boots.<br/>
When the first html is being loaded the program is going to expect from the user a proper connection **credentials**. When the query is being triggered, the post request, holding the credentials, is send to the servlet. If the connection is succesfull, the second html view is loading, where the user should give exact query. The request to the same servlet is going to reload. If the request failed in some reason, the "welcome" html view is going to be load.<br/>
In the back-end's every single controller have 3 types of queries. Each one calls the service, which call the engine. The engine takes responsibility to handle all requests to the base and response adequately. **JDBC** is used for this purpose. The service and engine are injected **automatically by Spring injection-annotation configuration type** </br>
A front-end validation is used for elude explicit connection to second and third html views. This is happened with a rootScope, declared at the just beggining of the page rendering.<br/>
All html views are ensured by AngularJs's mechanism for single page applications where all things happened in just one page with different views, depending on given user commands.<br/>
All dependancies are injected by **Maven**.<br/>

**Back-end tests** are accomplished with **mocking** the http request and response objects, provided by the Java Servlets. Mocking is a technique for set precisely defined behaviour of every mocked object. Using this "overrided" object we can skip creating objects, which depends our tested ones, also skip the incapabillity to creating them in some cases. </br>
**Front-end tests** are accomplished with integration end-to-end tests using Protractor.</br>
The approach needs nodejs platform. After that installing the protractor using `npm install -g protractor` and make sure **Selenium server** is updated and turned on with `webdriver-manager update`, `webdriver-manager start`. Protractor needs two files to run, a spec file and a configuration file. In the first one stays the logical code, but in the second one stays a configuration code with additional specifics. The tests have to be run pushing on the command-line the exact command: `protractor conf_file_name_.js` from the current configuration's diretory.

**Pay Attention: Before using this program ensure that XAMPP MySQL and Tomcat servers are turned on and mysql connector jar file is referenced in the project library!**
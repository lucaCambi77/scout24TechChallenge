# TechChallenge for Scout24
v. 0.0.1

## Application Server 
The applications is not using Ejb, so it should be cross application server

## AngularJs
AngularJS  1.4.0

## Bower
Bower 1.7.9 (yoeman template)

#Java
compiled with 1.8

#Installation

To install the application:

Once the application is unzipped

1) on the root folder run;

mvn clean install 

and install /HOME_FOLDE/.m2/repository/Scout24/TechChallenge/0.0.1-SNAPSHOT/TechChallenge-0.0.1-SNAPSHOT.war in your application server

2) running in your favorite IDE, import the project as a maven project. Once maven plugin has updated the project, add it to your application server


#Comments

All tasks requested have been completed.

All though, many aspects haven't been examined in depth:

1) Html parse has been done with Jsoup, but i would had liked to do it on client side with javascript. I couldn't manage to do it because of CORS errors

2) Exception's catch in backend could had been done better, but i was more focused on completing all the tasks



 
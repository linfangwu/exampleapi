It has 2 maven probjects.
1. ExampleApiServer   is a spring boot server api project. It has all the service, controller, exception handler and test cases. The main class is App.java  .
   The test cases are coded in Spring Junit, they can be run without starting App.java
2. ExampleApiClient  is a simple java class . The client implements RestTemplate.  AppClient.java is the main class.

How to run it ? The easy way is by Eclipse
1) import these 2 projects in Eclipse
2) set up maven dependencies of the 2 projects.
3)  run App.java in ExampleApiServer as a java Application, the spring boot will start and works as a api server.
4) run AppClient.java in ExampleApiClient as a java Application, following the command line, you will see the result.

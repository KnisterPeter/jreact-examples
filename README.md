# jreact-examples

* simple-spring-boot
  This is a very simple and unoptimized universal react application with JReact.  
  The Java part of the application is to provide a REST server and serve static
  resources while there is a ServletFilter rendering each request which accepts
  `text/html` as react application.
  
  It should be sufficient to run the following commands in the simple-spring-boot directory.
  ```shell
  mvn clean package
  java -jar java -jar target/jreact-example-0.0.1-SNAPSHOT.jar
  ```
  Then open the url http://localhost:8080 with your browser.

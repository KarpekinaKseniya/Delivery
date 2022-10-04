# Configure Cost Delivery Testing Task

For starting application prefer docker-compose, but you also can use maven.

### Maven Instruction

Change liquibase flag and change database configs for your own in application.properties as below

````
spring.liquibase.enabled=true
````

````
mvn clean install
````

````
 mvn --projects backend spring-boot:run
````

### Docker Instruction

For generated target folder

````
mvn clean install
````

Build and Run Docker Images

````
docker-compose up
````

Rebuild Docker Images

````
docker-compose build
````

### Testing

For looking tests coverage you can use maven command(Report is in /target/site/jacoco/index.html)

````
mvn verify
````

For looking frontend tests coverage in frontend folder start command

````
npm test -- --coverage --watchAll=false
````   

Local:

+ http://localhost:8080/
+ http://localhost:8080/areas
+ http://localhost:8080/swagger-ui/index.html

Idea for project took from testing task: https://github.com/cscart/apply-for-job/tree/master/frontend/developer
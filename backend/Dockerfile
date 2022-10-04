FROM maven:3.8.6-eclipse-temurin-17 as build

VOLUME /tmp
COPY /target/backend-1.0.0-SNAPSHOT.jar app.jar
# Run the jar file
EXPOSE 8081 8081
ENTRYPOINT ["java","-jar","/app.jar"]
FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
RUN ["chmod", "+x", "app.jar"]
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
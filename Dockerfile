FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
RUN chmod 777 app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
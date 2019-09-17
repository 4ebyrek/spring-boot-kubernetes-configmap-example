FROM openjdk:8-jdk-alpine
RUN mkdir -p /app
WORKDIR /app
COPY target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
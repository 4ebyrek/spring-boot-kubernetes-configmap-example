FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/spring-boot-kubernetes-configmap-0.0.1-SNAPSHOT.jar /app/app.jar
RUN ["chmod", "+x", "app.jar"]
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]
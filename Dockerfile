FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/spring-boot-kubernetes-configmap-0.0.1-SNAPSHOT.jar /app/app-new.jar
RUN ["chmod", "+x", "/app/app-new.jar"]
EXPOSE 8080
CMD ["java", "-jar", "/app/app-new.jar"]
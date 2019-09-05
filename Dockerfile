FROM openjdk:8-jdk-alpine
COPY target/spring-boot-kubernetes-configmap-0.0.1-SNAPSHOT.jar example-app.jar
RUN mkdir /configs
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "example-app.jar"]
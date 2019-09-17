FROM openjdk:8-jdk-alpine
ADD ./target/spring-boot-kubernetes-configmap-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-jar", "/app/spring-boot-kubernetes-configmap-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
FROM openjdk:8-jdk-alpine
RUN mkdir /usr/myapp
COPY target/*.jar /usr/myapp/app.jar
WORKDIR /usr/myapp
EXPOSE 8080
CMD ["java", "-Xms128m", "-Xmx256m", "-jar", "app.jar"]
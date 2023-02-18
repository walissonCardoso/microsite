FROM openjdk:17-alpine

VOLUME /tmp

COPY target/*.jar app.jar

EXPOSE 8080/tcp

ENTRYPOINT ["java", "-jar", "app.jar"]

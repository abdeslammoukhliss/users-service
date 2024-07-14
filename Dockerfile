# Dockerfile for user-service
FROM openjdk:11-jre-slim
COPY target/users-service-0.0.1-SNAPSHOT user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]

FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${HAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
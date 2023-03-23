FROM openjdk:18.0

ARG WAR_FILE=./build/libs/*.jar

COPY ${WAR_FILE} webapp.jar

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "webapp.jar"]
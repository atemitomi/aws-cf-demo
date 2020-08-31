FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /home/spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} batch-app.jar
ENTRYPOINT ["java","-jar","batch-app.jar"]
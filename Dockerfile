FROM eclipse-temurin:17-jdk-jammy
MAINTAINER Khoa Nguyen
#VOLUME /tmp
#ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
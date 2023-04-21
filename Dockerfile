FROM eclipse-temurin:17-jdk-jammy
MAINTAINER Khoa Nguyen


# Set the working directory to /pastebin
WORKDIR /pastebin

# Copy the source code to the container
COPY . /pastebin

# Install Gradle
RUN apt-get update && apt-get install -y gradle
RUN rm -rf /var/lib/apt/lists/*

# Build the Spring Boot application with Gradle
RUN ./gradlew clean build

# Expose port 8080 for the Spring Boot application
EXPOSE 8080

# Start the Spring Boot application
CMD ["java", "-jar", "./build/libs/pastebin.jar"]

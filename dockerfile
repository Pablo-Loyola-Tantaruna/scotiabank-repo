# Use an official Maven image as a parent image
FROM gradle:7.2.0-jdk11 AS build

# Set the working directory in the image to /app
WORKDIR /app

# Start with a base image containing Java runtime
FROM openjdk:11-jre-slim

# Make port 8080 available to the world outside this container
EXPOSE 8083

# Set the application's jar file
ADD ./build/libs/scotiabank-1.0.0-SNAPSHOT.jar scotiabank-1.0.0-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/scotiabank-1.0.0-SNAPSHOT.jar"]
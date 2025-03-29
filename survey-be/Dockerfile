# Use OpenJDK 24 as the base image
FROM openjdk:24-jdk-slim

# Set the working directory inside the container
WORKDIR /app

RUN mkdir -p /root/.oci

# Copy the JAR file to the container
COPY target/demo-1.0-SNAPSHOT.jar .

COPY config.yml .

COPY config /root/.oci/
COPY private.pem /root/.oci/

# Expose the port (e.g., 8080 for a web app like Spring Boot)
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "demo-1.0-SNAPSHOT.jar", "server", "config.yml"]
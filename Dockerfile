# Stage 2: Build the final image with Java application
FROM openjdk:17-jdk-slim

WORKDIR app

RUN apt-get update \
    && apt-get install -y \
        texlive-latex-base \
        texlive-science \
        curl \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* \

# Copy the Spring Boot application JAR into the Docker image
COPY build/libs/resolventa.jar resolventa.jar

# Copy the 'wang' binary from the previous stage
COPY raw/wang raw/wang

RUN chmod +x ./raw/wang

# Expose the port that your Spring Boot application uses
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "./resolventa.jar"]
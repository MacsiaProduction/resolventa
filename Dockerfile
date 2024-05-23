# Stage 1: Build Haskell application
FROM haskell:8.0.2 as haskell-builder

# Set the working directory inside the container
WORKDIR /app

# Clone the WangsAlgorithm repository
RUN git clone https://github.com/joom/WangsAlgorithm.git

# Change to the WangsAlgorithm directory
WORKDIR /app/WangsAlgorithm

ENV  GIT_BUFFER_SIZE=52428800
ENV  GIT_HTTP_LOW_SPEED_LIMIT=100

# Build the project using stack with specific LTS version
RUN stack build

# Stage 2: Build the final image with Java application
FROM openjdk:17-jdk-slim

WORKDIR app

RUN apt-get update \
    && apt-get install -y \
        texlive-latex-base \
        texlive-science \
        curl \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Copy the Spring Boot application JAR into the Docker image
COPY build/libs/resolventa.jar resolventa.jar

# Copy the 'wang' binary from the previous stage
COPY --from=haskell-builder /app/WangsAlgorithm/.stack-work/dist/x86_64-linux/Cabal-1.24.2.0/build/wang/wang raw/wang

RUN chmod +x ./raw/wang

# Expose the port that your Spring Boot application uses
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "./resolventa.jar"]
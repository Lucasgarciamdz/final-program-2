# ================================
# Stage 1: Build the application
# ================================
FROM amazoncorretto:21.0.4-alpine3.20 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy over the Maven Wrapper related files
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .

# Create a dedicated layer for downloading dependencies using Maven Wrapper
RUN ./mvnw dependency:go-offline -B

# Copy the entire source code after the dependency layer is cached
COPY src ./src

# Build the application without running tests
RUN ./mvnw clean package -DskipTests

# ================================
# Stage 2: Create the runtime image
# ================================
FROM amazoncorretto:21.0.4-alpine3.20

# Set the working directory for the runtime container
WORKDIR /app

# Copy the Spring Boot JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

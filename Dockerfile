# ==========================================
# Stage 1: Build stage
# ==========================================
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy pom.xml first to resolve and cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the source code and build the executable jar
COPY src ./src
RUN mvn clean package -DskipTests

# ==========================================
# Stage 2: Runtime stage
# ==========================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create a non-root system group and user for security
RUN addgroup -S spring && adduser -S spring -G spring

# Copy the built JAR file from the builder stage
COPY --from=build /app/target/*.jar app.jar

# Switch to the non-root user
USER spring:spring

# Expose the default backend port
EXPOSE 8080

# Configure JVM parameters for container environment and execute the application
ENTRYPOINT ["java", "-XX:+UseG1GC", "-XX:+ExitOnOutOfMemoryError", "-jar", "app.jar"]

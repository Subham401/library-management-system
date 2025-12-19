# ---------- Build stage ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (cache optimization)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests


# ---------- Runtime stage ----------
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]

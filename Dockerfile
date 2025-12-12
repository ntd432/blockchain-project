# ---- Build Stage ----
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Package the application (skip tests if desired)
RUN mvn clean package -DskipTests

# ---- Runtime Stage ----
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built JAR from Maven target/
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "app.jar"]

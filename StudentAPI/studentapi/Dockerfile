# Stage 1: Build the backend
FROM maven:3.8.4-openjdk-17-slim AS backend-build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Serve the backend
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=backend-build /app/target/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Backend - Spring Boot
FROM adoptopenjdk:11-jdk-hotspot AS backend-build
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle ./gradle
RUN chmod +x gradlew
RUN ./gradlew --version
COPY src ./src
RUN ./gradlew build

# Final image
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=backend-build /app/build/libs/*.jar backend.jar
EXPOSE 8080
CMD ["java", "-jar", "backend.jar"]
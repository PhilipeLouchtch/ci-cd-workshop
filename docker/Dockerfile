# syntax=docker/dockerfile:1

# Runtime image for Spring Boot fat JAR (Java 21)
FROM eclipse-temurin:21-jre-alpine

LABEL org.opencontainers.image.title="CI_CD-workshop Demo App"
LABEL org.opencontainers.image.description="Simple Spring Boot app built in Level 7 and packaged with Docker"
LABEL org.opencontainers.image.vendor="CI_CD-workshop"

# Create non-root user
RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

# The workflow copies the built JAR to docker/app.jar before building this image
COPY app.jar /app/app.jar

# Change ownership and drop privileges
RUN chown -R spring:spring /app
USER spring

ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=""

EXPOSE 8080

# Use shell form so JAVA_OPTS is expanded at runtime
CMD sh -c "java $JAVA_OPTS -jar /app/app.jar"

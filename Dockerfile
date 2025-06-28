FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B package --file pom.xml

COPY target/*.jar /app/.

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

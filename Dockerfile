    # Use a base image with Java installed (e.g., OpenJDK)
    FROM eclipse-temurin:21-jdk

    # Set the working directory inside the container
    WORKDIR /app

    # Copy the Maven project's pom.xml to leverage Maven's dependency caching
    COPY pom.xml .

    # Copy the source code
    COPY src ./src

    # Build the application using Maven (this will download dependencies)
    RUN mvn -B package --file pom.xml

    # Copy the built JAR/WAR file into the image
    COPY target/*.jar /app/.

    # Expose the port your application listens on (if applicable)
    EXPOSE 8080

    # Define the command to run your application when the container starts
    ENTRYPOINT ["java", "-jar", "app.jar"]

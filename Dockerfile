FROM eclipse-temurin:17.0.9_9-jre-jammy
WORKDIR /app
COPY target/springqraphql.jar /app/springqraphql.jar
EXPOSE 8080
CMD ["java", "-jar", "springqraphql.jar"]
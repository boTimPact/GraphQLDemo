FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY target/springqraphql.jar /app/springqraphql.jar
EXPOSE 8080
CMD ["java", "-jar", "springqraphql.jar"]
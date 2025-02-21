FROM eclipse-temurin:21-jdk-alpine
EXPOSE 8080

VOLUME /tmp
COPY build/libs/wordle-solver-2.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
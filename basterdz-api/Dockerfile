FROM amazoncorretto:17

COPY build/libs/basterdz-api-0.0.1-SNAPSHOT.jar app.jar

ENV PROFILE=dev

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "/app.jar"]

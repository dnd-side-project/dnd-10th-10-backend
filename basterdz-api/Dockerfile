FROM amazoncorretto:17

ARG PROFILE=dev
ARG ENCRYPT_KEY

COPY basterdz-api/build/libs/basterdz-api-0.0.1-SNAPSHOT.jar app.jar

ENV PROFILE=${PROFILE}
ENV ENCRYPT_KEY=${ENCRYPT_KEY}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "-Dspring.encryptor.key=${ENCRYPT_KEY}", "/app.jar"]

FROM gradle:jdk17
USER root
WORKDIR /app
COPY build/libs/taxi-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "taxi-0.0.1-SNAPSHOT.jar", "-Dspring.profiles.active=cloud"]
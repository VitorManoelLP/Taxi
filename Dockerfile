FROM gradle:jdk17
USER root
WORKDIR /app
COPY app/ .

FROM maven:3.8.1-jdk-8

ENV TZ="Asia/Shanghai"
ENV SPRING_PROFILES_ACTIVE=test
ENV GENERATOR_SOURCE_DIR=/generator

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar
COPY src/main/resources/generator generator

ENTRYPOINT ["java", "-Xms128M", "-Xmx512M", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

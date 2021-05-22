FROM java:8-jdk-alpine

ENV TZ="Asia/Shanghai"

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Xms128M", "-Xmx512M", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

FROM openjdk:17

ENV APP_HOME=/apps

ARG JAR_FILE_PATH=build/libs/query-1.0.0.jar

WORKDIR $APP_HOME

COPY $JAR_FILE_PATH app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
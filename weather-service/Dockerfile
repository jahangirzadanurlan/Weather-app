FROM adoptopenjdk/openjdk11
EXPOSE 8083
ARG JAR_FILE=build/libs/weather-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} weather.jar
ENTRYPOINT ["java", "-jar","/weather.jar"]
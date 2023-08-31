FROM adoptopenjdk/openjdk11
EXPOSE 8080
ARG JAR_FILE=build/libs/api-gateway-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} gateway.jar
ENTRYPOINT ["java", "-jar","/gateway.jar"]
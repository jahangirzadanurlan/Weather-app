FROM adoptopenjdk/openjdk11
EXPOSE 8761
ARG JAR_FILE=build/libs/eureka-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} eureka.jar
ENTRYPOINT ["java", "-jar","/eureka.jar"]
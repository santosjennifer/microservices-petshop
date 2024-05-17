FROM maven:3.9.6-sapmachine-21 as build
WORKDIR /app
COPY ms-person .
RUN mvn clean package

FROM openjdk:21
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
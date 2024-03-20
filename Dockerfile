FROM maven:3.9.6 as build
LABEL authors="home"
COPY . /user/src/credentials_service
WORKDIR /user/src/credentials_service

COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN mvn install -DskipTests

FROM openjdk:21
COPY --from=build /user/src/credentials_service/target/ /app
CMD ["java", "-jar", "/app/credentialsservice-0.0.1-SNAPSHOT.jar"]
FROM maven:3.9.6 as build
ARG APP_VERSION
LABEL authors="home"
WORKDIR /user/src/credentials_service
COPY . .

RUN mvn install -DskipTests

FROM openjdk:21
COPY --from=build /user/src/credentials_service/target/*.jar /app/
WORKDIR /app
CMD java -jar credentialsservice-${APP_VERSION}-SNAPSHOT.jar
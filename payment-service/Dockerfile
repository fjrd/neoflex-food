FROM maven:3.8.1-openjdk-17 AS MAVEN_BUILD

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn clean package

FROM openjdk:17-jdk-alpine3.14

COPY --from=MAVEN_BUILD /app/target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]
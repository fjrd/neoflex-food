FROM maven:3.8.1-openjdk-17 AS MAVEN_BUILD

RUN cd ../ $
RUN cd shared-order-dto/ $
RUN mvn clean install

COPY pom.xml ./
COPY src ./src

WORKDIR /app

RUN mvn clean install

FROM openjdk:17-alpine

COPY --from=MAVEN_BUILD /app/target/*.jar app.jar

## Multi-stage build

#### 1st STAGE ###
FROM maven:3-openjdk-17 AS builder
WORKDIR /home/app/src/
COPY src ./
COPY pom.xml ../
RUN mvn -f /home/app/pom.xml clean package -DskipTests=true

#### 2nd STAGE ###
FROM openjdk:17 as runtime

RUN useradd -u 1234 user
USER user

WORKDIR /home/lib/
COPY --from=builder /home/app/target/*.jar ./app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/home/lib/app.jar"]

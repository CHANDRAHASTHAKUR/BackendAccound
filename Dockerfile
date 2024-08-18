FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY . .
ENTRYPOINT ["java","-jar","/app/target/accounts-0.0.1-SNAPSHOT.jar"]

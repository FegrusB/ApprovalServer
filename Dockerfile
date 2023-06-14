FROM eclipse-temurin:20.0.1_9-jre
WORKDIR /app

COPY /target/ApprovalServer-0.0.1-SNAPSHOT.jar .
EXPOSE 80

CMD [ "java", "-jar", "ApprovalServer-0.0.1-SNAPSHOT.jar"]
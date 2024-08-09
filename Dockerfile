FROM openjdk:17-alpine
COPY app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]